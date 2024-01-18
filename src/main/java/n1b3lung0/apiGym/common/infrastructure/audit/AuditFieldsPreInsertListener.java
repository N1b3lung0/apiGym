package n1b3lung0.apiGym.common.infrastructure.audit;

import n1b3lung0.apiGym.common.domain.audit.AuditFields;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import java.time.LocalDateTime;

import static n1b3lung0.apiGym.common.infrastructure.audit.AuditFieldsPreInsertAndUpdateUtils.hasAuditFields;
import static n1b3lung0.apiGym.common.infrastructure.audit.AuditFieldsPreInsertAndUpdateUtils.setAuditFieldsToEntity;
import static n1b3lung0.apiGym.common.infrastructure.audit.AuditFieldsPreInsertAndUpdateUtils.setAuditFieldsToState;

public class AuditFieldsPreInsertListener implements PreInsertEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent event) {

        Object entity = event.getEntity();
        Object[] state = event.getState();
        String[] propertyNames = event.getPersister().getPropertyNames();

        if (hasAuditFields(propertyNames)) {
            AuditFields auditFields = new AuditFields(LocalDateTime.now(), "n1b3lung0", null, null);
            setAuditFieldsToEntity(entity, auditFields);
            setAuditFieldsToState(state, propertyNames, auditFields);
        }

        return false;
    }
}
