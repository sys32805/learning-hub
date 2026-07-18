public class maximum_subarray {
  public int sub_array(int[] a){
    int i = 0;
    int j = a.length-1;
    int new_max  = 0;
    int current_sum = a[0];
    if(a.length == 1){
      return a[0];
    }
    for (i = 1; i <= j ; i++){
     current_sum =  current_sum < 0  ?  a[i] : current_sum + a[i];
      if(current_sum > new_max){
       new_max = current_sum;
      }
    }
    return new_max;
  }
  public static void main(String args[]){
    int a[] = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
    maximum_subarray ob = new maximum_subarray();
    int result = ob.sub_array(a);
    System.err.println("Maximum Value is "+ result);
  }
}
