import java.util.Scanner;


public class Main {
public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    try{
        String[] menuItems = {
                "Encrypt a file",
                "Decrypt a file",
                "Exit application"
        };

        MenuHandler menu = new MenuHandler(menuItems);
        menu.handleSelection(keyboard);

    }
    catch(Exception e){
        System.out.println("Invalid selection. Please input a valid number.");

    }

}
}