package bean.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author galileo
 * @date 2019/8/20 9:02
 *
 * 切面类
 * 坑：
 * 1、JoinPoint joinPoint必须是第一个参数
 * 2、要么全pointCut()，要么全 bean.aop.LogAspects.pointCut()，不能混合用
 */
@Aspect
public class LogAspects {

    /** 抽取公共切入点表达式 (也可bean.aop.MathCalculator.*(..))*/
    @Pointcut("execution(public int bean.aop.MathCalculator.div(int, int))")
    public void pointCut(){}

    /** 在目标方法之前切入：切入表达式 */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("日志："+joinPoint.getSignature().getName()+"开始 参数："+ Arrays.asList(args));
    }

    @After("pointCut()")
    public void logEnd(){
        System.out.println("日志：结束");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result){
        System.out.println("日志："+joinPoint.getSignature().getName()+"成功返回{"+result+"}");
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        System.out.println("日志："+joinPoint.getSignature().getName()+"异常:{"+exception+"}");
    }
}
