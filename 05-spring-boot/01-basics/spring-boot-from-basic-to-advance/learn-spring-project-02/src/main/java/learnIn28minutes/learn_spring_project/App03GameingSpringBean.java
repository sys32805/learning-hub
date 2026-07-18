package learnIn28minutes.learn_spring_project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import learnIn28minutes.learn_spring_project.game.GameConsole;
import learnIn28minutes.learn_spring_project.game.GameRunner;
import learnIn28minutes.learn_spring_project.game.PacMan;

/**
 * Code by Rohit Yadav
 */
/**
 * so inorder to make bean from spring we need to anotate classes using component and then scan package of 
 * component using componentScan
 * Note -> component ->bean
 * Note -> componentscan(package namespace) -> get the bean og the package
 */	
@Configuration
@ComponentScan("learnIn28minutes.learn_spring_project.game")
public class App03GameingSpringBean {
	
	public static void main(String[] args) {
		try(var context = new AnnotationConfigApplicationContext(App03GameingSpringBean.class)){
		context.getBean(GameConsole.class).left();
		}
	}
}