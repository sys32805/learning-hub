package rohit.datastructures.Arrays;

public class pascalTranglre {
  public static void main(String[] args){
    int n = 14;
    pascalTranglre ob  = new pascalTranglre();
    ob.genSequence(n);
  }

  private void genSequence(int n) {
    int i ,j = 0;
    for(i = 0 ; i < n ; i++){
      for( j = 0 ; j <= i ; j++){
        System.err.print(binomiyalCoeff(i , j)+ " ");
      }
      System.err.println();
    }
  }

  private int binomiyalCoeff(int n, int k) {

    return factorial(n) / (factorial(k) * factorial(n -k));
  }

  private int factorial(int n) {
    if( n == 0 || n == 1){
      return 1;
    }
    else{
      return n * factorial(n - 1);
    }
  }
}
