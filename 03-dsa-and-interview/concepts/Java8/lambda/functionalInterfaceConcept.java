// package rohit.Java8.lambda;

import java.util.function.Function;

public class functionalInterfaceConcept {
  public static void main(String[] args) {
/********************************************
 *              FUNCTION<T,R>               *
 * HERE T IS THE INPUT TYPE OF THE FUNCTION *
 *        HERE R IS THE RETURN TYPE         *
 ********************************************/
    Function<String,Integer> func  = x->x.length();
/**************************************************
 * APPLY FUNCTION WILL PASS THE VALUE TO FUNCTION *
 **************************************************/
    int len = func.apply("null");
    System.err.println(len);

    //here we have concept of chaining
    Function<Integer,Integer> func2  = x->x*2;
    var result = func.andThen(func2).apply("null1231221");
    System.out.println(result);
  }
}
