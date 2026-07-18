import java.util.Arrays;

public class MinElement {
public void FindMinSortedElement(int[] m, int higest ){
  int i = 0;
  boolean flag = false ;
  boolean Infinite = true;
  Arrays.sort(m);
  /*
   * min and max logic are vica versa kind of same hence avaoided wrinting same code 2'ice
   */
  while(Infinite){
      ++i;
      if(higest < m[i]){
        higest = m[i];
        flag = true;
      }
      if(flag && (m.length-1) == i){
        System.out.println(higest);
        break;
      }

  }
}
public void FindMinUnSortedElement(int[] m, int higest ){
  int i = 0;
  int min = 0;
  Arrays.sort(m);
  /*
   * min and max logic are vica versa kind of same hence avaoided wrinting same code 2'ice
   */
  for (i = 1; i < m.length; i++) {
    if (m[i] < min) {
     min = m[i];
    }
  }
  System.out.println(min);
}
  public static void main(String[] args){
    int[] a = { 100,98,1,34,10,9,4,-1};
    int higest  = -9999;
    (new MinElement()).FindMinSortedElement(a,higest);
    (new MinElement()).FindMinUnSortedElement(a,higest);

  }
}
