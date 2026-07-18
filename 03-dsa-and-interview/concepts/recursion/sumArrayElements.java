public class sumArrayElements {
  public static void main(String args[]){
    sumArrayElements ob = new sumArrayElements();
    int[] a = new int[]{ 1,2,3,4,5,6,7,8 };
    ob.sumOfArrayElements(a , 0 );
  }

  private int sumOfArrayElements(int[] a , int index) {

    int val = 0;
    int temp = 0 ;
    if(index == a.length) {
      return 0 ;
    } else {
       temp = temp + sumOfArrayElements( a, index + 1 );
      System.err.println(" The number is " + a[index]);
    }
    return val;
  }
}
