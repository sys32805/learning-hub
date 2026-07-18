package rohit.interview;

import java.util.HashMap;
import java.util.Map;

public class firstRepetingChar {
  public static void main(String[] args) {
    String s = "nbbwcn";
    int  i =0;
    int  j =0;
    HashMap<Character,Integer> sb = new HashMap<>();
    for( i = 0 ; i < s.length() ; i++){
      char c = s.charAt(i);
      if(!sb.containsKey(c) ){
        sb.put(c, sb.getOrDefault(c,0)+1);
      }else{
        System.out.println(c);
        return;
      }
    }
    //  for (Map.Entry<Character, Integer> entry : sb.entrySet()) {
    //    Integer value = entry.getValue();
    //    if(value > 1){
    //     System.out.println(entry.getKey());
    //     return;
    //    }
    //  }

  }
}