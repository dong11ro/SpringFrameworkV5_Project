package myspring.di.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Assertions.*; 을 하면
// Assertions의 클래스의 모든 static 메서드를 import 한다. Assertions.을 붙일 필요 없음.
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloBeanJunitTest {
	
	@BeforeEach
	void container() {
		// 1. Bean 0. 생성
		BeanFactory factory = new GenericXmlApplicationContext("classpath:spring-beans.xml");
	}
	
	@Test
	void hello() {
		
		//2. Hello Bean을 Container에게 요청하기 Bean의 id 기준으로 객체 요청. 
		Hello helloById = (Hello)factory.getBean("hello");
		// 2-1. Hello Bean을 Container에게 요청하기 Bean의 id와 type 기준으로 Bean 요청
		Hello helloByType = factory.getBean("hello", Hello.class);
		
		// 2개의 Hello Bean의 주소비교
		System.out.println(helloById == helloByType);
		//assertSame() 매서드 사용
		Assertions.assertSame(helloById, helloByType);
		
		//setName() Injection이 제대로 작동하는지 검증
		Assertions.assertEquals("Hello 스프링", helloByType.sayHello());
		
		//setPrinter() 메서드 검증
		helloByType.print();
		
		//3. StringPrinter Bean 요청
		Printer printer = factory.getBean("strPrinter", Printer.class); //StringPrinter로 굳이 안 줘도 됨. 캬 다형성 레전드
		
		// 값 비교  
		Assertions.assertEquals(printer.toString(), "Hello 스프링");
	}
}
