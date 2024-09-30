import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Git {
    File gitFolder;
    File objectsFolder;
    File indexFile;
    File newIndexFile;

    public Git() {
        gitFolder = new File("git/");
        objectsFolder = new File("git/objects/");
        indexFile = new File("git/index/");
        newIndexFile = new File("git/newIndex");

        if (gitFolder.exists() && objectsFolder.exists() && indexFile.exists()) {
            System.out.println("Git Repository already exists");
        } else {
            if (!gitFolder.exists()) {
                gitFolder.mkdir();
            }
            if (!objectsFolder.exists()) {
                objectsFolder.mkdir();
            }
            if (!indexFile.exists()) {
                try {
                    indexFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public boolean deleteGit() {
        File git = new File("git/");
        if (git.exists()) {
            deleteFolder(git);
            git.delete();
        }
        if (!git.exists())
            return true;
        else
            return false;
    }

    public void deleteFolder(File folder) {
        if (!folder.exists()){
            return;
        }
        if (folder.isDirectory())
        {
            File [] files = folder.listFiles();
            if (files != null)
            {
                for (File file : files)
                {
                    if(file.isDirectory())
                    {
                        deleteFolder(file);
                    }
                    if (!file.delete())
                    {
                        System.out.println("Failed to delete file: " + file);
                    }
                }
            }

        }
        
        }
        
        private static String byteToHex(final byte[] hash) { // shamelessly copied from stack overflow:
            // https://stackoverflow.com/questions/4895523/java-string-to-sha1
            Formatter formatter = new Formatter();
            for (byte b : hash) {
            formatter.format("%02x", b);
            }
            String result = formatter.toString();
            formatter.close();
            return result;
        }
        public static String calculateTreeSHA1(String treeContent)
        {
            String sha1 = "";
            try
            {
                MessageDigest encrypter = MessageDigest.getInstance("SHA-1");
                encrypter.reset();
                encrypter.update(treeContent.getBytes("UTF-8"));
                sha1 = byteToHex(encrypter.digest());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return sha1;
        }
        


    }
    
