package src.Resource;

public abstract class ResourceDecorator extends Resource {
    protected Resource resource;

    public ResourceDecorator(Resource resource) {
        super(resource.getName());
        this.resource = resource;
    }

    @Override
    public void display() {
        resource.display();
    }
}