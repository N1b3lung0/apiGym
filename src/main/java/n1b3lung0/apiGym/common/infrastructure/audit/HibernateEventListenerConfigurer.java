package n1b3lung0.apiGym.common.infrastructure.audit;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

@Component
public class HibernateEventListenerConfigurer {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        assert registry != null;
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(new AuditFieldsPreInsertListener());
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(new AuditFieldsPreUpdateListener());
    }
}
