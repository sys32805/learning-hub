package learnIn28minutes.learn_spring_project.JavaVsBean;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaVsBean {
	/**
	 * Pojo
	 * A pojo is anything which has varible and methods
	 * example
	 */
	class POJO{
		private int A;
		private int B;
		public int sum(int a, int b) {
			return a+b;
		}
		@Override
		public String toString() {
			return "POJO [A=" + A + ", B=" + B + "]";
		}
	}
	/**
	 * This is EJB -> ENTERPROSE JAVA BEANS
	 * DEF
	 * 	ANYTHING IS JAVA BEAN IF IT HAS FIELDS ANS GETTER AND SETTER ANS CLASS MUST IMPLEMTNS
	 * 	SERIALIZABLE
	 */
	class Bean implements Serializable{
		private int a;
		private int b;
		public int getA() {
			return a;
		}
		public void setA(int a) {
			this.a = a;
		}
		public int getB() {
			return b;
		}
		public Bean(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		public void setB(int b) {
			this.b = b;
		}
		@Override
		public String toString() {
			return "Bean [a=" + a + ", b=" + b + "]";
		}
		
	}
	public static void main(String[] args) {
		var context  = new AnnotationConfigApplicationContext();
		/**
		 * This is package base scanner if need total bean 
		 * @SpringBootApplication(scanBasePackages = "com.example")
		 */
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		
		
	}
	
}
