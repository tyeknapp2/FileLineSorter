import java.util.Scanner;
import java.awt.List;
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

      for (int i = 0; i < ((args.length == 3) ? Integer.parseInt(args[2]) : -1); i++) {
        skippedlines.add(scan.nextLine());
        if (!scan.hasNext()) {
          for (String s : skippedLines)
            System.out.println(s);
          System.exit(0);
          return;
        }
      }
      while (scan.hasNext())
        linesToSort.add(scan.nextLine());

      linesToSort = mergeSort(linesToSort);

      skippedLines.addAll(linesToSort);
      for (String s : skippedLines)
        System.out.println(s);

    } catch (FileNotFoundException e) {
      System.out.println("Please enter a valid file.");
      System.exit(1);
      return;
    }

  }

  private static ArrayList<String> mergeSort(ArrayList<String> in) {
    if (in.size() == 1) {
      return in;
    }
    int mid = in.size() / 2, ia = 0, ib = 0;
    ArrayList<String> a = mergeSort(sublist(in, 0, mid)), b = mergeSort(sublist(in, mid, in.size())),
        sorted = new ArrayList<String>();
    while (ia < a.size() || ib < b.size()) {
      if (ib == b.size() || (ia != a.size() && (a.get(ia).length() < b.get(ib).length()
          || (a.get(ia).length() == b.get(ib).length() && a.get(ia).compareTo(b.get(ib)) <= 0)))) {
        sorted.add(a.get(ia++));
      } else {
        sorted.add(b.get(ib++));
      }
    }
    return sorted;
  }

  private static ArrayList<String> sublist(ArrayList<String> in, int start, int end) {
    ArrayList<String> s = new ArrayList<String>();
    for (int i = start; i < end; i++) {
      s.add(in.get(i));
    }
    return s;
  }
}
