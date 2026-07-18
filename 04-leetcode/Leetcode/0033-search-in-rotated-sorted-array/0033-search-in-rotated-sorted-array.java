class Solution {
    public int search(int[] nums, int target) {
        int left = 0 ;
        int right = nums.length;
        int  i = 0;
        //first find max to get pivot
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(; i < nums.length ; i++){
            // heap.add(nums[i]);
            // if(heap.size() > 1){
            //     heap.poll();
            // }
            if(nums[i] == target){
                return i;
            }
        }
        return -1;
    }
}