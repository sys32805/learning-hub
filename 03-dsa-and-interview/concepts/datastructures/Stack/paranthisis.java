import java.util.Stack;

public class paranthisis {
    public boolean paranthisis_validation(Stack<Integer> stack , String a){
        int size = a.length();
        int count = 0;
        int currenIndex = 0; // Move the currentIndex initialization outside the loop
        if(size == 0){
          return false;
        }
        while( size > 0){
            // Fetch the character at the current index
            char currentChar = a.charAt(currenIndex);

            // Increment or decrement count based on the current character
            switch (currentChar) {
                case '(':
                    count = count+1;
                    break;
                case ')':
                    count = count-1;
                    break;
                case '[':
                    count = count+2;
                    break;
                case ']':
                    count = count-2;
                    break;
                case '{':
                    count = count+3;
                    break;
                case '}':
                    count = count-3;
                    break;
                default:
                    break;
            }

            // Move to the next character
            currenIndex++;
            size--;
        }

        return count == 0; // Return true if count is 0, false otherwise
    }

    public static void main(String[] args){
        paranthisis ob = new paranthisis();
        Stack<Integer> stack = new Stack<Integer>();
        String a  = "()";
        boolean r = ob.paranthisis_validation(stack,a);
        System.out.println(r);
    }
}
