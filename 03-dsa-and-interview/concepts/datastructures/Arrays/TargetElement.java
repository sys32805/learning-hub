// target element in un sorted array
import java.util.*;
class TargetElement {
  public static void print(String name){
    System.out.println("hellow world");
  }
  public void Findtarget(int [] array,int target){
    /*Below is the liner approch*/
    /*
     * if array is not sorted optimal approch is not possible hence liner search is the only way to get target element
     * unsorted != O(1) for understanding purpose
     */
    // for(int i= 0; i < array.length; i++){
    //   if(target == array[i])
    //   System.out.println(" Target Element " +array[i] +" Fount at the index " +(i+1));
    // }
    /*Optimal appproch*/
    /*
     * in order to sort a sorted array in non liner time binary Search can be used
     */
    int leftestIndex = 0;
    Arrays.sort(array);
    System.out.println("here is the sorted array"+ Arrays.toString(array));
    int RighttestIndex = (array.length) - 1 ;
    while(leftestIndex < RighttestIndex){
      int mid  = (leftestIndex + RighttestIndex) / 2;
      if(array[mid] > target){
        mid  = mid - 1;
        RighttestIndex--;
      } else {
        mid  = mid + 1;
        leftestIndex++;
      }
      if(array[mid] == target){
        System.out.println("Found At index " + (mid+1));
        break;
      }
    }


  }
  public static void main(String[] args){
    // print("rohit");
    int[] a = {10,1,5,11,24,13,2,19};
    int targetItem = 13;
    TargetElement m = new TargetElement();
    m.Findtarget(a,targetItem);
  }
}

