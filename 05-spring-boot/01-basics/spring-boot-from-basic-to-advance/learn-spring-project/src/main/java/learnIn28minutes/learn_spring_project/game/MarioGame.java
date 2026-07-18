package learnIn28minutes.learn_spring_project.game;

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
