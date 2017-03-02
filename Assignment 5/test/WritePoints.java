import java.nio.file.*;
import java.io.*;

public class WritePoints{
    public static void main(String[] args) {
        Path path = Paths.get("points.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 1; i <= 25; i++) {
                for (int j = 1; j <= 25; j++) {
                    String s = i + " " + j;
                    writer.write(s, 0, s.length());
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}