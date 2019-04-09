package Utility;

import CodeGeneration.*;
import java.io.IOException;
import Picocog.*;
import java.io.File;
import java.io.FileWriter;

public class FileManager {
    
    public static void writeCode(String fileName, PicoWriter w) throws IOException {
        File writeFile = new File(EntityManager.getDirectoryName() + "\\" + fileName);
        FileWriter out = new FileWriter("\\generated\\" + fileName + ".java");
        out.write(w.toString());
        out.close();
    }
}
