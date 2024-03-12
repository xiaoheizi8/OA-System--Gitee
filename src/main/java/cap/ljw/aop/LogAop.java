//package cap.ljw.aop;
//
//import cap.ljw.annotation.Log;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
///**
// * @author Ljw
// * 2023.8.31 日志切面
// */
//@Aspect
//@Component
//public class LogAop {
//    @Pointcut("execution(* cap.ljw.controller.*.*(..))")
//    public void pointCut(){
//
//    }
//
//
//    @Around(value = "pointCut()")
//    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable{
//        // 获取注解的值
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Log logAnnotation = signature.getMethod().getAnnotation(Log.class);
//        String logMessage = logAnnotation.value();
//
//        // 输出日志信息到控制台
//        System.out.println("日志信息：" + logMessage);
//
//        // 执行原始方法
//        return joinPoint.proceed();
//    }
//}
////此aop已经取消,切入细粒度方法导致前端获取不到后端接口的数据