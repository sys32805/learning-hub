/*
 * This is the concept of mehtod hiding
 */
package rohit.Concept;

 class logger {
  static void printMessage( String msg){
    System.out.println(" This is parent " + msg );
  }
}
 class FileLogger extends logger{
  static void printMessage(String msg){
    System.out.println(" This is the fileLogger child " + msg);
  }
}

public class mehtodHiding{
  public static void main(String[] args) {
    logger m1 = new logger();
    m1.printMessage("Thats it");
    FileLogger m2 = new FileLogger();
    m2.printMessage("Thats 2nd it");
    logger m3 = new FileLogger();
    m3.printMessage("check!");
    /*
     * This is called method hiding where methods can be get overriden if they are static
     */
  }
}
