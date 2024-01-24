package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ConfigTests extends BaseArchTest {

    @ArchTest
    static final ArchRule configShouldBeInTheRightPackage = classes()
            .that().haveSimpleNameEndingWith(CONFIG)
            .should().resideInAPackage(CONFIG_PACKAGE);
}
