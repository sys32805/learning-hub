package rohit;

import java.util.LinkedList;

public class addTwonumbers {
  Node head;
  static class Node{
    int data ;
    Node next;
  public Node(int data){
      this.data = data;
      this.next = null;
    }
  }
  public static void main(String args[]){
    addTwonumbers sb = new addTwonumbers();
    Node l1 = new Node(3);
    l1.next = new Node(2);
    l1.next.next = new Node(1);

    // Create the second linked list: 3 -> 2 -> 1 (represents 123)
    Node l2 = new Node(3);
    l2.next = new Node(2);
    l2.next.next = new Node(1);

    sb.addtwolinklist(l1,l2);
  }
    public static void addtwolinklist(Node l1, Node l2) {
      Node new_node = new Node(0);
      Node dummyHead = new_node;
      int carry = 0;
      int sum =0;
      Node current = dummyHead;
      while(l1!=null || l2 != null || carry != 0){
         sum = carry;
        if (l1 != null) {
          sum += l1.data; // Add the current digit from l1
          l1 = l1.next; // Move to the next node in l1
        }

        if (l2 != null) {
          sum += l2.data; // Add the current digit from l2
          l2 = l2.next; // Move to the next node in l2
        }
        carry = sum / 10;
        int digit  = sum%10;
        current.next = new Node(digit);
        current = current.next;
      }
      while (dummyHead != null) {
        System.out.println(dummyHead.data);
        dummyHead = dummyHead.next;
      }
    }
}
