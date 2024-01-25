package n1b3lung0.apiGym.arch;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaConstructorCall;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.MappedSuperclass;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import n1b3lung0.apiGym.common.domain.audit.NoAudit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class DomainTests extends BaseArchTest {

    @ArchTest
    static final ArchRule domainShouldNotDependOnAnyOtherLayer = noClasses()
            .that().resideInAPackage(ANY_DOMAIN_PACKAGE)
            .should().dependOnClassesThat().resideInAnyPackage(ANY_REST_PACKAGE, ANY_INFRASTRUCTURE_PACKAGE, ANY_APPLICATION_PACKAGE);

    @ArchTest
    static final ArchRule entitiesShouldResideInDomainLayer = classes()
            .that().areAnnotatedWith(Entity.class)
            .or().areAnnotatedWith(MappedSuperclass.class)
            .or().areAnnotatedWith(Embeddable.class)
            .should().resideInAPackage(ANY_DOMAIN_PACKAGE);

    @ArchTest
    static final ArchRule entitiesShouldBeAbstractOrFinal = classes()
            .that().areAnnotatedWith(Entity.class)
            .or().areAnnotatedWith(MappedSuperclass.class)
            .or().areAnnotatedWith(Embeddable.class)
            .should().haveModifier(JavaModifier.FINAL)
            .orShould().haveModifier(JavaModifier.ABSTRACT);

    @ArchTest
    static final ArchRule allFieldsInEntitiesShouldBePrivateAndFinal = fields()
            .that().areDeclaredInClassesThat().areAnnotatedWith(Entity.class)
            .or().areDeclaredInClassesThat().areAnnotatedWith(MappedSuperclass.class)
            .or().areDeclaredInClassesThat().areAnnotatedWith(Embeddable.class)
            .should().bePrivate()
            .andShould().beFinal();

    static final ArchCondition<JavaClass> haveANamedConstructor =
            new ArchCondition<>("have a named constructor") {
                @Override
                public void check(JavaClass item, ConditionEvents events) {
                    if (item.getMethods().stream().noneMatch(method -> method.getName().equals("create"))) {
                        String message = String.format("Domain model %s should have a named constructor", item.getName());
                        events.add(SimpleConditionEvent.violated(item, message));
                    }
                }
            };

    @ArchTest
    static final ArchRule domainEntitiesShouldHaveANamedConstructor = classes()
            .that().areAnnotatedWith(Entity.class)
            .and().areNotAnnotatedWith(Inheritance.class)
            .should(haveANamedConstructor);

    static final ArchCondition<JavaClass> haveAFieldOfTypeAuditFields =
            new ArchCondition<>("have a field of type AuditFields") {
                @Override
                public void check(JavaClass item, ConditionEvents events) {
                    if (!item.isAnnotatedWith(NoAudit.class) && item.getFields().stream().noneMatch(field -> field.getRawType().getName().equals(AuditFields.class.getName()))) {
                        String message = String.format(
                                "Domain model %s should have a field of type AuditFields", item.getName());
                        events.add(SimpleConditionEvent.violated(item, message));
                    }
                }
            };

    @ArchTest
    static final ArchRule domainEntitiesShouldHaveAFieldOfTypeAuditFields = classes()
            .that().areAnnotatedWith(Entity.class)
            .should(haveAFieldOfTypeAuditFields);

    static final DescribedPredicate<JavaConstructorCall> belongsToAnEntity =
            new DescribedPredicate<>("constructor call is from outside the target entity") {
                @Override
                public boolean test(JavaConstructorCall call) {
                    return !call.getOriginOwner().equals(call.getTargetOwner())
                            //&& !call.getTargetOwner().equals(call.getOriginOwner().getSuperclass())
                            && !call.getOriginOwner().getFullName().contains(BUILDER)
                            && call.getTargetOwner().isAnnotatedWith(Entity.class);
                }
            };

    @ArchTest
    static final ArchRule unnamedConstructorsMustNotBeCalledFromOutside = noClasses()
            .should().callConstructorWhere(belongsToAnEntity);

    @ArchTest
    static final ArchRule repositoriesInDomainLayerShouldBeInterfaces = classes()
            .that().resideInAPackage(ANY_DOMAIN_PACKAGE)
            .and().haveSimpleNameEndingWith(REPOSITORY)
            .should().beInterfaces();
}
