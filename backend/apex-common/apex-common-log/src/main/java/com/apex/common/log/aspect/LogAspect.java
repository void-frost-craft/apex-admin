package com.apex.common.log.aspect;

import com.apex.common.core.utils.SecurityUtils;
import com.apex.common.core.utils.StringUtils;
import com.apex.common.log.annotation.Log;
import com.apex.common.log.enums.BusinessType;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * 操作日志记录处理
 *
 * @author apex
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 处理完请求后执行
     */
    @Around("@annotation(controllerLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, Log controllerLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        handleLog(joinPoint, controllerLog, null, result, endTime - startTime);
        return result;
    }

    /**
     * 拦截异常操作
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null, 0L);
    }

    /**
     * 处理日志
     */
    private void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object result, long costTime) {
        try {
            // 获取当前请求
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return;
            }

            HttpServletRequest request = attributes.getRequest();

            // 获取操作信息
            String title = controllerLog.title();
            BusinessType businessType = controllerLog.businessType();

            // 获取方法信息
            String methodName = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";

            // 获取请求信息
            String requestMethod = request.getMethod();
            String requestUrl = request.getRequestURI();
            String operIp = request.getRemoteAddr();
            String operName = SecurityUtils.getUsername();

            // 构建日志信息
            StringBuilder logInfo = new StringBuilder();
            logInfo.append("操作模块: ").append(title).append("\n");
            logInfo.append("业务类型: ").append(businessType).append("\n");
            logInfo.append("请求方式: ").append(requestMethod).append("\n");
            logInfo.append("请求URL: ").append(requestUrl).append("\n");
            logInfo.append("方法名称: ").append(methodName).append("\n");
            logInfo.append("操作人员: ").append(operName).append("\n");
            logInfo.append("操作IP: ").append(operIp).append("\n");
            logInfo.append("耗时: ").append(costTime).append("ms");

            if (e != null) {
                logInfo.append("\n").append("异常信息: ").append(e.getMessage());
                log.error(logInfo.toString(), e);
            } else {
                log.info(logInfo.toString());
            }

            // TODO: 保存操作日志到数据库
            // 异步保存日志
            // asyncService.saveOperLog(operLog);

        } catch (Exception exp) {
            log.error("异常信息: {}", exp.getMessage(), exp);
        }
    }
}
