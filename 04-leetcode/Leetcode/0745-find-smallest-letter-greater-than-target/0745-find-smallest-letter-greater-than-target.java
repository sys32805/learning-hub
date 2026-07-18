//LinkedIn Amazon Bloomberg
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        char result = ' ';
        for(int i = letters.length-1 ; i >= 0 ; i--){
            if( letters[i] > target){
                result = letters[i];
            }
        }
        return result == ' ' ? letters[0] : result;
    }
}