package rohit.collection;

import java.util.ArrayList;
import java.util.Arrays;

public class Interview {
  public static void main(String[] args) {
    //very famous interview question remove even number and print odd numbers
    ArrayList<Integer> list  =  new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    list.removeIf(num->num%2==0);
    System.out.println(list);

  }
}
/**********************************************
 *        NUMBER OF METHODS AVAILABLE         *
 * ADD ,GET,REMOVEIF,STARTINDEXOF,LASTINDEXOF *
 **********************************************/

/**********************************************************************************
 * ANYTHING WHICH HAS TABLE START WITH C OR HAS SYNC KEYWORK THEY ARE THREAD SAFE *
 *          EXCEPTION VECTOR AND HASHTBALE ELSE THEY ARE NOT THREAD SAFE          *
 **********************************************************************************/

/**************************************************************
 //  remove duplicates from arraylist using java stream
 *    LIST.STREAM().DISTINCT().COLLECT(COLLECTORS.TOLIST);    *
 *                       SECOND METHOD                        *
 * CREATE A OBJECT OF LINKEDHASHSET AND ASSIGN THE VALUE INIT *
 **************************************************************/

/*******************************************
 * HOW TO CHECK IF TWO ARRAYLIST ARE EQUAL *
 *       JUT DO LIST1.EQUALS(LIST2)        *
 *******************************************/

/**********************************************************
 * HOW TO FIND WHICH ELEMNT IS NOT PRESENT IN SECOND LIST *
 *              BIGLIST.REMOVALL(SMALLLIST);              *
 **********************************************************/

/***************************
 * FIND THE COMMON ELEMENT *
 * LIST1.RETAINALL(LIST2) *
 **************************/
