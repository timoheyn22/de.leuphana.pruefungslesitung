package src.content.structure;

public abstract class Content {
    private String name;

    public Content(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void displayContent();
}

