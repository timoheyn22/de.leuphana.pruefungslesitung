package src.content.structure;

public class File extends Content {
    public File(String name) {
        super(name);
    }

    @Override
    public void displayContent() {
        System.out.println("File: " + getName());
    }
}

