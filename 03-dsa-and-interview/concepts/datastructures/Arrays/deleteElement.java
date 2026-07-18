/*
 * Delete Target element in unsorted array
 */
import java.util.Arrays;
public class deleteElement {
  public void deleteTargetElement(int[] a,int target){
    int size = a.length;
    int i = 0;
    int j = 0;
    boolean flag = false;
    System.out.println("Before->" +Arrays.toString(a));
    while(a.length > 0){
      i++;
      if(a[i] == target){
        flag = true;
        break;
      }
    }
    if(flag){
      for( j = i; j < size-1 ; j++){
        a[j] = a[i+1];
      }
      //after deleting element and traversing all data set last element as zero
      a[size-1] = 0;
    }
    System.out.println("After->" +Arrays.toString(a));
  }
  public static void main(String[] args) {
    int a[] = {1,4,2,7,8,9};
    int target = 8;
    deleteElement m = new deleteElement();
    m.deleteTargetElement(a,target);
  }
}
