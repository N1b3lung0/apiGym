package n1b3lung0.apiGym.common.infrastructure.audit;

import lombok.extern.slf4j.Slf4j;
import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

@Slf4j
final class AuditFieldsPreInsertAndUpdateUtils {

    private static final String AUDIT_FIELDS = "auditFields";

    static void setAuditFieldsToState(Object[] currentState, String[] propertyNames, Object value) {

        int index = ArrayUtils.indexOf(propertyNames, AUDIT_FIELDS);
        if (index >= 0) {
            currentState[index] = value;
        }
    }

    static void setAuditFieldsToEntity(Object entity, AuditFields value) {

        try {
            Field auditFields = getAuditFields(entity);
            auditFields.setAccessible(Boolean.TRUE);
            auditFields.set(entity, value);
        } catch (IllegalAccessException e) {
            log.error("Value " + value + " could not be set for audit fields in class " + entity.getClass().getName());
        }
    }

    static AuditFields getAuditFieldsValue(Object entity) {

        AuditFields auditFieldsValue = null;

        try {
            Field auditFields = getAuditFields(entity);
            auditFields.setAccessible(Boolean.TRUE);
            auditFieldsValue = (AuditFields) auditFields.get(entity);
        } catch (IllegalAccessException iae) {
            log.error("Audit fields value could not be obtained for class " + entity.getClass().getName());
        }

        return auditFieldsValue;
    }

    private static Field getAuditFields(Object entity) {

        Field field = null;

        try {
            field = entity.getClass().getDeclaredField(AUDIT_FIELDS);
        } catch (NoSuchFieldException e) {
            log.error("Audit fields could not be found in class " + entity.getClass().getName());
        }

        return field;
    }

    static boolean hasAuditFields(String[] propertyNames) {

        return Arrays.stream(propertyNames).anyMatch(name -> StringUtils.equals(name, AUDIT_FIELDS));
    }
}
