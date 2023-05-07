import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DB_EXT = ".txt";
    private static String filename = "MyTableDataBase" + DB_EXT;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to My NoSQL Database!\n\n");
        boolean running = true;
        while (running) {
            System.out.println("\nPlease enter a number which you want to use \n 1 -- CREATE\n 2 -- READ\n 3 -- UPDATE\n 4 -- DELETE \n 5 -- QUIT");
            String command = scanner.nextLine();
            switch (Integer.parseInt(command)) {
                case 1:
                    createRecord(scanner);
                    break;
                case 2:
                    readRecord(scanner);
                    break;
                case 3:
                    updateRecord(scanner);
                    break;
                case 4:
                    deleteRecord(scanner);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void createRecord(Scanner scanner) {
        System.out.println("\nPlease enter the login:");
        String key = scanner.nextLine();
        System.out.println("Please enter the password:");
        String value = scanner.nextLine();
        NoSQLDatabase.create(filename, key, value);
        System.out.println("Record created!");
    }

    private static void readRecord(Scanner scanner) {
        System.out.println("\nPlease enter the record login:");
        String key = scanner.nextLine();
        String value = NoSQLDatabase.read(filename, key);
        if (value != null) {
            System.out.println("password: " + value);
        } else {
            System.out.println("Record not found!");
        }
    }

    private static void updateRecord(Scanner scanner) {
        System.out.println("\nPlease enter the record login:");
        String key = scanner.nextLine();
        System.out.println("Please enter the new password:");
        String value = scanner.nextLine();
        NoSQLDatabase.update(filename, key, value);
        System.out.println("Record updated!");
    }

    private static void deleteRecord(Scanner scanner) {
        System.out.println("\nPlease enter the record login:");
        String key = scanner.nextLine();
        NoSQLDatabase.delete(filename, key);
        System.out.println("Record deleted!");
    }
}
