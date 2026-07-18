/**
 * Fibonacci
 */
public class Fibonacci {
  public static void main(String args[]){
    Fibonacci ob = new Fibonacci();
    int limit = 9;
    ob.series();
    int result  = ob.seriesUsingRecursion(limit);
    System.err.println( " The result is " + result) ;
  }

  /*
 * using normal approch
 */
  private void series() {
    int previous = 0;
    int next = 1;
    int sum = 0;
    System.err.println();
    System.err.print(" The series -> " + 0);
    System.err.print(", " + 1);
    for(int i = 2 ; i <= 10 ; i++){
      sum = previous + next;
      next = previous;
      previous = sum;
      sum = next + previous;
      System.err.print(", " + sum);
    }
    System.err.println();
    System.err.println();
  }
  /*
   * Lets try with the recursive approch
   */

  private int seriesUsingRecursion(int limit) {
    if(limit == 1){
      return 0;
    }
    if(limit == 2){
      return 1;
    } else {
      int temp = seriesUsingRecursion(limit-1) + seriesUsingRecursion(limit-1);
      return  temp;
    }
  }
}