package src.person.structure;

public class LegalPerson implements Person {
    private String companyName;

    public LegalPerson(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getName() {
        return companyName;
    }
}
