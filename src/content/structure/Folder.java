package src.content.structure;
import java.util.ArrayList;
import java.util.List;

public class Folder extends Content {
    private List<Content> contents = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }


    public void addContent(Content content) {
        contents.add(content);
    }


    public void removeContent(Content content) {
        contents.remove(content);
    }


    public Content getChild(int i) {
        return contents.get(i);
    }

    @Override
    public void displayContent() {
        System.out.println("Folder: " + getName());
        for (Content content : contents) {
            content.displayContent();
        }
    }
}
