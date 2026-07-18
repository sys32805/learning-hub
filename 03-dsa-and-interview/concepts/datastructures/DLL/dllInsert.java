package rohit.datastructures.DLL;

class node{
  node back;
  int data;
  node next;
  node( int data , node back, node next){
    this.data = data;
    this.back = back;
    this.next = next;
  }
}
public class dllInsert {
  /*
   * 0 <-> 1 <-> 2 <-> 3;
   */
  public static void main(String[] args) {
    node a = new node(1, null, null);
    a.next = new node(2, a , null);
    a.next.next = new node(3, a.next, null);
    node current = a;
    node notNull = null;
    while( current.next != null){
      System.err.println(current.data);
      current = current.next;
    }
    System.err.println(current.data);
    while (current != null) {
      System.err.println(current.data);
      current = current.back;
    }
  }
}
