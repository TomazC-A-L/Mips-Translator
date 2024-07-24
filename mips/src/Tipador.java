public class Tipador {
    public static void identify(String linha) {
        Tabela tabel = new Tabela();
        String instruction = Reader.getInstruction(linha);
        String regis = Reader.getRegis(linha);
        formatRegis(regis);

        if(tabel.getl(instruction) != null){
            String[] registradores = regis.split(",");

            //trocando o segundo registrador fonte com o registrador de destino
            String aux = registradores[2];
            registradores[2] = registradores[3];
            registradores[3] = aux;

            String resposta = "";
            for (int i = 0; i < registradores.length; i++) {
                resposta = instruction + registradores[i];
            }
            System.out.println(resposta + "i");
            
        } 
        else if (tabel.getr(instruction) != null) {
            String[] registradores = regis.split(",");
            
            String resposta = "";
            for (int i = 0; i < registradores.length; i++) {
                resposta = instruction + registradores[i];
            }
            System.out.println(resposta + "r");
            //metodo de binario
        }
        else {
            String[] registradores = linha.split(" ");
            String resposta = "";
            for (int i = 0; i < registradores.length; i++) {
                resposta = instruction + registradores[i];
            }
            System.out.println(resposta + "j");
        }
    }

    private static String formatRegis(String reg) {
        String aux = reg.replace("$","");
        if(reg.contains("(")) {
            aux.replace("(", ",").replace(")","");
        }
        return aux; //retorna: t0,1200,t1
    }
}
