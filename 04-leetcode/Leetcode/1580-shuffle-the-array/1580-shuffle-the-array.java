class Solution {
    public int[] shuffle(int[] nums, int n) {
        int  j = 0;
        int index = 0;
        int a[] = new int[nums.length];
        while(j!= n){
           a[index++] = nums[j];
           a[index++] = nums[j+n]; 
            j++;
        }
        return a;
    }
}