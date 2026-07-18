package learnIn28minutes.learn_spring_project.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MarioGame implements GameConsole{
	public void up() {
		System.out.println(" Jump ");
	}
	public void down() {
		System.out.println(" sit ");
	}
	public void left() {
		System.out.println(" move left ");
	}
	public void right() {
		System.out.println(" move right ");
	}
}
