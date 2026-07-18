package rohit.bit_manipulation;
class Solution {
  public int minChanges(int n, int k) {
    // Step 1: XOR n and k to find the differing bits
    int r = n ^ k;

    // Step 2: Check if it's impossible (if n has a 1 bit where k has a 0)
    if ((r & n) != r) {
      return -1; // If there are differing bits that cannot be flipped
    }

    // Step 3: Count the number of 1 bits in n where k has 0 bits
    int count = 0;
    while (n != 0) {
      if ((n & 1) == 1 && (k & 1) == 0) {
        count++; // We need to flip this 1 bit to 0
      }
      n >>= 1;
      k >>= 1;
    }

    return count; // Return the number of bit flips required
  }
}
/*
 * 14
 * 13
 *
 * 1110
 * 1101 xor
 * =====
 * 0011 = 3
 *
 * 0011
 * 1110 and
 * ====
 * 0010 = 2
 *
 * comparison of r and 14 where r is xor of 14 and 13
 *
 * 0011 & 1110 = 0010 = decimal->2
 *
 * r != r and 14
 *
 *
 * while()
 *
 *
 * public class Solution {
 * // you need treat n as an unsigned value
 * public int reverseBits(int n) {
 * String number = String.valueOf(n);
 * int sum = 0;
 * int count = 0;
 * int multiplier = 0;
 * int result= 0;
 * for(int i = number.length()-1;i>=0;i--){
 * count++;
 * result = (int)number.charAt(i);
 * multiplier = result * Math.pow(2,count);
 * sum = sum + multiplier;
 * }
 * return sum;
 * }
 * }
 */