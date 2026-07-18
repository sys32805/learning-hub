import java.util.Arrays;

/*Solution is exact as taught in leacture
  Only work if arrays are sorted.
  step1: in a sigle while loop all based on conditional comparision all data
         should be inserted.
  Step2: Look forward for remaining elment through seperate loop.
*/
public class mergeArrays {

  public void merge(int a[] ,int b[]){
    int array_3[] = new int[a.length+b.length];
    int i=0,j=0,k=0;
    while( i < a.length && j < b.length ){
      if(a[i] < b[j]){
        array_3[k] = a[i++];
      } else {
        array_3[k] = b[j++];
      }
      k++;
    }
    while(i < a.length){
      array_3[k] = a[i++];
      k++;
    }
    while(j < b.length){
      array_3[k] = a[j++];
      k++;
    }
System.out.println(Arrays.toString(array_3));
  }
  public static void main(String[] args) {
    int array_1[] = { 1, 3, 5, 17, 19 ,11};
    int array_2[] = { 2, 4, 6, 8, 10 };
    mergeArrays m = new mergeArrays();
    m.merge(array_1 , array_2 );
  }
}
