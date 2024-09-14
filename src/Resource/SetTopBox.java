package src.Resource;

public class SetTopBox extends Resource {
    public SetTopBox(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("SetTopBox: " + name);
    }
}