class Solution {
    public boolean containsDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        int count = 1;
        HashMap<Integer,Integer> hm = new HashMap<>();
        if(right >= 1 ){
            for(int i = 0; i <= right ;i++){
                if( !hm.containsKey(nums[i])){
                    hm.put(nums[i],i);
                }else{
                    count++;
                    if(count == 2){
                        return true;
                    }
                }
            }
        }else{
            return false;
        }
    return false;
    }
}