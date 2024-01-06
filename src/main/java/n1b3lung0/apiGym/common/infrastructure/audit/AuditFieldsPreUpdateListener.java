package n1b3lung0.apiGym.common.infrastructure.audit;

import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import java.time.LocalDateTime;

import static n1b3lung0.apiGym.common.infrastructure.audit.AuditFieldsPreInsertAndUpdateUtils.getAuditFieldsValue;
import static n1b3lung0.apiGym.common.infrastructure.audit.AuditFieldsPreInsertAndUpdateUtils.hasAuditFields;
import static n1b3lung0.apiGym.common.infrastructure.audit.AuditFieldsPreInsertAndUpdateUtils.setAuditFieldsToEntity;
import static n1b3lung0.apiGym.common.infrastructure.audit.AuditFieldsPreInsertAndUpdateUtils.setAuditFieldsToState;

public class AuditFieldsPreUpdateListener implements PreUpdateEventListener {
    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        Object entity = event.getEntity();
        Object[] state = event.getState();
        String[] propertyNames = event.getPersister().getPropertyNames();

        if (hasAuditFields(propertyNames)) {
            AuditFields oldAuditFields = getAuditFieldsValue(entity);
            AuditFields auditFields = new AuditFields(oldAuditFields.getCreatedAt(), oldAuditFields.getCreatedBy(), LocalDateTime.now(), "n1b3lung0");
            setAuditFieldsToEntity(entity, auditFields);
            setAuditFieldsToState(state, propertyNames, auditFields);
        }
        return false;
    }
}
