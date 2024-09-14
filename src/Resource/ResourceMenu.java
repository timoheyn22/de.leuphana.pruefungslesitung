package src.Resource;

import java.util.Scanner;

public class ResourceMenu {
    public static void main(String[] args) {
        ResourceService resourceService = new ResourceService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Daten eingeben");
            System.out.println("2. Daten löschen");
            System.out.println("3. Daten ausgeben");
            System.out.println("4. Beenden");
            System.out.print("Wählen Sie eine Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Verbraucht die neue Zeile

            switch (choice) {
                case 1:
                    System.out.print("Geben Sie den Ressourcentyp (Car, SetTopBox, ChildSeat) ein: ");
                    String type = scanner.nextLine();
                    System.out.print("Geben Sie den Namen der Ressource ein: ");
                    String name = scanner.nextLine();
                    Resource resource = null;

                    if (type.equalsIgnoreCase("Car")) {
                        resource = new Car(name);
                    } else if (type.equalsIgnoreCase("SetTopBox")) {
                        resource = new SetTopBox(name);
                    } else if (type.equalsIgnoreCase("ChildSeat")) {
                        resource = new ChildSeat(name);
                    }

                    if (resource != null) {
                        resourceService.addResource(resource);
                        System.out.println(type + " erfolgreich hinzugefügt.");
                    } else {
                        System.out.println("Ungültiger Ressourcentyp.");
                    }
                    break;
                case 2:
                    System.out.print("Geben Sie den Namen der zu löschenden Ressource ein: ");
                    String nameToDelete = scanner.nextLine();
                    resourceService.removeResource(nameToDelete);
                    System.out.println("Ressource gelöscht.");
                    break;
                case 3:
                    resourceService.displayResources();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
            }
        }
        scanner.close();
    }
}