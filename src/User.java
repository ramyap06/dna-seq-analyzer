package src;

import java.io.*;

// Makes user objects with all info on the user

public class User {
    private String name; //name of the user
    private String username; //unique name to each user, also used to store files
    private String password; //password used to access user's account, if typed in wrong it is inaccessible
    private String fileName; //name of the file that user's data loaded from and saved into

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.fileName = "src/" + username + "Data.txt";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.fileName)))) {
            writer.printf("%s,%s,%s", this.name, this.username, this.password);
            writer.println();
            writer.flush();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
    }


}
