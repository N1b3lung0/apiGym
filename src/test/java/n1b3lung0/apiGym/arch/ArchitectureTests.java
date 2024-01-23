package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ArchitectureTests extends BaseArchTest {

    @ArchTest
    static final ArchRule shouldComplyWithHexagonalArchitecture = onionArchitecture()
            .domainModels(ANY_DOMAIN_PACKAGE)
            .domainServices(ANY_DOMAIN_PACKAGE)
            .applicationServices(ANY_APPLICATION_PACKAGE)
            .adapter(INFRASTRUCTURE, ANY_INFRASTRUCTURE_PACKAGE, CONFIG_PACKAGE, ANY_REST_PACKAGE);

    @ArchTest
    static final ArchRule servicesShouldResideInApplicationOrInfrastructure = classes()
            .that().areAnnotatedWith(Service.class)
            .should().resideInAnyPackage(ANY_APPLICATION_PACKAGE, ANY_INFRASTRUCTURE_PACKAGE);

    @ArchTest
    static final ArchRule dtosShouldResideInApplicationOrInfrastructure = classes()
            .that().haveSimpleNameEndingWith(DTO)
            .or().haveSimpleNameEndingWith(REQUEST)
            .or().haveSimpleNameEndingWith(RESPONSE)
            .or().haveSimpleNameEndingWith(COMMAND)
            .or().haveSimpleNameEndingWith(QUERY)
            .should().resideInAnyPackage(ANY_APPLICATION_PACKAGE, ANY_INFRASTRUCTURE_PACKAGE);

    @ArchTest
    static final ArchRule utilsShouldResideInApplicationOrInfrastructure = classes()
            .that().haveSimpleNameEndingWith(UTILS)
            .should().resideInAnyPackage(ANY_APPLICATION_PACKAGE, ANY_INFRASTRUCTURE_PACKAGE);
}
