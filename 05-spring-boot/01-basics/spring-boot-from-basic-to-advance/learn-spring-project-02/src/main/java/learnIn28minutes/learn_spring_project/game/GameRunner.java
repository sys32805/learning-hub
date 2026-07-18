package learnIn28minutes.learn_spring_project.game;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class GameRunner {
	GameConsole gameconole;
	public GameRunner(GameConsole gameconole){
		this.gameconole = gameconole;
	}
	public void run() {
		System.out.println(" Running Game: " + gameconole);	
		gameconole.down();
	}
}