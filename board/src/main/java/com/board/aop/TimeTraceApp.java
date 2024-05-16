package com.board.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect // 관점 지향 프로그래밍 [AOP(Aspect-Oriented Programming)]에서 사용되는 어노테이션
@Slf4j
// 모든 작업에 대한 시간을 측정해줌
public class TimeTraceApp {
    // 대상메소드 선택 : CommentService#create()
    // @Pointcut("execution(* com.example.firstproject.service.CommentService.create(..))")
    
    // Around 앞뒤로 실행하겠다는 뜻
    @Around("execution(* com.board..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start=System.currentTimeMillis();

        System.out.println("START(시작) : " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        }finally {
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;

            System.out.println("END(종료) : " + joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}