package learnIn28minutes.learn_spring_project.HelloWorld;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import learnIn28minutes.learn_spring_project.game.GameRunner;
import learnIn28minutes.learn_spring_project.game.MarioGame;
import learnIn28minutes.learn_spring_project.game.PacMan;
/**
 * Code by Rohit Yadav
 */
public class App02HelloworldSpring {
	public static void main(String[] args) {
		try (//Launch spring context
		var context = new AnnotationConfigApplicationContext(HelloWorldConfigration.class)) {
			//config the the we want manage to do it we need to config configration class
			System.out.println(context.getBean("name"));
			System.out.println(context.getBean("person"));
			System.out.print(context.getBean(Person.class));
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
//java
	//jvm
		//spring
			//using springContext to manage configration

 