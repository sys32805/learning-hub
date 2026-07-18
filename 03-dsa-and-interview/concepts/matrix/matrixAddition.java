package rohit.matrix;

public class matrixAddition {
  public static void main(String[] args){
    int[][] a = {
      {1,2,3},{4,5,6},{7,8,9}
    };
    int[][] b = {
        { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }
    };
    int[][] c = new int[3][3];
    int[][] transpose = new int[3][3];
    matrixAddition ob = new matrixAddition();
    ob.add(a,b,c, transpose);
  }

  private void add(int[][] a, int[][] b, int[][] c,int[][] transpose) {
    for(int i = 0;i< a.length ;i++){
      for(int j = 0 ;j < a[i].length ;j++){
        /*for matrix addition this needs to be done  */
        c[i][j] = a[i][j] + b[i][j];
        /*lets check what needs to be done inorder to transpose a matrix */
        transpose[j][i] = c[i][j];
        // System.err.print(c[i][j] + " ");
        System.err.print(transpose[j][i] + " ");
      }
      System.err.println();
    }
  }
}
