package rohit.collection.collectionInterfaceBasedQuestion;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class frequenceOfNumbers {
  public static void main(String[] args) {
    int a[] = new int[]{ 2, 3, 2, 3, 5};
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int c : a){
      if(map.containsKey(c)){
       map.put(c, map.get(c)+1);
      } else {
        map.put(c, 1);
      }
    }

// System.err.println();
    for(int  i = 1 ; i <= a.length ; i++){
      if(map.containsKey(i)){
        System.err.print(map.get(i) + " ");
      }else{
        System.err.print(0 + " ");
      }
    }
  }
}
// a
// b