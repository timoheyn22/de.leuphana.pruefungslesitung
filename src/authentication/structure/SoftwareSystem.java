package src.authentication.structure;

public class SoftwareSystem extends Subject {
    private String name;

    public SoftwareSystem(String name) {
        super(name);
    }
    @Override
    public String getName() {
        return name;
    }
}
