package com.artherus.carshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = HibernateDatabaseConfig.class)
class CarshopApplicationTests {

	@Test
	void contextLoads() {
	}

}
