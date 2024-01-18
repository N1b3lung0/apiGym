package n1b3lung0.apiGym.common.infrastructure.audit;

import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.exercise.mother.ExerciseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuditFieldsPreUpdateListenerTests extends BaseIntegrationTest {

    private String username;

    @BeforeEach
    void setUp() {
        username = "n1b3lung0";
    }

    @Test
    void shouldAuditEntityBeforeUpdate() {

        var exercise = ExerciseMother.random().id(null).build();
        em.persist(exercise);

        var updatedExercise = exercise.withDescription("Description updated");
        em.merge(updatedExercise);
        em.flush();

        assertNotNull(exercise.getAuditFields().getUpdatedAt());
        assertEquals(username, exercise.getAuditFields().getUpdatedBy());
    }
}
