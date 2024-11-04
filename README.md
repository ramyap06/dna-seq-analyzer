# honors-project

## Overall Idea
An application that allows the user to input a DNA sequence and the program will build a graphical DNA structure.  It will be color-coded and you can zoom in and scroll horizontally to see different parts of the DNA strand.  If making a DNA strand is too hard, then I will analyze the DNA, and given out a table of results such as the count of each base, or more complex, translate to RNA and the codons and amino acids present in percentage.

## Implementation of CS 180

### File I/O
Reads and parses a given file (either in .txt or FASTA format) as well as possibly rewrites the file to have two strands.  If a sequence is given and not a file, then it will be written into a default file and then parsed.

### Concurrency
Can use threads for all of the calculation and parsing.  After the sequence is ready to be made, the rest will be built in order from first to last.

### GUI
* Text box or upload file button to allow user to input DNA sequence/its file.
* Graphical display of DNA strand
* AND/OR Data visualizations about the DNA strand
* Buttons for Upload File, Search, Build, etc
