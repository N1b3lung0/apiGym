package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ControllersTests extends BaseArchTest {

    @ArchTest
    static final ArchRule controllersShouldNotBeAccessedByAnyOtherLayer = classes()
            .that().resideInAPackage(ANY_REST_PACKAGE)
            .should().onlyBeAccessed().byAnyPackage(ANY_REST_PACKAGE);

    @ArchTest
    static final ArchRule controllersShouldResideInRestLayer = classes()
            .that().areAnnotatedWith(RestController.class)
            .or().areAnnotatedWith(Controller.class)
            .or().haveSimpleNameEndingWith(CONTROLLER)
            .should().resideInAPackage(ANY_REST_PACKAGE);

    @ArchTest
    static final ArchRule restControllersShouldReturnResponseEntities = methods()
            .that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
            .and().arePublic()
            .should().haveRawReturnType(ResponseEntity.class);
}
