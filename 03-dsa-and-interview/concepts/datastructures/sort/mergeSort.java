import java.util.Arrays;
public class mergeSort {
  public void sort(int startIndex,int midIndex,int lastIndex,int[] a){
    /**calculate the first array size then second array size move all elements
      in array copy them and and follow the quick sort algo.**/
      int i = 0;
      int j = 0;
      int n1 = midIndex-startIndex+1;
      int n2 = lastIndex - midIndex;
      int leftArray[]  = new int[n1];
      int rightArray[] = new int[n2];
      for(i = 0; i < n1 ; i++){
       leftArray[i] = a[startIndex+i];
      }
      for(j = 0; j < n2 ; j++){
       rightArray[j] = a[(midIndex+1) + j ];
      }
      // System.out.println(" LeftArray" + Arrays.toString(leftArray));
      // System.out.println(" rightArray" + Arrays.toString(leftArray));
      i = 0;
      j = 0;
      int k = startIndex;
      while (i < n1 && j < n2) {
        if( leftArray [ i ] <= rightArray[ j ] ){
          a[ k ] = leftArray[ i++ ];
        } else{
          a[ k ] = rightArray[ j++ ];
        }
        k++;
      }
      while( i < n1){
        a[ k] = leftArray[ i ];
        i++;
        k++;
      }
      while( j < n2){
        a[ k] = rightArray[ j ];
        k++;
        j++;
      }
      // System.out.println(Arrays.toString(a));
  }
    public void merge_sort(int startIndex , int lastIndex, int[] a){
      if(startIndex < lastIndex){
        int midIndex = startIndex+(lastIndex-startIndex)/2;
        merge_sort(startIndex, midIndex, a);
        merge_sort(midIndex+1, lastIndex, a);
        sort(startIndex,midIndex,lastIndex,a);
      }
  }
  public static void main(String[] args){
    int a[] = new int[]{5,1,6,3,10,9,3,1,6,3,45,8,9,3,1,0};
    mergeSort ob = new mergeSort();
    int startIndex = 0;
    int lastIndex  = a.length-1;
    ob.merge_sort(startIndex,lastIndex,a);
    System.out.println(Arrays.toString(a));
  }
}
