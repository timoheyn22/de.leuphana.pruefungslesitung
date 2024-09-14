package src.Resource;

public class Resource {
    protected String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.println("Resource: " + name);
    }
}