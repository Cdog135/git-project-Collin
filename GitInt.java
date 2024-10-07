import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GitInt implements GitInterface{

    //constructor for testing
    public GitInt (){}
    
    //adds in new files/directories, updates index
    public void stage(String filePath){
        File file = new File(filePath);
        if (!file.exists()){
            System.err.println("File/directory does not exist.");
            return;
        }
        try {
            Blob blob = new Blob(filePath);
            File git = new File ("git");
            if (!git.exists()){
                Git newGit = new Git();
            }
        } catch (IOException e) {
            System.err.println("blob could not be created");
        }
    }

    //new commit with the files/directoies that are currently staged
    public String commit(String author, String message){
        try {
            Commit commit = new Commit(author, message);
        } catch (IOException e) {
            System.err.println("Commit could not be completed");
        }
        try {
            return new String (Files.readAllBytes(Paths.get("git/HEAD")));
        } catch (IOException e) {
            System.err.println("Could not retrieve commit hash");
        }
        return "Commit failed";
    }

    //restoes the working directory (index) to what it was at this commit
    public void checkout(String commitHash) {
        //makes sure commit exists
        File commit = new File ("git/objects/"+commitHash);
        if (!commit.exists()){
            System.err.println("commit does not exist");
            return;
        }
        
        try (BufferedReader commitReader = Files.newBufferedReader(Paths.get("git/objects/"+commitHash))) {
            //gets the name of the tree file from the commit file
            String firstLine = commitReader.readLine();
            String [] parts = firstLine.split(" ");
            if (parts.length!=2){
                System.err.println("tree line is not formatted corectly/tree was not made. This is the line:" + firstLine);
                return;
            }
            String treeName = parts[1];

            //writes the tree contents/working directory to the index
            String treeContent = new String (Files.readAllBytes(Paths.get("git/objects/"+treeName)));
            BufferedWriter indexWriter = Files.newBufferedWriter(Paths.get("git/index/"));
            indexWriter.write(treeContent);
            indexWriter.close();
        } catch (IOException e) {
            System.err.println ("could not checkout the commit");
        }
    }
    
}
