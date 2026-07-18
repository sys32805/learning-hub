package learnIn28minutes.learn_spring_project.HelloWorld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

record Person(String name, int age) {};

@Configuration
public class HelloWorldConfigration {
	@Bean
	public String name() {
		return "rohit";
	}
	@Bean
	public int age() {
		return 10;
	}
	/**
	 * here primary is added because multiple beans are added and AnnotationcfigApplicationContext
	 * is not able to find hence we need to add @Primary annotation to tell bean which one to call
	 * @return
	 * There is another way to do it using qualifier("AnotherName)
	 * Qualifier("testAddress") This will behave as another bean name
	 */
	@Bean
	@Primary
	@Qualifier("testAddress")
	public Person person() {
		var person = new Person("rohit",26);
		return person;
	}
	@Bean
	public Person person2() {
		var person = new Person("rohit",26);
		return person;
	}
}
