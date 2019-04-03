package CodeGeneration;

import java.io.IOException;
import java.sql.SQLException;

public class TestGenerateDatabaseUtil {
    public static void main(String[] args) throws IOException, SQLException {
        GenerateDatabaseUtil.generateCode("Doctor Management System");
    }
}
