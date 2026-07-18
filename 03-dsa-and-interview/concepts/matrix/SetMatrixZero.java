package rohit.matrix;

import java.util.Arrays;

public class SetMatrixZero {
  public static void main(String args[]){
    int matrix[][] = {
      {0,2,3},
      {4,0,6},
      {7,8,9}
    };
    // System.err.println(matrix.length);
    // System.err.println(matrix[1].length);
    /*
     * matrix traversal
     */
    int indexI = 0;
    int indexJ = 0;
    int i = 0;
    int j = 0;
    int k = 0;
    int count = 0;
    boolean[] rowsToZero = new boolean[matrix.length];
    boolean[] colsToZero = new boolean[matrix[0].length];
    int[] container = new int[6];
    for(i = 0; i < matrix.length; i++ ){
      for(j = 0 ; j < matrix[i].length; j++){
        if(matrix[i][j] == 0){
          System.err.print(matrix[i][j] + " at position " + i + " "+ j);
          rowsToZero[i] = true;
          colsToZero[j] = true;
          break;
        }
      }
    }
    System.err.println(Arrays.toString(container) + " " + count);
    System.err.println(" The indexes are " + indexI + " " +indexJ);
    i = 0 ;
    j = 0;
    for(i = 0 ;i < matrix.length ; i++){
      for( j = 0 ; j < matrix[0].length ; j++){
        if(rowsToZero[i] || colsToZero[j]){
          matrix[i][j] = 0;
        }
      }
    }
    for (i = 0; i < matrix.length; i++) {
      for (j = 0; j < matrix[i].length; j++) {
        System.err.print(matrix[i][j] +" ");
      }
      System.err.println();
    }
  }
}
