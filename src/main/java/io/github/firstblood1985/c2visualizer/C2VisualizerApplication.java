package io.github.firstblood1985.c2visualizer;

import io.github.firstblood1985.c2visualizer.dao.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SpringBootApplication
public class C2VisualizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(C2VisualizerApplication.class, args);
	}

	@Component
	class MyRunner implements CommandLineRunner {

		@Value("${message}")
		private String message;
		@Autowired
		private Environment environment;

		@Autowired
		private SiteUserRepository siteUserRepository;

		@Override
		public void run(String... args) throws Exception {
			System.out.println("Active profiles: " +
					Arrays.toString(environment.getActiveProfiles()));
			System.out.println("Message: " + message);

		}
	}
}
