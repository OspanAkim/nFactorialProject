import java.io.*;
import java.util.*;

public class NoSQLDatabase {

    private static final String DB_DIR = "db/";
    private static final String DB_EXT = ".txt";

    // Create a new record in the database
    public static void create(String filename, String login, String password) {
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file, true);
            fw.write(login + ":" + password + "\n");
            fw.close();
            System.out.println("Record created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating record: " + e.getMessage());
        }
    }

    // Read a record from the database
    public static String read(String filename, String login) {
        String value = null;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts[0].equals(login)) {
                    value = parts[1];
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("filename not found.");
        }
        return value;
    }

    // Update a record in the database
    public static void update(String filename, String login, String password) {
        try {
            File file = new File(filename);
            File tempFile = new File(DB_DIR  + "tempFile" + DB_EXT);
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(login + ":")) {
                    bw.write(login + ":" + password + "\n");
                } else {
                    bw.write(line + "\n");
                }
            }
            br.close();
            bw.close();
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Record updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }

    // Delete a record from the database
    public static void delete(String filename, String login) {
        try {
            File file = new File(filename);
            File tempFile = new File(DB_DIR  + "tempFile" + DB_EXT);
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(login + ":")) {
                    bw.write(line + "\n");
                }
            }
            br.close();
            bw.close();
            file.delete();
            tempFile.renameTo(file);
            System.out.println("Record deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }
}
