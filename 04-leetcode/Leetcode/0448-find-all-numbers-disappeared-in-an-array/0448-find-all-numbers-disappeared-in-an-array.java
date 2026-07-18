class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> sb = new HashSet<>();
        for(int  i : nums){
            sb.add(i);
        }
        List<Integer> ar = new ArrayList<>();
        for(int j  = 1 ; j <= nums.length ; j++){
            if(!sb.contains(j)){
                ar.add(j);
            }
        }
        return ar;
    }
}