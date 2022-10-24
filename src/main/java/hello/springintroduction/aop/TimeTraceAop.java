package hello.springintroduction.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //이걸 적어야 Aop로 사용가능
@Component// 해도 되고, bean에 등록해도 됨. 빈에 등록하는게 좋은 이유는 등록돼서 쓰이는게 보암.
public class TimeTraceAop {

    @Around("execution(* hello.springintroduction..*(..)))")//공통관심사항의 타겟팅(적어준 패키지 하위에 다 적용)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish-start;
            System.out.println("END: "+joinPoint.toString() +" "+timeMs+"ms");
        }
    }
}
