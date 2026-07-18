class ThirdMax {
  public static void main(String[] nums) {
        int a[]  = new int[]{1,2,4};
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        for(int num : a ) {
          if( num > max1 ) {
              max3 = max2;
              max2 = max1;
              max1 = num;
          }else if( num > max2 && num != max1 && num != max3 ){
              max3 = max2;
              max2 = max1;
          } else if( num > max3 && num != max1 && num != max3 ) {
              max3 = max2;
          }
        }
        int result =  max3 != Long.MIN_VALUE ? (int) max3 : (int) max1;

        System.err.println(" The result is " + result);
    }
}