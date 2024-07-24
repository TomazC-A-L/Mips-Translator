import java.io.*;

public class Reader {
    private String filename;

    public Reader(String filename)throws IOException{
        this.filename = filename;
        BufferedReader leitor = new BufferedReader(new FileReader(filename));
        String linha;

        while ((linha = leitor.readLine()) != null) {
            System.out.println(linha);
            Tipador.identify(linha);
        }
        leitor.close();
    }

    static String getRegis (String inst){ 
        int index = inst.indexOf(" ");
        String regis = inst.substring(index).replace(" ","");
        return regis;
    }

    static String getInstruction (String inst){
        String instruction = inst.split(" ")[0];
        return instruction;
    }
}