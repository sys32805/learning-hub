public class print {
  public static void main(String args[]) {
    print a = new print();
    int num = 2;
    int multiplyer = 1;
    // a.printTable(num , multiplyer);
    int n = 1;
    a.printOneTOTen( n );
  }

  private int printOneTOTen(int n) {
    if( n == 11 ){
      return 1;
    } else {
      printOneTOTen(n+1);
      System.err.println(" The no is " + n);
    }
    return 1;
  }

  // private void printTable(int num, int multiplyer) {
  //   if(multiplyer > 10){
  //     return;
  //   } else{
  //     System.err.println(num * multiplyer);
  //     multiplyer++;
  //     printTable(num, multiplyer);
  //   }
  // }

}
