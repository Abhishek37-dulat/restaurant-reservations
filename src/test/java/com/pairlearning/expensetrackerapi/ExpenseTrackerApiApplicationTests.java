package com.pairlearning.expensetrackerapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ExpenseTrackerApiApplicationTests {

	private Calculator c = new Calculator();
	@Test
	void contextLoads() {
	}

	@Test
	void testSum(){
		int expectedResult = 40;
		int results = c.doSum(12,13,15);

		assertThat(results).isEqualTo(expectedResult);
	}


}
