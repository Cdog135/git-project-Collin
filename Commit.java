import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

public class Commit {
    
    public Commit (String author, String message) throws IOException{
       //creates a commit file
       File commit = new File ("commit");
       BufferedWriter commitBW = new BufferedWriter(new FileWriter("commit", true));

        //creates a tree file of the commit
        File treeFile = new File ("commitTree");
        BufferedWriter commitTreeBW = new BufferedWriter(new FileWriter("commitTree", true));

        //writes prior files (from previous commit) into tree file of commit
        String previousCommitSha = new String (Files.readAllBytes(Paths.get("git/HEAD")));
        if (!previousCommitSha.equals("")){
            BufferedReader br = Files.newBufferedReader(Paths.get("git/objects/"+previousCommitSha));
            String line1 = br.readLine();
            String [] parts = line1.split(" ");
            String previousTreeSha1 = parts[1];
            if (previousTreeSha1 != null){
                String priorContent = new String (Files.readAllBytes(Paths.get("git/objects/"+previousTreeSha1)));
                commitTreeBW.write(priorContent);
            }
            br.close();
        }
        
        //writes index (new files) into tree file of commit
        File index = new File ("git/index");
        if (index.exists()){
            String indexContent = new String (Files.readAllBytes(Paths.get("git/index")));
            commitTreeBW.write(indexContent);
        }
        commitTreeBW.close();

        //writes root tree file of commit into the objects folder, using its sha1 as its name
        Blob treeBlob = new Blob("commitTree");

        //writes contents of commit
        //IMPLEMENT NEW LINES
        String treeFileContent = new String (Files.readAllBytes(Paths.get("commitTree")));
        commitBW.write ("tree: " + treeBlob.toSHA1(treeFileContent) + "\n");
        commitBW.write ("parent: " + previousCommitSha + "\n");
        commitBW.write ("author: " + author + "\n");
        commitBW.write ("date: " + "October 7, 2024" + "\n");
        commitBW.write ("message: " + message);
        commitBW.close();

        //writes commit file of commit into the objects folder, using its sha1 as its name
        Blob commitBlob = new Blob ("commit");
        
        //reads hash of commit into head
        String commitContent = new String (Files.readAllBytes(Paths.get("commit")));
        String hashCommit = commitBlob.toSHA1(commitContent);
        BufferedWriter bw = Files.newBufferedWriter(Paths.get("git/HEAD"));
        bw.write(hashCommit);
        bw.close();

        //deletes unnecessary files
        treeFile.delete();
        commit.delete();
        index.delete();
        index.createNewFile();
    }
}
