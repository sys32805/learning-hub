class BoyerMooreRepetitiveNumber {
  public static void main(String[] args) {
    int[] nums = new int[] { 2, 2, 2, 22, 22 }; // Example array
    findRepetitiveNumber(nums);
  }

  public static int findRepetitiveNumber(int[] nums) {
    int candidate = nums[0];
    int count = 1;

    for (int i = 1; i < nums.length; i++) {
      if (count == 0) {
        candidate = nums[i];
        count = 1;
      } else if (nums[i] == candidate) {
        count++;
      }
    }
    System.err.println(" Repetative number is " + candidate + " and count is " + count);
    return candidate;
  }
}

/*
 * This algorithum will work only if element frequence is present in array is n/2 time ( N is size of array)
 */