package src.person.behaviour;

import java.util.ArrayList;
import java.util.List;

import src.person.creational.PersonFactory;
import src.person.structure.Person;

public class PersonService {
    private List<Person> persons = new ArrayList<>();

    // Erstelle eine Person basierend auf dem eingegebenen Typ und Namen
    public void createPerson(String type, String name) {
        if (type == null || name == null || type.isEmpty() || name.isEmpty()) {
            throw new IllegalArgumentException("Type and name must not be null or empty");
        }

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
