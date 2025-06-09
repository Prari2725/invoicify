package Invoicify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class InvoicifyApplication {
//private static final int STRENGHT=12;
	public static void main(String[] args) {
		SpringApplication.run(InvoicifyApplication.class, args);
	}
//@Bean
//public BCryptPasswordEncoder passwordEncoder()
//{
//	return  new BCryptPasswordEncoder(STRENGHT);
//}
}

