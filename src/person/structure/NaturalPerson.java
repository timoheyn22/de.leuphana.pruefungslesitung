package src.person.structure;

public class NaturalPerson /*extends Subject*/ implements Person  { //Implementiert Interface, Vererbung von name aus Subject
    private String name;

    public NaturalPerson(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
