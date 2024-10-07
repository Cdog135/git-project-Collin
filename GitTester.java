
import java.io.*;
import java.util.*;

public class GitTester {
    public static void main (String [] args) throws IOException {
        
        Git git = new Git();
        git.deleteGit();
        Git git2 = new Git();

        // Blob blob = new Blob ("test.txt");
        File index = new File ("git/index");
        if (index.exists()){
            index.delete();
            index.createNewFile();
        }
        // FileWriter indexWriter = new FileWriter("git/index");
        // indexWriter.write("hello");
        // indexWriter.close();
        // if (index.exists())
        // {
        //     index.delete();
        // }
        
        index.createNewFile();
        System.out.println("Testing tree");
        File file = new File ("testForTree");
        file.mkdir();
        try {
            File test2 = new File ("testForTree/test2.txt");
            if (!test2.exists()){
                test2.createNewFile();
            }
            FileWriter writer = new FileWriter("testForTree/test2.txt");
            writer.write("Big C in the house one");
            writer.close();
            
            File tree2 = new File("testForTree/testForTree2");
            if (!tree2.exists()){
                tree2.mkdirs();
            }
            System.out.println("Testing directory inside a directory");
            File file2 = new File ("testForTree/testForTree2");
            file2.mkdir();
    

            File test3 = new File("testForTree/test3.txt");
            if (!tree2.exists()){
                test3.createNewFile();
            }
            FileWriter writer2 = new FileWriter("testForTree/test3.txt");
            writer2.write("Test 2 bro");
            writer2.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //stages and commits folder
        GitInt tester = new GitInt();
        tester.stage("testForTree");
        tester.commit("nathaniel", "new commit");

        //stages and commits new file
        File newFile = new File ("newFile");
        if (!newFile.exists()){
            newFile.createNewFile();
        }
        tester.stage("newFile");
        tester.commit("nathaniel", "second commit");
        
        tester.checkout("7e08060d20d564b32a1a7d680a4300e5f65dd5a7");
    }
}
