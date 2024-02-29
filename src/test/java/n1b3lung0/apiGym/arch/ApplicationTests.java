package n1b3lung0.apiGym.arch;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ApplicationTests extends BaseArchTest {

    @ArchTest
    static final ArchRule applicationClassesShouldNotBeAccessedFromDomain = classes()
            .that().resideInAnyPackage(ANY_APPLICATION_PACKAGE)
            .should().onlyBeAccessed().byClassesThat().resideOutsideOfPackage(ANY_DOMAIN_PACKAGE);

    @ArchTest
    static final ArchRule applicationClassesShouldNotDependOnInfrastructure = noClasses()
            .that().resideInAnyPackage(ANY_APPLICATION_PACKAGE)
            .should().dependOnClassesThat().resideInAnyPackage(ANY_REST_PACKAGE, ANY_INFRASTRUCTURE_PACKAGE, CONFIG_PACKAGE);

    @ArchTest
    static final ArchRule methodsInApplicationServicesShouldHaveTransactionalAnnotation = methods()
            .that().areDeclaredInClassesThat().areAnnotatedWith(Service.class)
            .and().areDeclaredInClassesThat().resideInAPackage(ANY_APPLICATION_PACKAGE)
            .and().areDeclaredInClassesThat().haveSimpleNameNotEndingWith(VALIDATOR)
            .and().arePublic()
            .should().beAnnotatedWith(Transactional.class);
}
