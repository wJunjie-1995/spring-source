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
 * 2、要么全pointCut()，要么全 bean.aop.LogAspects.pointCutExecution()，不能混合用
 *
 * execution:粒度最小，可以有public? 返回类型 包名?方法名（参数） 异常?
 * within:粒度大 仅包名。（用于简化。因为有时仅需包名来指定目标对象 ，使用execution 参数过多显得复杂）
 * args:粒度大 仅参数类型
 * this: 代理对象类型
 * target：目标对象类型
 *
 * 切入点之间可以有逻辑运算 如 && || ！
 *
 * 切面实例模型（默认是单实例的）
 */
@Aspect
public class LogAspects {

    /** 抽取公共切入点表达式 (也可bean.aop.MathCalculator.*(..))*/
    @Pointcut("execution(public int bean.aop.MathCalculator.div(int, int))")
    public void pointCutExecution(){}

    @Pointcut("within(bean.aop.MathCalculator)")
    public void pointCutWithin(){}

    @Pointcut("args(String)")
    public void pointCutArgs(){}

    /** 在目标方法之前切入：切入表达式 */
    @Before("pointCutExecution()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("日志："+joinPoint.getSignature().getName()+"开始 参数："+ Arrays.asList(args));
    }

    @After("pointCutWithin()")
    public void logEnd(){
        System.out.println("日志：结束");
    }

    @AfterReturning(value = "pointCutArgs()||pointCutExecution()", returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result){
        System.out.println("日志："+joinPoint.getSignature().getName()+"成功返回{"+result+"}");
    }

    @AfterThrowing(value = "pointCutExecution()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        System.out.println("日志："+joinPoint.getSignature().getName()+"异常:{"+exception+"}");
    }
}
