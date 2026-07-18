package learnIn28minutes.learn_spring_project;

import learnIn28minutes.learn_spring_project.game.GameRunner;
import learnIn28minutes.learn_spring_project.game.MarioGame;
import learnIn28minutes.learn_spring_project.game.PacMan;
/**
 * Code by Rohit Yadav
 */
public class AppGameBasicJava {
	public static void main(String[] args) {
		/**
		 * here Var is TypeInference
		 */
		var game = new MarioGame();
//		var game1 = new PacMan();
		/**
		 * here as we can see Game_runner is dependent on game hence her is dependency of game on game_runner
		 * lets use spring boot to manage dependency
		 * look for AppGameBasicJava.java which do magic of spring to manage the bean
		 */
		var gameRunner = new GameRunner(game);
		gameRunner.run();
	}
}

/**
 here Var is very helpfull lets have an example
 List<Integers> list = List.of(A,b,c,d);
 var a =  list = List.of(A,b,c,d);
 **/

/**
 * Note: this is tightly coupled hence is is not ideal way to do above code is loosly coupled which is able to do all task
 * 	var MarioGame = new MarioGame();
	var PacMan = new PacMan();
	var gameRunner = new GameRunner(MarioGame,PacMan);
	gameRunner.run();
	
	how to make a classes loosely coupled
	1->make an interface which has multiple method declation
	2->implement the functional classes which are in use 
	3->make master class which and initialize the class the filed injection of master class and run the seperate class
	multiple class would not run 1 at a time
 */
 