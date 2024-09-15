package src.person.behaviour;



import java.util.Scanner;

public class PersonMenu {
    private PersonService personService;
    private Scanner scanner;

    public PersonMenu() {
        personService = new PersonService();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("Please choose an option:");
            System.out.println("1. Create Person");
            System.out.println("2. Delete Person");
            System.out.println("3. List Persons");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character from the buffer

            switch (choice) {
                case 1:
                    createPersonMenu();
                    break;
                case 2:
                    deletePersonMenu();
                    break;
                case 3:
                    personService.listPersons();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void createPersonMenu() {
        System.out.println("Enter type of person (natural/legal): ");
        String type = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        try {
            personService.createPerson(type, name);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to create person: " + e.getMessage());
        }
    }

    private void deletePersonMenu() {
        System.out.println("Enter name of the person to delete: ");
        String name = scanner.nextLine();
        personService.deletePerson(name);
    }
    public static void main(String[] args) {
        PersonMenu menu = new PersonMenu();
        menu.start();
    }
}
