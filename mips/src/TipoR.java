/*
 *  Está classe é a responsavel por fazer a codificação correta
 * das instruções do tipo R.
 *  Ela possui uma lista com instruções que não se encaixam no 
 * padrão de codificação normal das instruções tipo R. Caso a 
 * instrução da linha atual esteja contida nesta lista, a função
 * chama o método callException para lidar com o tipo de excessão
 */
import java.util.ArrayList;
import java.util.List;

public class TipoR {
    private static List<String> exceptions = new ArrayList<>();
    private static String binary;

    public static String binaryString (String instruction, String[] reg) {
        exceptions.add("sll");
        exceptions.add("srl");
        exceptions.add("sra");
        exceptions.add("sllv");
        exceptions.add("srlv");
        exceptions.add("srav");
        exceptions.add("mfhi");
        exceptions.add("mthi");
        exceptions.add("mflo");
        exceptions.add("mtlo");
        exceptions.add("mult");
        exceptions.add("multu");
        exceptions.add("div");
        exceptions.add("divu");
        exceptions.add("jr");
        exceptions.add("jarl");
        
        String binCode = Tabela.getr(instruction);
        if (exceptions.contains(instruction)) {
            reg = Tradutor.Traduzir(reg);
            binary = "000000";
            binary = callExceptions(instruction, reg, binCode);
            return binary;
        } else {
            String binary = "000000";
            String[] resposta = new String [reg.length];
            resposta[0] = reg[1];
            resposta[1] = reg[2];
            resposta[2] = reg[0];
            resposta = Tradutor.Traduzir(resposta);
    
            for (int i = 0; i < 3; i++) {
                binary += resposta[i];
            }
            binary += "00000" + binCode;
            return binary;
        }
    }

    /*
     *  Este método lida com as exceções. Ele recebe a instrução, o registrador, e OP code
     * da instrução.
     *  O primeiro passo é identificar qual excessão está se tratando. Feito isso, o método
     * formata corretamente a posição dos registradores e completa espaços vazios, ou formata
     * o tamanho da string para caber na instrução de 32bits, (ja que a classe tradutor formata
     * todos imediatos em 16 bits)
     * 
     * @param: inst, reg[], binInst
     */
    private static String callExceptions(String instruction, String reg[], String binInst) {
        String binary = "000000";

        if (instruction.equals("sll") || instruction.equals("srl") || instruction.equals("sra")){
            reg[2] = reg[2].substring(11);
            binary = binary + "00000" + reg[1] + reg[0] + reg[2] + binInst;
        } 
        else if (instruction.equals("sllv") || instruction.equals("srlv") || instruction.equals("srav")) {
            reg[2] =reg[2].substring(11);
            binary = binary + reg[1] + reg[2] + reg[0] + "00000" + binInst;
        }
        else if (instruction.equals("mfhi") || instruction.equals("mflo")) {
            binary = binary + "0000000000" + reg[0] + "00000" + binInst;
        }
        else if (instruction.equals("mthi") || instruction.equals("mtlo") || instruction.equals("jr")) {
            binary = binary + reg[0] + "000000000000000" + binInst;
        }
        else if (instruction.equals("mult") || instruction.equals("multu") || instruction.equals("div") || instruction .equals("divu")) {
            binary = binary + reg[1] + reg[0] + "00000" + "00000" + binInst;
        }
        else if (instruction.equals("jarl")) {
            binary = binary + reg[1] + "00000" + reg[0] + "00000" + binInst;
        }
        return binary;
    }
    
}
