package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ApiDocsTests extends BaseArchTest {

    @ArchTest
    static final ArchRule controllersShouldBeAnnotatedForDocs = classes()
            .that().areAnnotatedWith(RestController.class)
            .should().beAnnotatedWith(Tag.class);

    @ArchTest
    static final ArchRule endpointsShouldBeAnnotatedForDocs = methods()
            .that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
            .and().arePublic()
            .should().beAnnotatedWith(Operation.class);

    static final DescribedPredicate<JavaAnnotation<?>> anyApiResponseAnnotation =
            new DescribedPredicate<>("annotation is an ApiResponse annotation") {
                @Override
                public boolean test(JavaAnnotation<?> annotation) {
                    return annotation.getRawType().isAnnotatedWith(ApiResponse.class)
                            || annotation.getRawType().isAnnotatedWith(ApiResponses.class);
                }
            };
    @ArchTest
    static final ArchRule endpointsShouldDocumentApiResponse = methods()
            .that().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
            .and().arePublic()
            .should().beAnnotatedWith(ApiResponse.class)
            .orShould().beAnnotatedWith(ApiResponses.class)
            .orShould().beAnnotatedWith(anyApiResponseAnnotation);

    @ArchTest
    static final ArchRule requestsAndResponsesShouldBeAnnotatedForDocs = classes()
            .that().haveSimpleNameEndingWith(REQUEST)
            .or().haveSimpleNameEndingWith(RESPONSE)
            .and().resideOutsideOfPackage(ANY_INFRASTRUCTURE_PACKAGE)
            .should().beAnnotatedWith(Schema.class);

    @ArchTest
    static final ArchRule fieldsInRequestsAndResponsesShouldBeAnnotatedForDocs = fields()
            .that().areDeclaredInClassesThat().haveSimpleNameEndingWith(REQUEST)
            .or().areDeclaredInClassesThat().haveSimpleNameEndingWith(RESPONSE)
            .and().areDeclaredInClassesThat().resideOutsideOfPackage(ANY_INFRASTRUCTURE_PACKAGE)
            .should().beAnnotatedWith(Schema.class);
}
