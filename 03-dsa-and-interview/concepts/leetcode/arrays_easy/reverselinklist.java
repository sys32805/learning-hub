// package rohit.leetcode.arrays_easy;

public class reverselinklist {
  static Node head;
  class Node{
    int data;
    Node next;
    public Node(int data){
      this.data = data;
      this.next = null;
    }
  }

  public Node reversLinkNodes(Node head){
    Node prev  = null;
    Node current = head;
    Node next = null;
    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    head = prev;
    return head;
  }

  public void pushNode(int data) {
    Node new_node = new Node(data);
    if (head == null) {
      head = new_node;
    } else {
      Node temp = head;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = new_node;
    }
}
  public void printNodes(Node a){
    Node temp = a;
    while(temp.next != null){
      System.err.println("The values are " + temp.data );
      temp = temp.next;
    }
    /*
     * To get print of last element
     */
    System.err.println("The values are " + temp.data );
  }
  public static void main(String args[]){
    reverselinklist ob = new reverselinklist();
    ob.pushNode(1);
    ob.pushNode(2);
    ob.pushNode(3);
    ob.pushNode(4);
    ob.pushNode(5);
    ob.printNodes(head);
    Node a = ob.reversLinkNodes(head);
    System.err.println("======================");
    ob.printNodes(a);
  }
}
