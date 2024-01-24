package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.CompositeArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.core.domain.JavaModifier.STATIC;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class CodingRulesTests extends BaseArchTest {

    @ArchTest
    static final ArchRule noClassesShouldThrowGenericExceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    static final ArchRule exceptionsShouldBeInAExceptionPackage = classes()
            .that().areAssignableTo(RuntimeException.class)
            .or().haveSimpleNameEndingWith(EXCEPTION)
            .should().resideInAPackage(ANY_EXCEPTION_PACKAGE);

    @ArchTest
    static final ArchRule customExceptionsShouldBeFinal = classes()
            .that().areAssignableTo(RuntimeException.class)
            .or().resideInAPackage(ANY_EXCEPTION_PACKAGE)
            .and().resideOutsideOfPackage(BASE_PACKAGE + COMMON_EXCEPTION_PACKAGE)
            .should().haveModifier(JavaModifier.FINAL);

    static final DescribedPredicate<JavaClass> haveOnlyStaticMethods =
            new DescribedPredicate<>("have only static methods") {
                @Override
                public boolean test(JavaClass item) {
                    return CollectionUtils.isNotEmpty(item.getMethods()) && item.getMethods()
                            .stream()
                            .allMatch(m -> m.getModifiers().contains(STATIC));
                }
            };
    @ArchTest
    static final ArchRule utilsShouldBeFinalAndOnlyHavePrivateConstructors = classes()
            .that(haveOnlyStaticMethods)
            .and().areNotAssignableTo(RuntimeException.class)
            .and().areNotAnnotatedWith(SpringBootApplication.class)
            //.should().haveOnlyPrivateConstructors()
            .should().haveModifier(FINAL);

    @ArchTest
    static final ArchRule fieldInjectionShouldNotBeUsed =
            CompositeArchRule
                    .of(GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION)
                    .and(noFields().should(GeneralCodingRules.BE_ANNOTATED_WITH_AN_INJECTION_ANNOTATION))
                    .and(noFields().should().beAnnotatedWith(Autowired.class))
                    .and(noMethods().should().beAnnotatedWith(Autowired.class));

    @ArchTest
    static final ArchRule loggersShouldBePrivateStaticFinal =
            fields().that().haveRawType(Logger.class)
                    .should().bePrivate()
                    .andShould().beStatic()
                    .andShould().beFinal();
}
