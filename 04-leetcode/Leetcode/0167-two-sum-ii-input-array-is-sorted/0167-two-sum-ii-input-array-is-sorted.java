class Solution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer,Integer> sb = new HashMap<>();
        for(int i = 0 ; i < numbers.length ; i++){
            int remaining = target - numbers[i];
            if(sb.containsKey(remaining)){
                return new int[]{sb.get(remaining)+1 , i+1};
            }else{
                sb.put(numbers[i] , i);
            }   
        }
        return new int[]{};
    }
}