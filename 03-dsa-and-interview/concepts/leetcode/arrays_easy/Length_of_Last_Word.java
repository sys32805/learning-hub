// import java.util.Arrays;

// public class Length_of_Last_Word {
//   public static void main(String[] args) {
//     String str = " bdsds  sdjbfjdfsdf ksjdfs  sdjbfsjd ";
//     String trim  = str.trim();
//     int length = trim.length();
//     int exit = 0;
//     int index = 0;
//     int countt = 0;
//     for(int i = length-1; i >= 0 ; i--){
//       if(trim.charAt(i) != ' '){
//         countt++;
//       } else {
//         exit = 1;
//       }
//       if(exit == 1){
//         System.err.println(" here is the result " + countt);
//         return;
//       }
//     }
//   }
// }

class Solution {
  public int lengthOfLastWord(String str) {
    String trimed = str.trim();
    int length = trimed.length();
    int exit = 0;
    int countt = 0;
    for (int i = length - 1; i >= 0; i--) {
      if (trimed.charAt(i) != ' ') {
        countt++;
      } else {
        exit = 1;
      }
      if (exit == 1) {
        // System.err.println(" here is the result " + countt);
        break;
      }
    }
    return countt;
  }
}