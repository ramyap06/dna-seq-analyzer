package src;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private String fileName;
    private ArrayList<String> data;

    public Database(String fileName) {
        this.data = new ArrayList<>();
        this.fileName = fileName;
        loadData();
    }

    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/" + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong sorry!");
        }
    }

    public void reverseSeq() {
        ArrayList<String> reverseArray = new ArrayList<>();
        StringBuilder reverseString = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length(); j++) {
                char compare = data.get(i).charAt(j);
                switch (compare) {
                    case 'A' -> reverseString.append("T");

                    case 'T' -> reverseString.append("A");

                    case 'G' -> reverseString.append("C");

                    case 'C' -> reverseString.append("G");
                }
            }
            reverseArray.add(String.valueOf(reverseString));
            reverseString.setLength(0);
        }

        //print to somewhere?
    }

    public void rnaSeq() {
        ArrayList<String> rnaArray = new ArrayList<>();
        StringBuilder rnaString = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length(); j++) {
                char compare = data.get(i).charAt(j);
                switch (compare) {
                    case 'A' -> rnaString.append("U");

                    case 'T' -> rnaString.append("A");

                    case 'G' -> rnaString.append("C");

                    case 'C' -> rnaString.append("G");
                }
            }
            rnaArray.add(String.valueOf(rnaString));
            rnaString.setLength(0);
        }

        //print to somewhere?
    }

    public boolean codonSearch(String codon) {
        codon = codon.toUpperCase();
        if (codon.length() != 3) {
            return false;
        }

        int counter = 0;
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length(); j++) {
                if (j == (data.get(i).length() - 1)) {
                    if (data.get(i).charAt(j) == codon.charAt(0)) {
                        if (data.get(i + 1).charAt(0) == codon.charAt(1)) {
                            if (data.get(i + 1).charAt(1) == codon.charAt(2)) {
                                counter++;
                            }
                        }
                    }
                }

                if (j == (data.get(i).length() - 2)) {
                    if (data.get(i).charAt(j) == codon.charAt(0)) {
                        if (data.get(i).charAt(j + 1) == codon.charAt(1)) {
                            if (data.get(i + 1).charAt(0) == codon.charAt(2)) {
                                counter++;
                            }
                        }
                    }
                }

                if (data.get(i).charAt(j) == codon.charAt(0)) {
                    if (data.get(i).charAt(j + 1) == codon.charAt(1)) {
                        if (data.get(i).charAt(j + 2) == codon.charAt(2)) {
                            counter++;
                        }
                    }
                }
            }
        }

        if (counter == 0) {
            System.out.println("No results found.");
            return false;
        } else {
            System.out.printf("Codon was found %d times\n", counter);
            return true;
        }
    }

}
