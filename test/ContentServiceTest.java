package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.content.behaviour.ContentService;
import src.content.structure.File;
import src.content.structure.Folder;

public class ContentServiceTest {

    private ContentService contentService;

    @Before
    public void setUp() {
        contentService = new ContentService();
    }

    @Test
    public void testAddFile() {
        File file = new File("TestFile");
        contentService.addContent(file);

        // Hier könnte man den Inhalt überprüfen
        contentService.displayContents();  // Sollte "File: TestFile" anzeigen
    }

    @Test
    public void testAddFolder() {
        Folder folder = new Folder("TestFolder");
        contentService.addContent(folder);

        contentService.displayContents();  // Sollte "Folder: TestFolder" anzeigen
    }

    @Test
    public void testRemoveFile() {
        File file = new File("TestFile");
        contentService.addContent(file);
        contentService.removeContent(file);

        // Überprüfen, dass das File entfernt wurde
        contentService.displayContents();  // Sollte nichts anzeigen
    }
    @Test
    public void testGetChild() {
        Folder folder = new Folder("TestFolder");
        File file1 = new File("TestFile1");
        File file2 = new File("TestFile2");

        folder.addContent(file1);
        folder.addContent(file2);

        contentService.addContent(folder);

        // Teste getChild() für den Zugriff auf die einzelnen Inhalte
        assertEquals("TestFile1", folder.getChild(0).getName());
        assertEquals("TestFile2", folder.getChild(1).getName());

        contentService.displayContents();  // Sollte den Ordner mit den beiden Dateien anzeigen
    }
}