/*
 *  Está classe é a responsavel por fazer a codificação correta
 * das instruções do tipo i.
 *  Ela possui uma lista com instruções que não se encaixam no 
 * padraão de codificação normal das instruções tipo i. Caso a 
 * instrução da linha atual esteja contida nesta lista, a função
 * chama o método callException para lidar com o tipo de excessão
 */
import java.util.ArrayList;
import java.util.List;

public class TipoI {
    private static List<String> exception = new ArrayList<>();

    public static String binaryString(String inst, String[] reg,boolean imed) {
        exception.add("bgez");
        exception.add("bltz");
        exception.add("bltzal");
        exception.add("bgezal");
        exception.add("blez");
        exception.add("bgtz");
        
        String binary = Tabela.geti(inst);
        if (exception.contains(inst)) {
            reg = Tradutor.Traduzir(reg);
            binary = callExceptions(inst, reg);
            
        } else {
            if (imed) {
                reg = imediato(reg);
                for (int i = 0; i < reg.length; i++) {
                    binary += reg[i];
                }
            } else {
                reg = normal(reg);
                for (int i = 0; i < reg.length; i++) {
                    binary += reg[i];
                }
            }
        }
        return binary;
    }

    // Reposiciona os registradores para enviar para a classe tradutor e receber um resultado
    // ja formatado
    private static String[] normal(String[] registradores) {
        String aux = registradores[0];
        registradores [0] = registradores[1];
        registradores [1] = aux;

        registradores = Tradutor.Traduzir(registradores);
        return registradores;
    }
    
    // Reposiciona os registradores para enviar para a classe tradutor e receber um resultado
    // ja formatado
    private static String[] imediato(String[] registradores) {
        String aux = registradores[0];
        registradores[0] = registradores[2];
        registradores[2] = registradores[1];
        registradores[1] = aux;

        registradores = Tradutor.Traduzir(registradores);
        return registradores;
    }

    /*
     *  Este método lida com as exceções. Ele recebe a instrução, o registrador, e OP code
     * da instrução.
     *  O primeiro passo é identificar qual excessão está se tratando. Feito isso, o método
     * formata corretamente a posição dos registradores e completa espaços vazios para garantir
     * que o return tenha 32 bits.
     * 
     * @param: inst, reg[], binInst
     */
    public static String callExceptions(String inst, String[] reg) {
        String binary = Tabela.geti(inst);
        if(inst.equals("bltz")){
            binary = "000001" + reg[0] + "00000" + reg[1];
        } else if (inst.equals("bgez")) {
            binary = "000001" + reg[0] + "00001" + reg[1];
        } else if (inst.equals("bltzal")) {
            binary = "000001" + reg[0] + "10000" + reg[1];
        } else if (inst.equals("bgezal")) {
            binary = "000001" + reg[0] + "10001" + reg[1];
        } else if (inst.equals("blez") || inst.equals("bgtz")) {
            binary = Tabela.geti(inst) + reg[0] + "00000" + reg[1];
        }
        return binary;
    }
}