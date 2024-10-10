package com.bhawna.SpringBoot.Testing.TestingApplication;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class TestingApplicationTests {



	@BeforeEach
	void setUp(){
		log.info("Starting the test cases");
	}


	@BeforeAll
	static void setUpOnce(){
		log.info("Set up Once...");
	}

	@AfterEach
	void tearDown(){
		log.info("Tearing down the test cases");
	}

	@AfterAll
	static void tearDownOnce(){
		log.info("Tearing down all...");
	}


	@Test
	//@Disabled
	void contextLoads() {
	}

	@Test
	@DisplayName("Test Case 1")
	void testNumberOne(){

		int a = 3;
		int b = 0;

		int result = addTwoNumbers(a, b);

//		Assertions.assertEquals(8, result);
		assertThat(result).isEqualTo(8)
				.isCloseTo(9, Offset.offset(1));

		assertThat("Apple")
				.isEqualTo("Apple")
				.startsWith("App")
				.endsWith("le")
				.hasSize(5);
	}

	@Test
	void testDivideTwoNumbers_whenDenominatorIsZero_Then_itThrowArithmeticException(){
		log.info("Test two is running");

		int a = 3;
		int b = 0;

//		double result = divideTwoNumbers(a, b);
		assertThatThrownBy(() -> divideTwoNumbers(a,b))
				.isInstanceOf(ArithmeticException.class);

	}

	int addTwoNumbers(int a, int b){
		return a + b;
	}

	double divideTwoNumbers(int a , int b){
		try {
			return  a/b;
		}catch(ArithmeticException e){
			log.error("Arithmetic Exception occurred: " + e.getMessage());
			throw new ArithmeticException(e.getMessage());
		}
	}

}
