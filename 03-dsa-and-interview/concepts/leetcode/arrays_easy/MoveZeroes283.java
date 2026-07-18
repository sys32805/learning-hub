import java.util.Arrays;

public class MoveZeroes283 {
    public static void main(String args[]){
      int a[] = new int[]{0,1,2,3,4,0};
      int k = 0;
      int count  = 0;
      for(int i = 0;i < a.length ; i++ ){
        int temp = i;
        if(a[i] != 0){
          a[k] = a[i];
          k++;
        } else {
          count++;
        }
      }
      while (count != 0) {
        a[a.length-count] = 0;
        count--;
      }
    }
}
