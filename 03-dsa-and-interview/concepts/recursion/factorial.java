/**
 * factorial
 */
public class factorial {

  public static void main(String args[]){
    int number = 5;
    int result = factorial(number);
    System.out.println(" The result is " + result);
  }

  private static int factorial(int number) {
    int fact = 1;
    int temp = 1;
    if( number == 0){
      return 1;
    } else {
      temp = factorial(number - 1);
      return number * temp;
      /*
      function can will go like 5 4 3 2 1 0 when number does to 0 if contain get
      satisfied then it goes to else condition and return 5*4*3*2*1
      */

    }
  }
}