public class BinarySearch {
  public int binary_search(int[] a){
    int left = 0;
    int right = a.length-1;
    int target = 50;
    while(left <= right){
        int mid  = left + (right - left)/2;
        if (a[mid] < target){
            left  = mid + 1;
        } else if(a[mid] > target){
            right = mid - 1;
        } else {
          return mid;
        }
      }
      return -1;
    }
  public static void main(String[] args){
    BinarySearch ob  = new BinarySearch();
    int a[] = new int[]{1,11,12,15,25,27,22,41,50};
    int k =  ob.binary_search(a);
    System.out.println("result: " + k);
  }
}

