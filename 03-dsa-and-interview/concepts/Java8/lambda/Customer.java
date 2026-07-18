package rohit.Java8.lambda;

public class Customer {
  private String name;
  private int age;
  public Customer(String name, int age){
    this.age = age;
    this.name = name;
  }
  public String getName(){
    return name;
  }

  public void setname(String name) {
    this.name =name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Customer{name='" + name + "', age=" + age + "}";
  }
}
