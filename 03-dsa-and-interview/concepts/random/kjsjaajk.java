package rohit;
import java.util.*;

class node{
  int data;
  node next;
   node(int data , node next){
    this.data= data;
    this.next = next;
  }
}
public class kjsjaajk {
  static node head;
  static node head1;
  // static{
  //   List<Integer> ob = new ArrayList<>();
  //   int a = 1009;
  //   int b = 1;
  //   int carry = 0;
  //   while(a != 0){
  //     int digitFrom1 = a % 10 + carry;
  //     int digitFrom2 = b % 10;
  //     a = a / 10;
  //     b = b / 10;
  //     int lastDigit = (digitFrom1 + digitFrom2) % 10;
  //     ob.add(lastDigit);
  //     carry = (digitFrom1 + digitFrom2) / 10;
  //   }
  //   if(carry > 0 ){
  //     ob.add(carry);
  //   }
  //   Collections.reverse(ob);
  //   System.out.println(ob);
  // }
    public static void main(String[] args){
      head  = new node(98, null);
      head.next = new node(2,null);
      head.next.next = new node(32, null);

      System.out.print("Before reversing the node");

      head1 = new node(49, null);
      head1.next = new node(50, null);
      head1.next.next = new node(60, null);
      // printNodes(head);
      // node prv = reverseNode(head);
      // addNumbers(head);
      // printNodes(head);
      mergeNode(head , head1);
  }

  static void mergeNode(node head, node head1){
    List<Integer> ob  = new ArrayList<>();
    node current = head;
    node lastNode  = null;
    while(current.next != null){
      current = current.next;
    }
    current.next = head1;
    current = head;
    printNodes(head);
    while(current != null){
      ob.add(current.data);
      current = current.next;
    }
    Collections.sort(ob);
    System.out.print(ob);
    current = head;
    int index = 0;
    while (current != null) {
      current.data = ob.get(index++);
      current = current.next;
    }
    current = head;
    printNodes(head);
  }

  static void addNumbers(node head){
    int num2   = 100;
    node current  = head;
    while(current != null){
      int num = addNumbers(99 ,current.data);
      current.data = num;
      current = current.next;
    }
  }

  static int addNumbers( int num1 , int num2){
    List<Integer> ob = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int a = num1;
    int b = num2;
    int carry = 0;
    while(a != 0){
      int digitFrom1 = a % 10 + carry;
      int digitFrom2 = b % 10;
      a = a / 10;
      b = b / 10;
      int lastDigit = (digitFrom1 + digitFrom2) % 10;
      ob.add(lastDigit);
      carry = (digitFrom1 + digitFrom2) / 10;
    }
      if(carry > 0 ){
        ob.add(carry);
      }
      Collections.reverse(ob);
      for (int num : ob) {
        sb.append(num);
      }
      return Integer.parseInt(sb.toString());
  }

  static void printNodes(node head){
    node currNode  = head;
    System.err.print("   Nodes  are ");
    while (currNode != null) {
      System.err.print(" -> " + currNode.data);
      currNode = currNode.next;
    }
    System.err.println();
  }

  static node reverseNode(node head){
      System.err.println();
      System.out.print(" After reversing the node ");
      node next = null;
      node current = head;
      node previous = null;
      while( current != null){
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
      }
      node temp = previous;
      while (previous != null) {
        System.err.print(" -> " + previous.data);
        previous = previous.next;
      }
      System.err.println();
      return temp;
  }
}