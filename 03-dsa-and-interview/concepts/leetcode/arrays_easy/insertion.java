public class insertion {
  public int searchInsert(int[] nums, int target) {
    int index = 0;
    int i = 0;
    int flag = 0;
    int counter = 0;
    int loop = 0;
    if (nums.length > 1) {
      for( i = 0 ; i < nums.length ; i++ ){
        loop++;
        if (nums[i] == target) {
          index = i;
          flag = 1;
          break;
        } else if (nums[i] != target && nums[i] > target) {
          index = i;
          break;
        }
        counter = i;
      }
      if (index == 0 && nums.length == loop) {
        return i;
      } else {
        return flag == 1 ? index : index;
      }
    } else if(nums.length == 1){
      if(nums[i] == target && nums[i] > target){
        index = i;
      } else if(nums[i] != target && nums[i] < target){
        index = 1;
      } else if (nums[i] != target && nums[i] > target) {
        index = 0;
      }
    }
    return index;
  }
  public static void main(String[] args) {
    insertion m = new insertion();
    int nums[] ={1,3,4,3,5};
    int target =5 ;
    int ans = 0;
    // give array is sorted
    ans = m.searchInsert(nums, target);
    System.out.print(ans);
  }
}
