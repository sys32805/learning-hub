public class miiddle_of_linklist {
 Node head;
  class Node{
    int data;
    Node next;
    public Node(int data){
      this.data = data;
      this.next = null;
    }
  }
  public void pushNode(int data){
      Node new_node = new Node(data);
      new_node.next = head;
      head = new_node;
  }
  public void printNodes(){
    Node temp = head;
    while (temp.next != null) {
      System.out.println(temp.data);
      temp = temp.next;
    }
  }
  public void findMiddle(){
    Node ptr1 = head;
    Node ptr2 = head;
    while (ptr1.next != null && ptr2.next.next != null) {
      ptr1 = ptr1.next;
      ptr2 = ptr2.next.next;
    }
    System.err.println("Middle Element is " + ptr1.data );
  }
  public static void main(String a[]){
    miiddle_of_linklist ob = new miiddle_of_linklist();
    ob.pushNode(5);
    ob.pushNode(6);
    ob.pushNode(7);
    ob.pushNode(9);
    ob.printNodes();
    ob.findMiddle();
  }
}
