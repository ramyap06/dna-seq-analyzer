# honors-project

## Overall Idea
An application that allows the user to input a DNA sequence and the sequence can be analyzed in different ways. 1) If prompted, the program will return a reverse complement sequence (the hypothetical second strand) for the sequence.  2) If prompted, the program can also return a messenger RNA sequence that would be used in protein synthesis.  3) Can return the percentage of a certain base pair in the sequence.  4) If prompted with a 3 base pairs, the program will return a count of how many times this three letter pair was found in the sequence (& if possible, highlight where it occurs in the sequence).

## Implementation of CS 180

### File I/O
* reads and parses a given file (in .txt format) - these will be given since actual DNA sequence files are written in FASTA format and are incredibly long
* if a sequence is given and not a file, then it will be written into a default file and then parsed
* data can be exported into a separate file containing all the analysis the user has prompted

### Concurrency
Can use threads for the calculation and parsing.  After the sequence is ready to be made, the rest will be built in order from first to last.

### GUI
* Text box or upload file button to allow user to input DNA sequence
* Graphical display of DNA strand
* AND/OR Data visualizations about the DNA strand
* Buttons for Upload File, Search, Build, etc

### Sources for DNA Sequences
* sample1: https://www.ncbi.nlm.nih.gov/nuccore/CP002684.1?report=fasta
* sample2: https://www.ncbi.nlm.nih.gov/nuccore/GU170821.1?report=fasta
* 