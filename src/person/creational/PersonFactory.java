package src.person.creational;

import src.person.structure.LegalPerson;
import src.person.structure.NaturalPerson;
import src.person.structure.Person;

public abstract class PersonFactory { // Klasse, in der entschieden wird, welche Klasse die Instanz hat und die richtige Konstruktor-Methode ausgewählt wird

    public static Person createPerson(String type, String name) {
        if (type.equalsIgnoreCase("natural")) {
            return new NaturalPerson(name);
        } else if (type.equalsIgnoreCase("legal")) {
            return new LegalPerson(name);
        }
        throw new IllegalArgumentException("Unknown person type");
    }
}
