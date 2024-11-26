package src;

public class Database {
    private String data;

    public Database(String data) {
        this.data = data;
    }

    public String reverseSeq() {
        StringBuilder reverseString = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char compare = data.charAt(i);
            switch (compare) {
                case 'A' -> reverseString.append("T");

                case 'T' -> reverseString.append("A");

                case 'G' -> reverseString.append("C");

                case 'C' -> reverseString.append("G");
            }
        }
        return String.valueOf(reverseString);
    }

    public String rnaSeq() {
        StringBuilder rnaString = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char compare = data.charAt(i);
            switch (compare) {
                case 'A' -> rnaString.append("U");

                case 'T' -> rnaString.append("A");

                case 'G' -> rnaString.append("C");

                case 'C' -> rnaString.append("G");
            }
        }
        return String.valueOf(rnaString);
    }

    public String codonSearch(String input) {
        String codon = input.toUpperCase();

        int counter = 0;
        for (int i = 0; i < (data.length() - 2); i++) {
            if (data.charAt(i) == codon.charAt(0)) {
                if (data.charAt(i + 1) == codon.charAt(1)) {
                    if (data.charAt(i + 2) == codon.charAt(2)) {
                        counter++;
                    }
                }
            }
        }
        return String.valueOf(counter);
    }

    public String bpPercentage(String input) {
        String result = "";
        if (input.contains(",")) {
            String[] bases = input.split(",");
            result = bpPercentage(bases);
        } else {
            char base = input.toUpperCase().charAt(0);
            result = bpPercentage(base);
        }

        return result;
    }

    public String bpPercentage(char input) {
        double totalBaseCount = data.length();
        double inputBaseCount = 0.0;

        for (int i = 0; i < data.length(); i++) {
            if (input == data.charAt(i)) {
                inputBaseCount++;
            }
        }
        double percent = (inputBaseCount / totalBaseCount) * 100;
        return String.format("%s:%.2f", input, percent);
    }

    public String bpPercentage(String[] input) {
        double totalBaseCount = data.length();
        double inputBaseCount = 0.0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < data.length(); j++) {
                if (input[i].charAt(0) == data.charAt(j)) {
                    inputBaseCount++;
                }
            }
            double percent = (inputBaseCount / totalBaseCount) * 100;
            result.append(String.format("%s:%.2f", input[i], percent));
            if (i != input.length - 1) {
                result.append(",");
            }
        }

        return String.valueOf(result);
    }
}
