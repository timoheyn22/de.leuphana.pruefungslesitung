package src.CarReservationService;

public class CarReservationService {
}

/* Um Booking und Payment in CONTENT einzuspeisen

// In der CarReservationService-Klasse
public class CarReservationService {
    private ContentService contentService;

    public CarReservationService() {
        contentService = new ContentService();
    }

    public void addBooking(String year, String month, Content booking) {
        Folder yearFolder = getOrCreateFolder(year);
        Folder monthFolder = getOrCreateFolderInYear(yearFolder, month);
        monthFolder.addContent(booking);
    }

    private Folder getOrCreateFolder(String year) {
        // Prüfe, ob der Ordner für das Jahr existiert, oder erstelle ihn.
        // (kann mit dem getChild() aus Folder kombiniert werden)
    }

    private Folder getOrCreateFolderInYear(Folder yearFolder, String month) {
        // Prüfe, ob der Ordner für den Monat existiert, oder erstelle ihn.
    }
}

 */




/* Um Scanner in Content zu integrieren
import java.util.Scanner;

public class CarReservationService {
    private ContentService contentService;
    private Scanner scanner;

    public CarReservationService() {
        contentService = new ContentService();
        scanner = new Scanner(System.in);
    }

    public void addBookingFromUserInput() {
        System.out.println("Enter booking name: ");
        String bookingName = scanner.nextLine();

        // Erstelle eine neue Buchung als Datei im Content-System
        File booking = new File(bookingName);
        contentService.addContent(booking);

        System.out.println("Booking '" + bookingName + "' added successfully.");
    }

    public void removeBookingFromUserInput() {
        System.out.println("Enter booking name to remove: ");
        String bookingName = scanner.nextLine();

        // Entferne die Buchung, indem du den entsprechenden Content suchst und löschst
        File booking = new File(bookingName);
        contentService.removeContent(booking);

        System.out.println("Booking '" + bookingName + "' removed successfully.");
    }

    public void displayBookings() {
        contentService.displayContents();
    }
}
 */