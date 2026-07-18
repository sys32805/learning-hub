package rohit.bit_manipulation;

public class NumToBinary {
  public static void main(String[] args) {
    int num  = 5;
    String binary = "";
    while(num > 0){
      binary = (num % 2) + binary;
      num = num / 2;
    }
    System.out.println(binary);
  }
}
