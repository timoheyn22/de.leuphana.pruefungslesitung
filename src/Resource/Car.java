package src.Resource;

public class Car extends Resource {
    public Car(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("Car: " + name);
    }
}