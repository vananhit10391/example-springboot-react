package vananh.example.multitenant;

import vananh.example.common.config.AppProperties;
import vananh.example.multitenant.model.User;
import vananh.example.multitenant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MultitenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultitenantApplication.class, args);
	}

	@Autowired
	UserService userService;

	@PostConstruct
	public void register() {
		User user = new User();
		user.setName("admin");
		user.setEmail("admin@example.com");
		user.setPassword("admin");
		try {
			userService.save(user);
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}
}
