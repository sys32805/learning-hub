package rohit.slidingWindow;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
  public static String longestSubstringWithoutRepeatingChars(String s) {
    int start = 0;
    int maxlength = 0;
    int end = 0;
    int right = 0;
    if (s == null || s.length() == 0) {
      return null;
    }
    HashMap<Character, Integer> ob = new HashMap<>();
    for (right = 0; right < s.length(); right++) {

      char i = s.charAt(right);

      if (ob.containsKey(i) && ob.get(i) >= start) {
        start = ob.get(i) + 1;
      }

      ob.put(i, right);

      if (right - start + 1 > maxlength) {
        maxlength = right - start + 1;
        end = right + 1;
      }
    }
    return s.substring(end - maxlength, end);
  }

  public static void main(String[] args) {
    String input1 = "abcdecbb";
    String input2 = "bbbbb";
    String input3 = "pwwkew";

    System.out.println("Longest substring without repeating characters in '" + input1 + "': "
        + longestSubstringWithoutRepeatingChars(input1));
    System.out.println("Longest substring without repeating characters in '" +
    input2 + "': "
    + longestSubstringWithoutRepeatingChars(input2));
    System.out.println("Longest substring without repeating characters in '" +
    input3 + "': "
    + longestSubstringWithoutRepeatingChars(input3));
  }
}
