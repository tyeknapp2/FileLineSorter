import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.ArrayList;

/**
 * FileLineLengthSorter
 */
public class FileLineLengthSorter {
  public static void main(String[] args) {
    if (args.length <= 1 || args[0].equals("-h")) {
      System.out.println("   Proper usage:\t$ java FileLineLengthSorter {-n|-o} fullFilePath [numLinesSkipped]");
      System.out.println(
          "----------------------------------------------------------------------------------------------------");
      System.out.println("\t     -n:\tCreate a new file in the same location with sorted appended to the file name");
      System.out.println("\t     -o:\tOverwrite the original file with the new sorted data");
      System.out.println("   fullFilePath:\tThe full path to the file to be sorted");
      System.out.println("numLinesSkipped:\tNumber of lines for the unsorted header. Optional");
      System.out.println(
          "----------------------------------------------------------------------------------------------------");
      System.out.println("Type:  $java FileLineLengthSorter -h [...]^  to bring up this screen");
      System.exit(0);
      return;
    }

    ArrayList<String> skippedLines = new ArrayList<String>();
    ArrayList<String> linesToSort = new ArrayList<String>();
    try {
      File fileToSort = new File(args[1]);
      Scanner scan = new Scanner(fileToSort);

      File fileToWrite = args[1].equals("-o") ? fileToSort
          : new File(args[1].substring(0, args[1].lastIndexOf(".")) + "(Sorted)"
              + args[1].substring(args[1].lastIndexOf(".")));
      PrintStream stream = new PrintStream(fileToWrite);
      System.setOut(stream);

      for (int i = 0; i < ((args.length == 3) ? Integer.parseInt(args[2]) : -1); i++)
        skippedlines.add(scan.nextLine());

      
    } catch (FileNotFoundException e) {
      System.out.println("Please enter a valid file.");
      System.exit(1);
      return;
    }

  }
}
