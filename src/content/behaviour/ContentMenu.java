package src.content.behaviour;

import java.util.Scanner;

import src.content.structure.Content;
import src.content.structure.File;
import src.content.structure.Folder;

/*
import content.structure.Content;
import content.structure.File;
import content.structure.Folder;
*/
public class ContentMenu {
    private ContentService contentService;

    public ContentMenu(ContentService contentService) {
        this.contentService = contentService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nContent Menu:");
            System.out.println("1. Add content");
            System.out.println("2. Remove content");
            System.out.println("3. Display contents");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addContent(scanner);
                    break;
                case 2:
                    removeContent(scanner);
                    break;
                case 3:
                    displayContents();
                    break;
                case 4:
                    System.out.println("Exiting Content Menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void addContent(Scanner scanner) {
        System.out.println("Enter content type (file/folder): ");
        String type = scanner.next();

        System.out.println("Enter content name: ");
        String name = scanner.next();

        Content content = null;

        if (type.equalsIgnoreCase("file")) {
            content = new File(name);
        } else if (type.equalsIgnoreCase("folder")) {
            content = new Folder(name);
        } else {
            System.out.println("Invalid content type. Please enter 'file' or 'folder'.");
            return;  // Abbrechen, wenn der Inhaltstyp ungültig ist
        }

        contentService.addContent(content);
        System.out.println("Added " + type + " with name: " + name);
    }

    private void removeContent(Scanner scanner) {
        System.out.println("Enter content name to remove: ");
        String name = scanner.next();

        // Hier kannst du entscheiden, ob es eine Datei oder ein Ordner ist
        // Für dieses Beispiel gehen wir davon aus, dass nur Dateien entfernt werden
        File content = new File(name);

        contentService.removeContent(content);
        System.out.println("Removed content with name: " + name);
    }

    private void displayContents() {
        System.out.println("Displaying all contents:");
        contentService.displayContents();
    }

    public static void main(String[] args) {
        ContentService contentService = new ContentService();
        ContentMenu contentMenu = new ContentMenu(contentService);
        contentMenu.start();
    }
}
