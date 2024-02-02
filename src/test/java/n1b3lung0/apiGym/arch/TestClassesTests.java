package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.common.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE)
public class TestClassesTests extends BaseArchTest {

    @ArchTest
    static final ArchRule testsShouldExtendABaseTest = classes()
            .that().haveSimpleNameEndingWith(TEST)
            .or().haveSimpleNameEndingWith(TESTS)
            .should().beAssignableTo(BaseIntegrationTest.class)
            .orShould().beAssignableTo(BaseUnitTest.class)
            .orShould().beAssignableTo(BaseArchTest.class);

//    @ArchTest
//    static final ArchRule testsShouldBeAnnotatedWithTest = methods()
//            .that().areDeclaredInClassesThat().haveSimpleNameEndingWith(TEST)
//            .or().areDeclaredInClassesThat().haveSimpleNameEndingWith(TESTS)
//            .and().arePublic()
//            .should().beAnnotatedWith(Test.class);

    @ArchTest
    static final ArchRule beforeEachMethodsShouldBePrivate = methods()
            .that().areDeclaredInClassesThat().haveSimpleNameEndingWith(TEST)
            .or().areDeclaredInClassesThat().haveSimpleNameEndingWith(TESTS)
            .and().areAnnotatedWith(BeforeEach.class)
            .should().bePrivate();

    @ArchTest
    static final ArchRule testFieldsThatAreNotStaticShouldBePrivateOrProtected = fields()
            .that().areDeclaredInClassesThat().haveSimpleNameEndingWith(TEST)
            .or().areDeclaredInClassesThat().haveSimpleNameEndingWith(TESTS)
            .and().areNotStatic()
            .should().bePrivate()
            .orShould().beProtected();

    @ArchTest
    static final ArchRule mothersShouldBeSuffixed = classes()
            .that().resideInAPackage(ANY_MOTHER_PACKAGE)
            .should().haveSimpleNameEndingWith(MOTHER)
            .orShould().haveSimpleNameStartingWith(MOTHER);

    @ArchTest
    static final ArchRule mothersShouldBeFinal = classes()
            .that().resideInAPackage(ANY_MOTHER_PACKAGE)
            .should().haveModifier(JavaModifier.FINAL);

    @ArchTest
    static final ArchRule motherMethodsShouldBeStatic = methods()
            .that().areDeclaredInClassesThat().haveSimpleNameEndingWith(MOTHER)
            .or().areDeclaredInClassesThat().haveSimpleNameStartingWith(MOTHER)
            .should().beStatic();
}
