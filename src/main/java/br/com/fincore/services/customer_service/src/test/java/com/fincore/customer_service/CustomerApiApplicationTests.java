package br.com.fincore.services.customer_service.src.test.java.com.fincore.customer_service;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.junit.Assert.assertNotNull;

public class CustomerApiApplicationTests {

	@Test
    public void shouldBeConfiguredAsSpringBootApplication() throws ClassNotFoundException {
		Class<?> applicationClass = Class.forName("CustomerApiApplication");

		assertNotNull(applicationClass.getAnnotation(SpringBootApplication.class));
	}

}
