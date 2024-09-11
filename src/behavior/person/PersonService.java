package src.behavior.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import person.creational.PersonFactory;
import person.structure.Person;

public class PersonService { // Klasse, die das Verhalten/Verwalten steuert.
    private List<Person> persons = new ArrayList<>();

    public void createPerson() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter type of person (natural/legal): ");
            String type = scanner.next();

            System.out.println("Enter name: ");
            String name = scanner.next();

            Person person = PersonFactory.createPerson(type, name);
            persons.add(person);  // Füge die erstellte Person zur Liste hinzu

            System.out.println("Created " + type + " with name: " + person.getName());
        } finally {
            scanner.close();  // Scanner schließen, um Ressourcen freizugeben
        }
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