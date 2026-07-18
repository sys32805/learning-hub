package rohit;

public class search {
  public static void main(String[] args) {
    int[] a = { 4, 5, 6, 7, 0, 1, 2 };
    int left = 0;
    int t = 7;
    int right = a.length - 1;
    int mid = left + (right - left) / 2;
    while (left < right) {
      if (a[mid] == t) {
        System.out.println("elment found at index " + mid);
        return;
      }
      if (a[left] < a[mid]) {
        if (a[left] < t && a[mid] >= t) {
          right = mid--;
        } else {
          left = mid++;
        }
      } else {
        if (a[mid] <= t && a[right] >= t) {
          left = mid++;
        } else {
          right = mid--;
        }
      }
    }
  }
}
