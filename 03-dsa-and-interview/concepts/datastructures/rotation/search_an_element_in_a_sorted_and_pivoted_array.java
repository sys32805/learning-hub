package rohit.datastructures.rotation;
import java.util.Arrays;

public class search_an_element_in_a_sorted_and_pivoted_array {
  public static void main(String[] args){
    int a[] = new int[]{5, 6, 7, 8, 9, 10, 1, 2, 3};
    int targetId = 8;
    search_an_element_in_a_sorted_and_pivoted_array ob = new search_an_element_in_a_sorted_and_pivoted_array();
    ob.search_element(a, targetId);
  }

  public static void search_element(int[] a, int targetId) {
    int startIndex = 0;
    int pivot = 0;
    int endIndex = a.length-1;
    while(startIndex < endIndex){
      int mid = startIndex + (endIndex - startIndex) / 2;
      if(a[mid] > a[endIndex]){
        startIndex = mid + 1;
      }else{
        endIndex = mid - 1;
      }
    }
    pivot = ++startIndex;
    startIndex = 0;
    endIndex = pivot;
    System.out.println(a[pivot]);
    if( pivot > 0 ){
      while(startIndex < endIndex){
        int mid = startIndex + (endIndex - startIndex) / 2;
        if(a[mid] > a[endIndex]){
          startIndex = mid + 1;
        }else{
          endIndex = mid - 1;
        }
        if(a[mid] == targetId){
          System.out.println(a[mid]);
          return;
        }
      }
    }
    return;
  }
}