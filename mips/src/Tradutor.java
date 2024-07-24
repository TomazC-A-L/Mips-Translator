public class Tradutor extends Tabela {
    public String[] Traduzir(String[] regis){
        Tabela tabel = new Tabela();
        String traduzido[] = new String [3];

        for (int i = 0; i < traduzido.length; i++) {
            if(regis[i] == null) {
                traduzido[i] = "00000";
            } else if (tabel.getRegistrador(regis[i])!=null){
                traduzido[i] = tabel.getRegistrador(regis[i]);
            } else {
                traduzido[i] = Integer.toBinaryString(Integer.parseInt(regis[i]));
            }
            traduzido[i] = arrumarbinario(traduzido[i], 5);
        }
        return traduzido;
    }

    public static String arrumarbinario(String binario, int tamanho) {
        if (binario.length() < tamanho) {
             int diferenca = tamanho - binario.length();
             for (int i = 0; i < diferenca; i++) {
                  binario = "0" + binario;
             }
        }
        return binario;
   }
}
