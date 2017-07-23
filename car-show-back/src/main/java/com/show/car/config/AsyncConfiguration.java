package com.show.car.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

@Configuration
public class AsyncConfiguration implements AsyncConfigurer {

	private Logger logger = LoggerFactory.getLogger(AsyncConfiguration.class);

	private CarProperties carProperties ;

	public AsyncConfiguration(CarProperties carProperties) {
		this.carProperties = carProperties;
	}

	@Bean
	public Executor carAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(carProperties.getAsync().getThreadPoolSize());
		executor.setThreadNamePrefix("car-");
		executor.initialize();
		return executor;
	}

	@Override
	public Executor getAsyncExecutor() {
		return carAsyncExecutor();
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {

			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				logger.error("An error occurred while executing async operation: {}, params:{} ", method, params, ex);
			}
		};
	}

}
