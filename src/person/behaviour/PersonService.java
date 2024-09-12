package src.person.behaviour;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.person.creational.PersonFactory;
import src.person.structure.Person;

public class PersonService { // Klasse, die das Verhalten/Verwalten steuert.
    private List<Person> persons = new ArrayList<>();
    private Scanner scanner;

    // Initialisiere den Scanner nur einmal im Konstruktor
    public PersonService() {
        scanner = new Scanner(System.in);
    }

    // Erstelle eine Person mit Benutzerinteraktion
    public void createPerson() {
        String type;
        // Validierung der Eingabe für den Personentyp
        do {
            System.out.println("Enter type of person (natural/legal): ");
            type = scanner.next();
            if (!type.equalsIgnoreCase("natural") && !type.equalsIgnoreCase("legal")) {
                System.out.println("Invalid type. Please enter 'natural' or 'legal'.");
            }
        } while (!type.equalsIgnoreCase("natural") && !type.equalsIgnoreCase("legal"));

        System.out.println("Enter name: ");
        String name = scanner.next();

        // Erstelle die Person basierend auf dem eingegebenen Typ
        Person person = PersonFactory.createPerson(type, name);
        persons.add(person);  // Füge die erstellte Person zur Liste hinzu

        System.out.println("Created " + type + " with name: " + person.getName());
    }

    public void listPersons() {
        if (persons.isEmpty()) {
            System.out.println("No persons available.");
            return;
        }
        for (Person person : persons) {
            System.out.println(person.getName());
        }
    }


    public void deletePerson(String name) {
        Person toRemove = findPersonByName(name);
        if (toRemove != null) {
            persons.remove(toRemove);
            System.out.println("Person with name " + name + " has been removed.");
        } else {
            System.out.println("Person with name " + name + " not found.");
        }
    }

    public Person findPersonByName(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    // Diese Methode gibt die Liste aller Personen zurück
    public List<Person> getPersons() {
        return persons;
    }
}
