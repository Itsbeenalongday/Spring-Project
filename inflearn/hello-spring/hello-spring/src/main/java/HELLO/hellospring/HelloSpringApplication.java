package HELLO.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {

		SpringApplication.run(HelloSpringApplication.class, args); // class 넣으면, 스프링부트 애플리케이션 실행
		// tocamt 웹 서버 실행시키면서, 위에 스프링부트 웹 애플리케이션이 올라간다.
	}

}
