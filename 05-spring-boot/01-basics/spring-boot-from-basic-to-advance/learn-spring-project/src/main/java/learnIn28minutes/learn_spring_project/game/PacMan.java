package learnIn28minutes.learn_spring_project.game;

public class PacMan implements GameConsole{
	public void left() {
		System.out.println(" Moving left eating point ");
	}
	public void right() {
		System.out.println(" Moving right eating point ");
	}
	public void down() {
		System.out.println(" Plant Boom for the Invador ");
	}
	public void up() {
		System.out.println(" No action ");
	}
}
