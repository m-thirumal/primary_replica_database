package in.thirumal.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import in.thirumal.config.DataSourceContextHolder;
import in.thirumal.config.DataSourceType;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ReadOnlyAspect {

    @Around("@annotation(ReadOnly)")
    public Object handleReadOnly(ProceedingJoinPoint joinPoint) throws Throwable {
    	System.out.println("ReadOnly..");
        try {
            System.out.println("Setting data source to REPLICA");
            DataSourceContextHolder.setDataSource(DataSourceType.REPLICA);
            return joinPoint.proceed();
        } finally {
          // Always clean up
            DataSourceContextHolder.clear(); 
        }
    }
    
}
