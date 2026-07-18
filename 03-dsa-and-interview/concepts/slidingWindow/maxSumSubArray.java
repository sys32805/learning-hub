package rohit.slidingWindow;

public class maxSumSubArray {
  public static void main(String[] args){
    int a[] = new int[] { -1, -2, -3, -4};
    /*
     * Here i am using sliding window tech or approch to get the max sum of subarray
     */
    int l = 0;
    int k = 3;
    int sum = 0;
    int len = a.length;
    int maxSum = 0;
    for (int i = 0; i < k; i++) {
      sum = sum + a[i];
    }
    maxSum = sum;
    int currentSum = 0;
    for(int j = k ; j < a.length ; j++){
      sum += a[j];
      sum -= a[j-k];
      maxSum = Math.max(maxSum, sum);
    }
    System.err.println( maxSum);
  }
}
