package pl.codeleak.demos.sbt.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.listener.AbstractAuditListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Replaces the default org.springframework.boot.actuate.audit.listener.AuditListener.
 */
@Component
public class AuditEventListener extends AbstractAuditListener {

    private static final Logger LOG = LoggerFactory.getLogger(AuditEventListener.class);

    @Resource
    private AuditEventRepository auditEventRepository;

//    public AuditEventListener(AuditEventRepository auditEventRepository) {
//        this.auditEventRepository = auditEventRepository;
//    }

    @Override
    protected void onAuditEvent(AuditEvent event) {

        LOG.info("On audit event: timestamp: {}, principal: {}, type: {}, data: {}",
                event.getTimestamp(),
                event.getPrincipal(),
                event.getType(),
                event.getData()
        );

        auditEventRepository.add(event);
    }



}
