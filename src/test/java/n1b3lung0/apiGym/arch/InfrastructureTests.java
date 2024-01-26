package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class InfrastructureTests extends BaseArchTest {

    @ArchTest
    static final ArchRule infrastructureClassesShouldNotBeAccessedFromApplicationOrDomain = classes()
            .that().resideInAPackage(ANY_INFRASTRUCTURE_PACKAGE)
            .should().onlyBeAccessed().byClassesThat().resideOutsideOfPackages(ANY_DOMAIN_PACKAGE, ANY_APPLICATION_PACKAGE);

    @ArchTest
    static final ArchRule infrastructureShouldNotDependOnControllers = noClasses()
            .that().resideInAPackage(ANY_INFRASTRUCTURE_PACKAGE)
            .should().dependOnClassesThat().resideInAnyPackage(ANY_REST_PACKAGE);

    @ArchTest
    static final ArchRule jpaRepositoriesShouldResideInInfrastructurePackage = classes()
            .that().areInterfaces()
            .and().areAssignableTo(JpaRepository.class)
            .or().areAssignableTo(CrudRepository.class)
            .or().areAssignableTo(PagingAndSortingRepository.class)
            .should().resideInAPackage(ANY_INFRASTRUCTURE_PACKAGE);

//    @ArchTest
//    static final ArchRule repositoryImplementationsShouldResideInInfrastructurePackage = classes()
//            .that().areNotInterfaces()
//            .and().haveSimpleNameEndingWith(REPOSITORY)
//            .should().resideInAPackage(ANY_INFRASTRUCTURE_PACKAGE);
}
