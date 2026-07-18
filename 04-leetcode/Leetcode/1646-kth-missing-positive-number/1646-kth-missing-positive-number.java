class Solution {
    public int findKthPositive(int[] arr, int k) {
        int len = arr.length;
        Set<Integer> sb = new HashSet<>();
        List<Integer> sc = new ArrayList<>();
        for(int  i = 0 ; i < len ;i++){
            sb.add(arr[i]);
        }
        for(int i = 1 ; i < arr[arr.length-1] ; i++){
            if( !sb.contains(i) ){
                sc.add(i);
            }
        }
        if(sc.isEmpty()){
            return arr[arr.length-1]+k;
        }else if(sc.size() >= k){
            return sc.get(k-1);
        }else if(sc.size() < k){
            return arr[arr.length-1] + k - sc.size();
        }
        return 0;
    }
}