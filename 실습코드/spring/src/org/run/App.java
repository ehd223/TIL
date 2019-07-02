package org.run;

import org.frame.Biz;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		System.out.println("Spring Loaded\n===============================");
		Biz biz = (Biz) factory.getBean("userBiz");
		
		biz.register();
		factory.close();
		System.out.println("===============================\nSpring Ended");
	}

}
