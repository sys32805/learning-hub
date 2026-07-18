package rohit.bit_manipulation;

public class minBitFlips {
  public static void main(String[] args) {
    int start = 10;
    int goal = 7;
    int countOfOne1 = 0;
    int result = 0;
    // output = 3
    result = start ^ goal;
    String newResult = NumToBinary(result);
    for(int j = newResult.length()-1 ; j >= 0 ; j--){
      if(newResult.charAt(j) == '1'){
          countOfOne1++;
      }
    }
  }

  private static String NumToBinary(int i) {
    String binary = "";
    while (i > 0) {
      binary = i % 2 + binary;
      i = i / 2;
    }
    return binary;
  }
}
