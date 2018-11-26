package zx.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringApplication.class, args);
	}
}
