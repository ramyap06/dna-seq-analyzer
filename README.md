# DNA Sequence Analyzer

## Overall Idea
An application that allows the user to input a DNA sequence and the sequence can be analyzed in different ways.  Uses solely Java and Java Swing.

1. If prompted, the program will return a reverse complement sequence (the hypothetical second strand) for the sequence.
2. If prompted, the program can also return a messenger RNA sequence that would be used in protein synthesis.
3. Can return the percentage of a certain base pair in the sequence.
4. If prompted with 3 base pairs (also known as a codon), the program will return a count of how many times this three letter pair was found in the sequence.

## Implementation of CS 180 - Object Oriented Programming

### File I/O
* data can be saved into a separate file containing all the analysis the user has prompted
* this file will be saved on a local machine in the same folder as the source code

### Concurrency
* threads are used to do all the backend work of the user interface efficiently

### GUI
* JTextArea to allow user to input DNA sequence
* JLabel to make a title
* JButton for the analyze button
* JComboBox used to make a dropdown of various analysis prompts
* JOptionPane used for all successfully completed messages, error messages, and further prompting based on the original prompt

### Sources for DNA Sequences
* sample1: https://www.ncbi.nlm.nih.gov/nuccore/NM_001382186.1?report=fasta
* sample2: https://www.ncbi.nlm.nih.gov/nuccore/XM_024727368.1?report=fasta
* I used these examples to test the functionality of this program.
