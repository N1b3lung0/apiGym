package n1b3lung0.apiGym.common.infrastructure.audit;

import n1b3lung0.apiGym.common.BaseIntegrationTest;
import n1b3lung0.apiGym.exercise.mother.ExerciseMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuditFieldsPreInsertListenerTests extends BaseIntegrationTest {

    private String username;

    @BeforeEach
    private void setUp() {
        username = "n1b3lung0";
    }

    @Test
    void shouldAuditEntityBeforeInsert() {

        var exercise = ExerciseMother.random().id(null).build();

        em.persist(exercise);
        em.flush();

        assertNotNull(exercise.getAuditFields().getCreatedAt());
        assertEquals(username, exercise.getAuditFields().getCreatedBy());
        assertNull(exercise.getAuditFields().getUpdatedAt());
        assertNull(exercise.getAuditFields().getUpdatedBy());
    }
}
