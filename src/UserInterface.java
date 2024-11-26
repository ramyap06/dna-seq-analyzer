package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class UserInterface implements Runnable {

    private JTextArea inputSequence;
    private JButton analyzeButton; // user clicks this button after copy-pasting a sequence and specifying an action
    private JComboBox<String> analyzeOptions;
    private Database database;
    private File saveFile;
    private boolean fileExists;

    public UserInterface() {
        this.inputSequence = null;
        this.analyzeButton = null;
        this.analyzeOptions = null;
        this.database = null;
        this.saveFile = null;
        this.fileExists = false;
    }

    public void handleAnalysis() {
        String sequence = inputSequence.getText().toUpperCase();
        String selectedOption = (String) analyzeOptions.getSelectedItem();
        String result = "";
        String toSave = "";

        // TEST
        System.out.println("INPUT SEQUENCE:\n" + sequence);
        System.out.println("SELECTED OPTION:\n" + selectedOption);
        //TEST

        database = new Database(sequence);

        if (sequence.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No DNA sequence was entered", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (selectedOption == null) {
            JOptionPane.showMessageDialog(null, "No option was selected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (selectedOption) {
            case "Double Strand" -> {
                result = database.reverseSeq();

                //TEST
                System.out.println("RESULT:\n" + result);
                //TEST

                toSave = displayResult(result, selectedOption);
            }
            case "mRNA Sequence" -> {
                result = database.rnaSeq();

                //TEST
                System.out.println("RESULT:\n" + result);
                //TEST

                toSave = displayResult(result, selectedOption);
            }
            case "Base Pair Percentage" -> {
                String[] options = {"A", "T", "G", "C", "All Bases"};
                String input = "";
                JCheckBox[] checkBoxes = new JCheckBox[options.length];
                JPanel bpPanel = new JPanel(new GridLayout(options.length, 1));
                for (int i = 0; i < options.length; i++) {
                    checkBoxes[i] = new JCheckBox(options[i]);
                    bpPanel.add(checkBoxes[i]);
                }
                int output = JOptionPane.showConfirmDialog(null, bpPanel, "Select the base(s):", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (output == JOptionPane.OK_OPTION) {
                    StringBuilder bases = new StringBuilder();
                    boolean anySelected = false;
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            bases.append(checkBox.getText()).append(", ");
                            anySelected = true;
                        }
                    }
                    if (anySelected) {
                        bases.setLength(bases.length() - 2);
                    }
                    input = bases.toString();
                } else {
                    JOptionPane.showMessageDialog(null, "No options selected.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                result = database.bpPercentage(input);

                //TEST
                System.out.println("RESULT:\n" + result);
                //TEST

                toSave = displayResult(result, input, "Base Pair Percentage");
            }
            case "Find Codon" -> {
                String codon = JOptionPane.showInputDialog(null, "Enter the codon (3 bases):");
                if (codon != null && codon.length() == 3) {
                    result = database.codonSearch(codon);

                    //TEST
                    System.out.println("RESULT:\n" + result);
                    //TEST

                    toSave = displayResult(result, codon, "Codon Search");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid codon input. Please enter a 3-base codon.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        int output = JOptionPane.showConfirmDialog(null, "Would you like to save these results in a file?");
        if (output == JOptionPane.YES_OPTION) {
            saveToFile(sequence, toSave);
        }
    }

    public String displayResult(String result, String option) {
        String displayed = "";

        if (result.length() > 68) {
            ArrayList<String> breakSequence = new ArrayList<>();
            int counter = 0;
            StringBuilder resultBuilder = new StringBuilder();

            for (int i = 1; i < result.length(); i++) {
                if (i % 68 == 0) {
                    breakSequence.add(result.substring((68 * counter) + 1, i + 1));
                    counter++;
                }
            }
            for (String seq : breakSequence) {
                resultBuilder.append(seq).append("\n");
            }

            result = resultBuilder.toString();
        }

        //TEST
        System.out.println("RESULT FOR DISPLAYING RESULTS:\n" + result);
        System.out.println("OPTION FOR DISPLAYING RESULTS:\n" + option);
        //TEST

        switch (option) {
            case "Double Strand" -> {
                JOptionPane.showMessageDialog(null, "Here is the double strand for the given sequence:\n" + result);
                displayed = "Double Strand Results:\n" + result + "\n\n";
            }
            case "mRNA Sequence" -> {
                JOptionPane.showMessageDialog(null, "Here is the mRNA strand for the given sequence:\n" + result);
                displayed = "mRNA Sequence Results:\n" + result + "\n\n";
            }
        }
        return displayed;
    }

    public String displayResult(String result, String input, String option) {
        String displayed = "";

        switch (option) {
            case "Codon Search" -> {
                JOptionPane.showMessageDialog(null, "Codon " + input + " was found " + result + " times in the given sequence");
                displayed = "Codon Search Results:\n\n" + "Codon: " + input + "\nSearch Results: " + result + " times\n\n";
            }
            case "Base Pair Percentage" -> {
                StringBuilder resultBuilder = new StringBuilder();
                StringBuilder displayBuilder = new StringBuilder("Base Pair Percentage:\n\n");
                if (result.contains(",")) {
                    String[] resultList = result.split(",");
                    String[] inputList = input.split(",");
                    for (int i = 0; i < resultList.length; i++) {
                        resultBuilder.append(resultList[i]).append("\n");
                        displayBuilder.append("Base Pair: ").append(inputList[i]).append("\n");
                        displayBuilder.append("Percentage: ").append(resultList[i]).append("%").append("\n\n");
                    }
                    result = resultBuilder.toString();
                } else {
                    displayBuilder.append("Base Pair: ").append(input).append("\n");
                    displayBuilder.append("Percentage: ").append(result).append("%").append("\n\n");
                }
                JOptionPane.showMessageDialog(null, "The percentages of each base pair in the given sequence are as follows:\n" + result);
                displayed = displayBuilder.toString();
            }
        }

        //TEST
        System.out.println("RESULT FOR DISPLAYING RESULTS:\n" + result);
        System.out.println("INPUT FOR DISPLAYING RESULTS:\n" + input);
        System.out.println("OPTION FOR DISPLAYING RESULTS:\n" + option);
        //TEST

        return displayed;
    }

    public void saveToFile(String originalSequence, String toSave) {
        String filename = "";
        if (!this.fileExists) {
            filename = JOptionPane.showInputDialog(null, "Enter a file name: (must be a .txt file)");
            saveFile = new File(filename);
            this.fileExists = true;
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(saveFile)))) {
            writer.write(toSave);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Results have been successfully saved!");
    }

    public void run() {
        JFrame frame = new JFrame("DNA Sequence Analyzer");

        Container content = frame.getContentPane();
        content.setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Welcome to the DNA Sequence Analyzer!", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Greyscale", java.awt.Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        content.add(titleLabel, BorderLayout.NORTH);

        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        String[] options = {"Double Strand", "mRNA Sequence", "Base Pair Percentage", "Find Codon"};
        analyzeOptions = new JComboBox<>(options);
        analyzeOptions.setSelectedIndex(0);

        analyzeButton = new JButton("Analyze");
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnalysis();
            }
        });

        inputSequence = new JTextArea(4, 68);
        inputSequence.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(inputSequence);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel1.add(inputSequence);
        panel2.add(analyzeOptions);
        panel3.add(analyzeButton);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.NORTH);
        frame.add(panel3, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new UserInterface());
    }

}
