package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class NamingConventionTests extends BaseArchTest {

    @ArchTest
    static final ArchRule applicationServicesShouldNotBeSuffixed = classes()
            .that().areAnnotatedWith(Service.class)
            .or().resideInAPackage(ANY_APPLICATION_PACKAGE)
            .should().haveSimpleNameNotEndingWith(SERVICE)
            .andShould().haveSimpleNameNotEndingWith(SERVICE_IMPL);

    @ArchTest
    static final ArchRule jpaRepositoryImplementationsShouldBeSuffixed = classes()
            .that().areAssignableTo(JpaRepository.class)
            .or().areAssignableTo(CrudRepository.class)
            .or().areAssignableTo(PagingAndSortingRepository.class)
            .should().haveSimpleNameEndingWith(REPOSITORY);

    @ArchTest
    static final ArchRule controllersShouldBeSuffixed = classes()
            .that().areAnnotatedWith(RestController.class)
            .or().areAnnotatedWith(Controller.class)
            .should().haveSimpleNameEndingWith(CONTROLLER);

    @ArchTest
    static final ArchRule configShouldBeSuffixed = classes()
            .that().areAnnotatedWith(Configuration.class)
            .and().areNotAnnotatedWith(ConfigurationProperties.class)
            .should().haveSimpleNameEndingWith(CONFIG);

//    @ArchTest
//    static final ArchRule configPropertiesShouldBeSuffixed = classes()
//            .that().areAnnotatedWith(ConfigurationProperties.class)
//            .should().haveSimpleNameEndingWith(CONFIG_PROPERTIES);

    @ArchTest
    static final ArchRule domainEntitiesShouldNotBeSuffixed = classes()
            .that().areNotAnnotatedWith(Entity.class)
            .should().haveSimpleNameNotEndingWith(ENTITY)
            .andShould().haveSimpleNameNotEndingWith(MODEL);
}
