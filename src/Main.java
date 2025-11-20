public class Main {
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    try{
        String[] menuItems = {
                "Encrypt a file",
                "Decrypt a file",
                "Exit appplication"
        };

        MenuHandler menu = new MenuHandler(menuItems);
        menu.displayMenu();
        menu.handleSelection(keyboard);

        keyboard.close();
    }
    catch(Exception e){
        System.out.println("Invalid selection. Please input a valid number.");

    }

}
}