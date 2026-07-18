package rohit.datastructures.String;

import java.util.Arrays;

public class palindrom {
  public static void main(String[] args){
    String name  = "A man, a plan, a canal: Panama";
    String reversedStr = "";
    String filterted = "";
    char[] strArray = name.toCharArray();
    for(int i = strArray.length-1 ; i >= 0 ; i--){
    if (Character.isLetter(strArray[i])) {
      reversedStr += strArray[i];
    }
    }
    for (int i = 0; i < strArray.length ; i++) {
      if (Character.isLetter(strArray[i])) {
        filterted += strArray[i];
      }
    }

    if(filterted.toLowerCase().equals(reversedStr.toLowerCase())){
      System.err.println("Yes");
    } else{
      System.err.println("No");
    }
  }
}
/*
The approch is too lame here there is so much optimization possible.
*/