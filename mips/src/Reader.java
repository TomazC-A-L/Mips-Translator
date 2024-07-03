import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Reader {
    private String filename;
    private String content[];
    private String elements[];

    public Reader(String filename)throws IOException{
        this.filename = filename;
        FileReader leitor = new FileReader(filename);
        BufferedReader reading = new BufferedReader(leitor);
        content = reading.readLine().split("\n");
        leitor.close();

        elements = content[0].split(",");
    }
}
