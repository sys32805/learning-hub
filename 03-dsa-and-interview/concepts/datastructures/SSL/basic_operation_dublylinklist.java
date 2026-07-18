/* Basic Operations:
Insertion at the beginning: Implement a function to insert a node at the beginning of a doubly linked list.
Insertion at the end: Implement a function to insert a node at the end of a doubly linked list.
Deletion by key: Implement a function to delete a node from the doubly linked list based on a given key.
Deletion at a position: Implement a function to delete a node from the doubly linked list at a given position.
Traversal: Implement a function to traverse and print all elements of the doubly linked list.
Advanced Operations:
Reverse a doubly linked list: Implement a function to reverse the elements of a doubly linked list.
Find middle node: Implement a function to find the middle node of a doubly linked list.
Merge two sorted doubly linked lists: Implement a function to merge two sorted doubly linked lists into a single sorted list.
Check if a doubly linked list is palindrome: Implement a function to check if a doubly linked list is palindrome.
Rotate doubly linked list: Implement a function to rotate a doubly linked list by a given number of positions.
Specific Problems:
Clone a doubly linked list with random pointers: Implement a function to clone a doubly linked list with each node containing an extra random pointer pointing to any node in the list or null.
Flatten a doubly linked list: Implement a function to flatten a doubly linked list where each node contains a child linked list.
Intersection of two doubly linked lists: Implement a function to find the intersection point of two doubly linked lists.
Additional Practice:
Sort a doubly linked list: Implement a function to sort a doubly linked list using an efficient sorting algorithm.
Detect and remove loop in a doubly linked list: Implement a function to detect if there is a loop in a doubly linked list and remove it if present.
Convert a binary tree to a doubly linked list: Implement a function to convert a binary search tree into a sorted doubly linked list.
Convert a sorted doubly linked list to a balanced BST: Implement a function to convert a sorted doubly linked list into a balanced Binary Search Tree (BST).
Delete alternate nodes in a doubly linked list: Implement a function to delete alternate nodes of a doubly linked list.
Pairwise swap nodes in a doubly linked list: Implement a function to pairwise swap elements in a doubly linked list.
Find the kth node from the end in a doubly linked list: Implement a function to find the kth node from the end of a doubly linked list.

 */
public class basic_operation_dublylinklist {
  class Node {
    Node previous;
    int data;
    Node next;

    Node(Node previous, Node next, int data) {
      this.previous = previous;
      this.data = data;
      this.next = next;
    }
  }

  public Node previous = null;
  public Node head = null;
  public Node tail = null;


  public static void main(String args[]) {
    basic_operation_dublylinklist ob = new basic_operation_dublylinklist();
    ob.insertNode(1);
    ob.insertNode(2);
    ob.insertNode(3);
    ob.insertNode(4);
    System.err.println(" All the elements in doubly linklist ");
    ob.printNode();
    System.err.println(" Insert at begining in the linklist ");
    ob.insertAtBegining(0);
    ob.printNode();
    System.err.println(" Insert at end in the linklist ");
    ob.insertAtEnd(5);
    ob.printNode();
    System.err.println(" Deletion of element based on key ");
    ob.deleteNodeAtPosition(4);
    ob.printNode();
    System.err.println(" Reverse a dubly linklist ");
    ob.reversNodes();
    // ob.printNode();
  }

  private void reversNodes() {
    Node current = head;
    while(current.next != null){
      current = current.next;
      continue;
    }
    while(current != null){
      System.err.println(" Reverse data is " + current.data);
      current = current.previous;
    }
  }

  public void deleteNodeAtPosition(int key) {
    Node currNode  = head;
    int count  = 1;
    while(currNode.next !=  null){
      if(count == key){
        System.err.println(" Element which about to delete is " + currNode.data);
        previous.next = currNode.next;
        return;
      }
      count++;
      previous = currNode;
      currNode = currNode.next;
      // System.err.println("Privious element" + previous.data);
      // System.err.println("Next element" + currNode.next.data);
    }
  }

  public void insertAtEnd(int data) {
    Node lastElement =  new Node(null, null, data);
    Node current = head;
    while(current.next != null){
      current = current.next;
    }
    current.next = lastElement;
    lastElement.previous = current;
    lastElement.next = null;

  }

  public void insertNode(int data) {
    Node newNode = new Node(null, null, data);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      newNode.previous = tail;
      tail.next = newNode;
      tail = newNode;
    }
  }

  public void printNode() {
    Node current = head;
    while (current != null) {
      System.err.print(" Data-next -> " + current.data);
      System.err.println();
      current = current.next;
    }
  }

  public void insertAtBegining(int data) {
    Node current = head;
    Node a = new Node(null, null, data);
    head = a;
    head.next = current;
    head.previous = null;
  }
}
