package src.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceService {
    private List<Resource> resources = new ArrayList<>();

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void removeResource(String name) {
        resources.removeIf(resource -> resource.getName().equals(name));
    }

    public Resource getSelectedResource(String name) {
        return resources.stream()
                .filter(resource -> resource.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void displayResources() {
        for (Resource resource : resources) {
            resource.display();
        }
    }
}
