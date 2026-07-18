import java.util.Arrays;

public class ZerosAndOnes {
  public void sort(int[] a){
    int count  = a.length-1;
    int temp = 0;
    while(count >= 0){
      if(a[count] == 0){
        temp++;
      }
      count--;
    }
    count  = a.length-1;
    int end = count - temp;
    while(count >= 0){
      if(end >= 0){
      a[count--] = 1;
      end--;
      }
      else{
      a[count--] = 0;
      }
    }
    System.out.println(Arrays.toString(a));
  }
public static void main(String[] args){
  ZerosAndOnes ob = new ZerosAndOnes();
  int a[] = new int[]{0,1,0,1,0,0,1,1,1};
  ob.sort(a);
  }
}
