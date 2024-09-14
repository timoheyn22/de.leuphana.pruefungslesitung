package src.Resource;

public class ChildSeat extends Resource {
    public ChildSeat(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("ChildSeat: " + name);
    }
}
