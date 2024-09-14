package test;

import src.Resource.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceServiceTest {
    private ResourceService resourceService;

    @BeforeEach
    void setUp() {
        resourceService = new ResourceService();
    }

    @Test
    void testAddResource() {
        Resource car = new Car("TestCar");
        resourceService.addResource(car);

        Resource retrievedResource = resourceService.getSelectedResource("TestCar");
        assertNotNull(retrievedResource, "Resource should be added and retrieved successfully.");
        assertEquals("TestCar", retrievedResource.getName(), "Resource name should match.");
    }

    @Test
    void testRemoveResource() {
        Resource car = new Car("TestCar");
        resourceService.addResource(car);

        resourceService.removeResource("TestCar");

        Resource retrievedResource = resourceService.getSelectedResource("TestCar");
        assertNull(retrievedResource, "Resource should be removed successfully.");
    }

    @Test
    void testGetSelectedResource() {
        Resource car = new Car("TestCar");
        Resource setTopBox = new SetTopBox("TestSetTopBox");
        resourceService.addResource(car);
        resourceService.addResource(setTopBox);

        Resource retrievedCar = resourceService.getSelectedResource("TestCar");
        Resource retrievedSetTopBox = resourceService.getSelectedResource("TestSetTopBox");

        assertNotNull(retrievedCar, "Car resource should be retrieved successfully.");
        assertEquals("TestCar", retrievedCar.getName(), "Retrieved car name should match.");

        assertNotNull(retrievedSetTopBox, "SetTopBox resource should be retrieved successfully.");
        assertEquals("TestSetTopBox", retrievedSetTopBox.getName(), "Retrieved SetTopBox name should match.");
    }

}