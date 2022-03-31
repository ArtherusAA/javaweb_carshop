package com.artherus.carshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ContextConfiguration(classes = HibernateDatabaseConfig.class)
@TestPropertySource(locations="classpath:application.properties")
class CarshopApplicationTests {

	@Test
	void contextLoads() {
	}

}
