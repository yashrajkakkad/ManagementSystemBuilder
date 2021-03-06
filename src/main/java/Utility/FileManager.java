package Utility;

import CodeGeneration.*;
import java.io.IOException;
import Picocog.*;
import java.io.File;
import java.io.FileWriter;

public class FileManager {
    
    public static void writeCode(String fileName, PicoWriter w) throws IOException {
        File writeFile = null;
        if (System.getProperty("os.name").startsWith("Windows")){
            writeFile = new File(EntityManager.getDirectoryName() + "\\" + fileName + ".java");
        } else {
            writeFile = new File(EntityManager.getDirectoryName() + "/" + fileName + ".java");
        }
        FileWriter out = new FileWriter(writeFile);
        out.write(w.toString());
        out.close();
    }
    
}
