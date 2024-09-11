package src.content.behaviour;

import src.content.structure.Content;
import src.content.structure.Folder;

public class ContentService {
    //public void addContent();
    // Daten eingeben
    // Daten löschen
    // Daten ausgeben
    private Folder rootFolder;

    public ContentService() {
        rootFolder = new Folder("Root");
    }

    public void addContent(Content content) {
        rootFolder.addContent(content);
    }

    public void removeContent(Content content) {
        rootFolder.removeContent(content);
    }

    public void displayContents() {
        rootFolder.displayContent();
    }
}

