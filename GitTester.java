import java.io.*;
import java.util.*;

public class GitTester {
    public static void main (String [] args) throws IOException {
        Git git = new Git();
        Blob blob = new Blob ("test.txt");
        File index = new File ("git/index");
        if (index.exists())
        {
            index.delete();
            index.createNewFile();
        }
        // System.out.println("Testing tree");
        // File file = new File ("testForTree");
        // file.mkdir();
        // try {
        //     FileWriter writer = new FileWriter("testForTree/test2.txt");
        //     writer.write("Big C in the house one");
        //     writer.close();
            
        //     System.out.println("Testing directory inside a directory");
        //     File file2 = new File ("testForTree/testForTree2");
        //     file2.mkdir();
    

        //     FileWriter writer2 = new FileWriter("testForTree/test3.txt");
        //     writer2.write("Test 2 bro");
        //     writer2.close();

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        Blob treeBlob = new Blob ("testForTree");
        // System.out.println ("Testing non existent file");
        // try {
        //     Blob nonExistentBlob = new Blob ("nonExistentFile.txt");
        // } catch (FileNotFoundException e) {
        //     System.out.println("Test passed");

        // }
        //creating a directory inside a directory
       


        //delete git 
        //System.out.println("Deleting git");
        //git.deleteGit();
    }

}
