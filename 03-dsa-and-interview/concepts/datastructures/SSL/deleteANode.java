class Node{
  int data;
  Node next;
  Node( int data, Node  next){
    this.data = data;
    this.next = next;
  }
}
public class deleteANode {
  public static void main(String[] args){
    Node a = new Node(1, null);
    a.next = new Node(2, null);
    a.next.next = new Node(3, null);
    Node current = a;
    while( current != null){
      System.err.println("The nodes are " + current.data);
      current = current.next;
    }
    int tData  = 2;
    current = a;
    while(current != null){
      if(current.data == tData){
        current.next = current.next.next;
      }
      current = current.next;
    }
    current = a;
    while (current != null) {
      System.err.println("The nodes are " + current.data);
      current = current.next;
    }
  }
}
