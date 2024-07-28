/* ate agora esta classe traduz os registradores para binario de acordo com seu tipo */
public class Tradutor {
    public static String[] Traduzir(String[] regis){
        Tabela tabel = new Tabela();

        String traduzido[] = new String [regis.length];
        for (int i = 0; i < traduzido.length; i++) {
            if(regis[i] == null) {
                traduzido[i] = "00000";
            } else if (tabel.getRegistrador(regis[i])!=null){
                traduzido[i] = tabel.getRegistrador(regis[i]);
            } else {
                traduzido[i] = imediateBinary(regis[i]);
                break;
            }
            traduzido[i] = arrumarbinario(traduzido[i], 5);
        }
        return traduzido;
    }

    private static String imediateBinary(String imediate){
        if (Integer.parseInt(imediate) >= 0) {
            return arrumarbinario(Integer.toBinaryString(Integer.parseInt(imediate)), 16);
        } else {
            int aux = Math.abs(Integer.parseInt(imediate));
            String binary = arrumarbinario(Integer.toBinaryString(aux), 16);

            String complemento1 = binary.replace('0', 'x').replace('1', '0').replace('x', '1');

            int aux2 = Integer.parseInt(complemento1, 2) + 1;
            String complementoDois = Integer.toBinaryString(aux2);
            return complementoDois;
        }
    }

    public static String arrumarbinario(String binario, int tamanho) {
        while (binario.length() < tamanho){
            binario = "0" + binario;
        }
        return binario;
   }
}
