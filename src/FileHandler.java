package src;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Handles saving and loading the entire system state.
 */
public class FileHandler {
    private static final String DATA_FILE = "ccrm_data.ser";

    public static void saveData(List<User> users, Map<String, Course> courses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(users);
            oos.writeObject(courses);
            System.out.println("[System] Data saved successfully.");
        } catch (IOException e) {
            System.out.println("[Error] Failed to save data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean loadData(List<User> users, Map<String, Course> courses) {
        File file = new File(DATA_FILE);
        if (!file.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<User> loadedUsers = (List<User>) ois.readObject();
            Map<String, Course> loadedCourses = (Map<String, Course>) ois.readObject();
            
            users.clear();
            users.addAll(loadedUsers);
            
            courses.clear();
            courses.putAll(loadedCourses);
            
            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[Error] Failed to load data: " + e.getMessage());
            return false;
        }
    }
}