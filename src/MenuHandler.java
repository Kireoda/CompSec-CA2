import java.util.Scanner;
import java.io.FileNotFoundException;

public class MenuHandler {
    private final String[] menuItems;

    public MenuHandler(String[] menuItems) {
        this.menuItems = menuItems;
    }

    public void displayMenu() {
        System.out.println("Please select a menu option:");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
        System.out.println("Enter -1 to exit");
    }

    public void handleSelection(Scanner keyboard) throws FileNotFoundException {
        int menuItem = 0;
        do {
            displayMenu();
            try{
            menuItem = keyboard.nextInt();
            keyboard.nextLine();

            if (menuItem == -1) {
                System.out.println("Exiting.");
                break;
            } else if (menuItem == 1) {
                encryptFile(keyboard);

            } else if (menuItem == 2) {
                decryptFile(keyboard);

            } else {
                System.out.println("Invalid selection. Please input a valid number.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please input a valid number.");
            keyboard.nextLine();
        }
        } while (menuItem != -1);
    }

    private void encryptFile(Scanner keyboard) throws Exception {
        System.out.println("Please enter the filename to encrypt:");
        String fileName = keyboard.nextLine();
        EncryptAndDecryptUtil.encryptFile(fileName);

    }
    private void decryptFile(Scanner keyboard) throws Exception {
        System.out.println("Please enter the filename to decrypt:");
        String fileName = keyboard.nextLine();

        System.out.println("Please enter the AES key:");
        String keyHex = keyboard.nextLine().trim();
        EncryptAndDecryptUtil.decryptFile(fileName, keyHex);
    }
}