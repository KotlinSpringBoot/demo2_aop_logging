package com.easy.springboot.demo2_aop_logging.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*


@Component
@Aspect
class LogAspect {
    private val LOG = LoggerFactory.getLogger(LogAspect::class.java)

    @Pointcut("execution(public * com.easy.springboot.demo2_aop_logging.controller.*.*(..))") //.*.*代表所有子目录下的所有方法，最后括号里(..)的两个..代表所有参数
    fun logPointCut() {
    }

    @Before("logPointCut()")
    @Throws(Throwable::class)
    fun doBefore(joinPoint: JoinPoint) {
        // 接收到请求，记录请求内容
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request

        // 记录下请求内容
        LOG.info("请求地址 : " + request.requestURL.toString())
        LOG.info("HTTP METHOD : " + request.method)
        LOG.info("IP : " + request.remoteAddr)
        LOG.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName())
        LOG.info("参数 : " + Arrays.toString(joinPoint.getArgs()))

    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")// returning的值和doAfterReturning的参数名一致
    @Throws(Throwable::class)
    fun doAfterReturning(ret: Any) {
        // 处理完请求，返回内容
        LOG.info("返回值 : " + ret)
    }

    @Around("logPointCut()")
    @Throws(Throwable::class)
    fun doAround(pjp: ProceedingJoinPoint): Any {
        val startTime = System.currentTimeMillis()
        val ob = pjp.proceed()// ob 为方法的返回值
        LOG.info("耗时 : " + (System.currentTimeMillis() - startTime))
        return ob
    }

}