package com.zz.aop;

import com.zz.KafkaProducer;
import com.zz.aop.anntation.KafkaSendLog;
import com.zz.security.domain.log.LogInfo;
import com.zz.vo.SendData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 日志逻辑处切面
 * @author wqy
 * @version 1.0
 * @date 2020/10/20 11:38
 */
@Aspect
@Component
public class KafkaSendLogAspect {

    @Autowired
    private KafkaProducer kafkaProducer;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation( com.zz.aop.anntation.KafkaSendLog)")
    public void kafkaSendLogs() {
    }

    /**
     * 正常执行完发送日志
     * @param joinPoint
     * @param kafkaSendLog
     */
    @AfterReturning("kafkaSendLogs() && @annotation(kafkaSendLog)")
    public void sendAfterReturning(JoinPoint joinPoint, KafkaSendLog kafkaSendLog){

        //获取所有参数
        Object[] objects = joinPoint.getArgs();
        //获取方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //创建日志对象
        LogInfo<Object[]> logInfo = new LogInfo<>(objects);
        //检查是否有无法序列化的对象
        int count = 0;
        for (Object o:logInfo.getParams()){
            if(o instanceof HttpServletRequest){
                logInfo.getParams()[count] = null;
                continue;
            }else if(o instanceof MultipartHttpServletRequest){
                logInfo.getParams()[count] = null;
                continue;
            }else if(o instanceof HttpServletResponse){
                //logInfo.setParams(null);
                logInfo.getParams()[count] = null;
                continue;
                //break ofs;
            }
            count = count + 1;
        }
        //设置调用的方法名
        logInfo.setFName(className+","+methodName);
        //发送给kafka
        kafkaProducer.sendMsg(new SendData<>(kafkaSendLog.topic().description,logInfo));
    }

    /**
     * 异常时候发送日志
     * @param joinPoint
     * @param kafkaSendLog
     */
    @AfterThrowing(value = "kafkaSendLogs() && @annotation(kafkaSendLog)",throwing = "exception")
    public void sendAfterThrowing(JoinPoint joinPoint, KafkaSendLog kafkaSendLog,Exception exception){

        //获取所有参数
        Object[] objects = joinPoint.getArgs();
        //获取方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //创建日志对象
        LogInfo<Object[]> logInfo = new LogInfo<>(objects);
        //检查是否有无法序列化的对象
        int count = 0;
        for (Object o:logInfo.getParams()){
            if(o instanceof HttpServletRequest){
                logInfo.getParams()[count] = null;
                continue;
            }else if(o instanceof MultipartHttpServletRequest){
                logInfo.getParams()[count] = null;
                continue;
            }else if(o instanceof HttpServletResponse){
                //logInfo.setParams(null);
                logInfo.getParams()[count] = null;
                continue;
                //break ofs;
            }
            count = count + 1;
        }
        //设置调用的方法名
        logInfo.setFName(className+","+methodName);
        //是否成功，默认成功，因为当前方法为异常后走的，所以为1
        logInfo.setIsSuccess(1L);
        //记录错误信息
        logInfo.setEx(exception.getMessage());
        //发送给kafka
        kafkaProducer.sendMsg(new SendData<>(kafkaSendLog.topic().description,logInfo));
    }
}
