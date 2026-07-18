/**
 * Single_Number_136
 */
public class Single_Number_136 {
  public static void main(String rohit[]){
      int nums[] = new int[]{2,2,3,3,3,10,0};
      int maxNumber= maxNumber(nums);
      int frq[] = new int[ maxNumber + 1];
      int i;
      for(i = 0;i < nums.length ; i++){
          frq[nums[i]]++;
      }
      for (int k = 0; k <= maxNumber; k++) {
        if (frq[k] > 0) {
          System.out.println(k + "\t" + frq[k]);
        }
      }
    }

    public static int maxNumber(int[]  nums){
      int k = 0;
      int max = -1;
      for ( k = 0; k < nums.length ;k++ ){
          if( max < nums[k]) {
              max = nums[k];
          }
      }
      return max;
    }
}