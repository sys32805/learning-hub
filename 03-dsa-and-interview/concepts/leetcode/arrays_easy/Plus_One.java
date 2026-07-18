/*
 You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order.
The large integer does not contain any leading 0's.
Increment the large integer by one and return the resulting array of digits.
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
 */
import java.util.Arrays;
public class Plus_One {
  public int[] plusOne(int[] digits) {
    int i = 0;
    int a[] = new int[digits.length + 1];
    for (i = digits.length - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i]++;
        return digits;
      } else if (digits[i] == 9) {
        digits[i] = 0;
      }
    }
    a[0] = 1;
    return a;
  }
  public static void main(String[] args){
    int digits[] = { 1, 2, 3};
    Plus_One m = new Plus_One();
    int val[] = m.plusOne(digits);

  }
}