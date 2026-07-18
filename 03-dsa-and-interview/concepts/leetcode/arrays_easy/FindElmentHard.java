package rohit.leetcode.arrays_easy;

import java.util.Arrays;

public class FindElmentHard {
  public static void main(String[] args) {
    int a[] = {5,7,7,8,8,10};
    System.out.println(Arrays.toString(FindElmentHard.searchRange(a,7)));
  }

  public static int[] searchRange(int[] a, int target) {
    int[] index = new int[2];
    int left = 0;
    int right = a.length - 1;
    index[0] = -1;
    index[1] = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (a[mid] < target) {
        left = mid + 1;
      } else if (a[mid] > target) {
        right = mid - 1;
      } else {
        if (mid == 0 || a[mid - 1] != target) {
          index[0] = mid;
          break;
        }
        right = mid - 1;
      }
    }
    if (index[0] == -1)
      return index;
    left = 0;
    right = a.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (a[mid] < target) {
        left = mid + 1;
      } else if (a[mid] > target) {
        right = mid - 1;
      } else {
        if (mid == a.length - 1 || a[mid + 1] != target) {
          index[1] = mid;
          break;
        }
        left = mid + 1;
      }
    }
    return index;
  }
}


