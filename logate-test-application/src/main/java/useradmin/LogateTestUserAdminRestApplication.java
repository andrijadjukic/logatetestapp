package useradmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // omogucava Cachnig
public class LogateTestUserAdminRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogateTestUserAdminRestApplication.class, args);
	}
}
