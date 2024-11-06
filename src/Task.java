package src;

import java.util.*;
import java.time.LocalDate;

// Makes task objects for each task and stores relevant info

public class Task {
    private String name; //name of the task
    private LocalDate deadline; //when the task is due
    private static String[] status = {"Not Started", "In Progress", "Completed"}; //status of completion: will have 3 options, "not started", "in progress", and "complete"
    private static String[] priority = {"High", "Medium", "Low"}; //priority/importance of task: will have 3 options, "high", "medium", and "low"
    private static ArrayList<String> type; //user can add options to the drop-down for what type of tasks to select from

    public Task(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
        Task.type = new ArrayList<>();
    }

    // allow user to edit dropdown for type
    public static void addType(String input) {
        type.add(input);
    }

    public static void deleteType(String input) {
        type.remove(input);
    }

    public static void renameType(String input, String output) {
        int index = type.indexOf(input);
        type.set(index, output);
    }

    // returns a String format for all the info on this task as should be saved in a file
    @Override
    public String toString() {
        return "";
    }

    // getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String date) {
        if (date.charAt(0) == '0') {
            date = date.substring(1);
        }
        String[] tempArray = date.split("/");
        int year = Integer.parseInt(tempArray[2]);
        int month = Integer.parseInt(tempArray[1]);
        int day = Integer.parseInt(tempArray[0]);

        this.deadline = LocalDate.of(year, month, day);;
    }

    public static String[] getStatus() {
        return Task.status;
    }

    public static String[] getPriority() {
        return Task.priority;
    }
}
