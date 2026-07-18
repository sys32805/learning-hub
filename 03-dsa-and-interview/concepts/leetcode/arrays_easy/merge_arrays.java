import java.util.Arrays;

public class merge_arrays {
    public void merge(int[] a, int m, int[] b, int n) {
        int i = n-1;
        int j = n-1;
        int k = m-1;
        while ( j >= 0) {
            if (i >= 0 && a[i] > b[j] ) {
                a[k--] = a[i--];
            }
             else if(i>=0) {
                a[k--] = b[j--];
            }
        }
    }

    public static void main(String[] args) {
        merge_arrays r  = new merge_arrays();
        int a[] = new int[]{3,4,5,9,0,0};
        int b[] = new int[]{7,8};
        int m = a.length;
        int n = b.length;
        r.merge(a, m, b, n);
        System.out.println(Arrays.toString(a));
    }
}

