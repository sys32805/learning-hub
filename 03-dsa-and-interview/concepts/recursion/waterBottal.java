/**
 * waterBotal
 */
public class waterBottal {
  public static void main (String[] args){
    int numBottles = 15;
    int numExchange = 4;
    waterBottal ob = new waterBottal();
    ob.getResultOfBottals(numBottles , numExchange );
  }

  private void getResultOfBottals( int numBottles , int numExchange) {
    int result = numBottles + ( numBottles - 1) / (numExchange - 1);
    System.err.println(" The result is " + result);
  }
}