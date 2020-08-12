package com.example.httpPoller;

import com.example.httpPoller.repository.EndPointRepository;
import com.example.httpPoller.repository.EndPointStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HttpPollerApplication {

	private static ConfigurableApplicationContext context;

	private static HttpPoller httpPoller;

	@Bean
	public HttpPoller poller() {
		httpPoller = new HttpPoller();
		return httpPoller;
	}

	public static void main(String[] args) {
		context = SpringApplication.run(HttpPollerApplication.class, args);

	}

	public static void restart() {
		ApplicationArguments args = context.getBean(ApplicationArguments.class);
		httpPoller.stopAndRemoveAllTasks();

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(HttpPollerApplication.class, args.getSourceArgs());
		});

		thread.setDaemon(false);
		thread.start();
	}

}
