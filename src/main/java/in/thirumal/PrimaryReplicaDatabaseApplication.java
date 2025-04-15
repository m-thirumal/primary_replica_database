package in.thirumal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class PrimaryReplicaDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimaryReplicaDatabaseApplication.class, args);
	}

}
