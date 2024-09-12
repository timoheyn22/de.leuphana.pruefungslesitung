package src.person.structure;

import src.authentication.structure.Subject;

public class NaturalPerson extends Subject implements Person  { //Implementiert Interface, Vererbung von name aus Subject
    private String name;

    public NaturalPerson(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
