package rohit.datastructures.rotation;
public class find_element_in_rotated_and_duplicate_element {
  /*
   * Code to find the pivot element.
   */
  public int find_pivot(int[] a , int startIndex, int endIndex, int target) {
    while(startIndex < endIndex){
      int mid = startIndex + (endIndex - startIndex)/2;
      if(a[mid] > a[endIndex]){
        startIndex = mid + 1;
      } else if(a[mid] < a[endIndex]) {
        endIndex = mid;
      } else {
        /*
         * This part used for preventing the Duplicacy
         */
        endIndex = endIndex - 1;
      }
    }
    return startIndex;
  }
  public String binary_search(int[] a , int startIndex, int endIndex, int target) {
    while(startIndex < endIndex){
      int mid = startIndex + (endIndex - startIndex)/2;
      /*
       * Always this part of array is going to be sorted hence end = mid--;
       */
      if(a[mid] > target){
        endIndex = mid-1;
        /*
         * 3,4,5,6,10
         * Target is 6 hence moving <-
         */
      } else if(a[mid] < target) {
        startIndex = mid + 1;
      }
      if(a[mid] == target)
        return  "Found element " + a[mid] + " at index " + mid;
    }
    return null;
  }
  public static void main(String args[]){
    int a[] = new int[]{5, 6 , 6 , 7 , 7, 8, 9, 10, 1, 2, 3, 3};
    int startIndex = 0;
    int endIndex = a.length-1;
    int target = 2;

   find_element_in_rotated_and_duplicate_element ob = new find_element_in_rotated_and_duplicate_element();
   int pivot = ob.find_pivot(a, startIndex, endIndex, target);
   System.err.println(pivot);
   if(a[pivot] > target){
   String val = ob.binary_search(a, startIndex, endIndex, target);
   System.err.println(val);
   }else{
   String val = ob.binary_search(a, pivot-1, endIndex, target);
   System.err.println(val);
   }
  }
}
