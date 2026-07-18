package rohit.algorithums;

import java.util.Arrays;

public class inDepthAlgo {
  public static void main(String[] args) {
    int[] a = {1,2,3,4,5,6};
    int len = a.length;
    int left = 0;
    int right = a.length-1;
    while (left < right) {
      int temp = a[left];
      a[left] = a[right];
      a[right] = temp;
      left++;
      right--;
    }
    System.out.println(Arrays.toString(a));
  }
}


class Solution {
  public String reverseVowels(String s) {
        char[] c = s.toCharArray();
        int left = 0;
        int right = c.length - 1;
        while (left <= right) {
            if(isVowel(c[left]) == false){
                left++;
            } else if(isVowel(c[right]) == false){
                right--;
            } else {
                char temp = c[left];
                c[left] = c[right];
                c[right] = temp;
                left++;
                right--;
            }
        }
        return new String(c);
    }

  public boolean isVowel(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I'
        || c == 'O' || c == 'U') {
      return true;
    }
    return false;
  }

}

class Solution {
  public String reverseStr(String s, int k) {
    char[] c = s.toCharArray();

    for (int i = 0; i < c.length; i += 2 * k) {
      int left = i;
      int right = Math.min(i + k - 1, c.length - 1); // Ensure right doesn't go out of bounds

      // Reverse the first k characters (or less if we reach the end)
      while (left < right) {
        char temp = c[left];
        c[left] = c[right];
        c[right] = temp;
        left++;
        right--;
      }
    }

    return new String(c);
  }
}