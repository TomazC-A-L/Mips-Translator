/*
 *  Esta classe é responsável por input/output de arquivos.
 *  Além disso, ela também é responsável por identificar qual o tipo de
 * instrução que está sendo passada na linha do arquivo, e chamar a 
 * classe correspondente ao tipo de instrução.
 */

 import java.io.*;
 public class Reader {
     //Função de input e output
     //@param: filename //recebe o nome do arquivo desejado
     public Reader(String filename)throws IOException{
         BufferedReader leitor = new BufferedReader(new FileReader(filename));
         BufferedWriter escrivo = new BufferedWriter(new FileWriter("binary.txt"));
         String linha;
 
         while ((linha = leitor.readLine()) != null) {
             escrivo.write(identify(linha) + "\n");
             System.out.println(linha);
         }
         escrivo.close();
         leitor.close();
     }
 
     //Esta função retorna apenas a parte dos registradores da instrução passada.
     static String getRegis (String inst){ 
         int index = inst.indexOf(" ");
         String regis = inst.substring(index).replace(" ","");
         return regis;
     }
 
     //Esta função formata a subtring de registradores, facilitando a leitura e tradução em outras etapas.
     public static String formatRegis(String reg) {
         String aux = reg.replace("$","");
         aux = aux.replace("(",",").replace(")", "");
         return aux;
     }
 
     //Esta função retorna apenas a parte da instrução na linha informada.
     static String getInstruction (String inst){
         String instruction = inst.split(" ")[0];
         return instruction;
     }
 
 
     /*  
     * Este método faz a identificação do tipo da instrução que esta sendo lida
     * ele usa condicionais simples para verificar se a substring "instructions" 
     * está contida em uma das 3 Hashs criadas na tabela.
     *  Quando ele encontra um valor correspondente a key instruída, ela aciona o
     * método de tradução da classe condizente com o tipo da instrução.
     * 
     * @param linha // recebe uma linha contendo uma instrução.
     */
     public static String identify(String linha) {
         Tabela tabel = new Tabela();
         String instruction = Reader.getInstruction(linha);
         String[] registradores = Reader.formatRegis(Reader.getRegis(linha)).split(",");
         String binary;
 
         if(tabel.geti(instruction) != null){
             if (linha.contains("(")) 
                 binary = TipoI.binaryString(instruction, registradores, true);
             else 
                 binary = TipoI.binaryString(instruction, registradores,false);
 
         } 
         else if (tabel.getr(instruction) != null) {
             binary = TipoR.binaryString(instruction,registradores);
 
         } 
         else if (tabel.getj(instruction)!= null){
             registradores = Tradutor.Traduzir(registradores);
             int dif = 26 - registradores[0].length();
             for (int i = 0; i < dif; i++) 
                 registradores[0] = "0" + registradores[0];
             
             binary = Tabela.getj(instruction) + registradores[0];
             return binary;
         }
         else {
             binary = "instrução não identificada";
             return binary;
         }
         return binary;
     }
 }