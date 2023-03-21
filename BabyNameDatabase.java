/**
 * Manages the list of BabyNames as well as reading and writing to the files
 * with the BabyNames.
 * @author __________
 */
import java.util.*;
import java.io.*;

public class BabyNameDatabase {
    
    // TODO 1: Write the code below this line.
    private String databaseFileName;
    private ArrayList<BabyName> records;
    private File fileRecord;
    
    public BabyNameDatabase(String databaseFileName){
        this.databaseFileName = databaseFileName;
        this.records = new ArrayList<BabyName>();
        this.fileRecord = new File(this.databaseFileName);
    }

  
    public ArrayList<BabyName> getRecords(){
        return records;
    }

    /**
     * Reads the csv file that holds the baby name birth data and updates
     * the records variable.
     * @param filename name of the file to read from
     * @throws IOException could not find or close file
     */
    public void readRecordsFromBirthDataFile(String filename) throws IOException {
        // TODO 2: Write the code below this line.

        //variables
        Scanner fileNameScanner = new Scanner(filename);
        File birthDataFile = new File(filename);
        Scanner birthDataFileScanner = new Scanner(birthDataFile);
        int year;
        char[] fileLineCharArray;

        //gets year of file
        fileNameScanner.useDelimiter("[^0-9]+");
        if(fileNameScanner.hasNextInt()){year = fileNameScanner.nextInt();}
        else{year = 0;}



        while(birthDataFileScanner.hasNextLine()){
            //gets current line and changes it to char array to check if the first value of the csv line is a digit
            String currentLine = birthDataFileScanner.nextLine();
            fileLineCharArray = currentLine.toCharArray();
            if(Character.isDigit(fileLineCharArray[0])){
                //sends stuff to process method, the year of file and the lines of the file starting with ints
                processLineFromBirthDataFile(currentLine, year);
            }
        }
    }

    /**
     * Processes one formatted line of the csv file into baby names and
     * adds/updates the records array.
     * @param line the string holding the line from the csv file
     * @param year when the data is from
     */
    public void processLineFromBirthDataFile(String line, int year){
    // TODO 3: Write the code below this line.
        //process stuff and check for duplicate names to change to neutral
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter(",");
        ArrayList<String> lineArrayList = new ArrayList<>();
        while(lineScanner.hasNextLine()){
            lineArrayList.add(lineScanner.nextLine());
        }
        for(int i = 0; i < lineArrayList.size(); i++) {
            if (lineArrayList.get(i).contains("\"")) {
                String correctNum = lineArrayList.get(i) + lineArrayList.get(i++);
                lineArrayList.remove(i);
                lineArrayList.remove(i++);
                lineArrayList.add(i, correctNum);
            }
        }
        lineArrayList.remove(0);
        //index should only be, [male name], [male num], [female name], [female num] by this point


        //use delimiter "," to split stuff and check for double quotes for numbers above 1000 then treat differently
        
    }
}