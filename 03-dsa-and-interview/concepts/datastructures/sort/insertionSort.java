/*
From wikipedia
link:https://en.wikipedia.org/wiki/Insertion_sort#Algorithm
i ← 1
while i < length(A)
    x ← A[i]
    j ← i
    while j > 0 and A[j-1] > x
        A[j] ← A[j-1]
        j ← j - 1
    end while
    A[j] ← x[3]
    i ← i + 1
end while
 */
import java.util.Arrays;
//1,4,2,5,6 key = a[i]
//1,4,4,5,6 swap
//1,2,4,5,6 reinsert the key
public class insertionSort {
  public static void insertionSortFun(int[] a){
  int  i =0,j=0;
  while(i < a.length){
    int key = a[i];
    j = i;
    while(j > 0 && a[j-1] > key ){
      a[j] = a[j-1];
      j--;
    }
    a[j] = key;
    i++;
  }
  System.out.println(Arrays.toString(a));
  }
public static void main(String[] args){
  insertionSort m = new insertionSort();
  int a[] =  new int[]{1,4,2,5,6};
  m.insertionSortFun(a);
}
}


