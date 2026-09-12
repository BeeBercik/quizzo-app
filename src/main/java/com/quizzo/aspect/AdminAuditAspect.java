package com.quizzo.aspect;

import com.quizzo.service.AdminAudit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AdminAuditAspect {

    @Pointcut("execution(* com.quizzo.service.AdminService.*User*(..)) && @annotation(adminAudit) && args(userId, adminId)")
    public void adminAuditOperation(AdminAudit adminAudit, Integer userId, Integer adminId) {
    }

    @AfterReturning(pointcut = "adminAuditOperation(adminAudit, userId, adminId)")
    public void logActionSuccess(AdminAudit adminAudit, Integer userId, Integer adminId) {
        log.info("Successful admin action. Admin - {} | Action - {} | User - {}", adminId, adminAudit.action(), userId);
    }
}
