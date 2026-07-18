import java.util.Arrays;

/**
 * CountingSort
 */
public class CountingSort {

  public static void main(String[] args){
    CountingSort ob  = new CountingSort();
    int a[] = new int[]{4,1,6,3,7,3,8,1000};
    ob.sort(a);
  }

  /**
   * @param a
   */
  public void sort(int[] a) {
    int max = 0;
    for (int k : a) {
      if(k > max){
        max = k;
      }
    }

    int count[] = new int[max+1];

    for (int j : a) {
      count[j]++;
    }

    int index = 0;
    for(int h = 0; h <= max; h++) {
      while (count[h] > 0) {
        a[index++] = h;
        count[h]--;
      }
    }
    System.out.println(Arrays.toString(a));
  }
}
