    import java.io.*;
    import java.util.ArrayList;
    import java.util.Scanner;

    public class Main {
        public static String filePath = "ENTERTXTHERE";
        public static ArrayList<String> toDoList = readTextFile(filePath);

        public static void main(String[] args) {
            mainMenu();  // Start the main menu loop
        }

        public static void mainMenu() {
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("\nEnter your option:");
                System.out.println("1. Add Task");
                System.out.println("2. Delete Task");
                System.out.println("3. Edit Task");
                System.out.println("4. List Tasks");
                System.out.println("5. Exit");

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Text to be added: ");
                    toDoList.add(sc.nextLine());
                }
                else if (choice == 2) {
                    printArrayList(toDoList);
                    System.out.print("Task to be deleted: ");
                    int indexToDelete = sc.nextInt() - 1;
                    sc.nextLine();
                    if (indexToDelete >= 0 && indexToDelete < toDoList.size()) {
                        toDoList.remove(indexToDelete);
                    } else {
                        System.out.println("Invalid index.");
                    }
                }
                else if (choice == 3) {
                    printArrayList(toDoList);
                    System.out.print("Task to be edited: ");
                    int editedTask = sc.nextInt() - 1;
                    sc.nextLine();  // Consume newline
                    if (editedTask >= 0 && editedTask < toDoList.size()) {
                        System.out.println("Previous text: " + toDoList.get(editedTask));
                        System.out.print("New text: ");
                        toDoList.set(editedTask, sc.nextLine());
                    } else {
                        System.out.println("Invalid index.");
                    }
                }
                else if (choice == 4) {
                    printArrayList(toDoList);
                }
                else if (choice == 5) {
                    fileWriter(filePath);
                    System.out.println("bye");
                    break;
                }
                else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
            sc.close();
        }

        public static ArrayList<String> readTextFile(String filePath) {
            ArrayList<String> toDoList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    toDoList.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return toDoList;
        }

        public static void printArrayList(ArrayList<String> list) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + list.get(i));
            }
        }
        public static void fileWriter(String filePath) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, false))) {
                for (String task : toDoList) {
                    bufferedWriter.write(task);
                    bufferedWriter.newLine();
                }
                System.out.println("File written successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file: " + e.getMessage());
            }
        }

    }
