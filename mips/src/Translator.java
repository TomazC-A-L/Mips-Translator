import java.util.Map;

public class Translator {
    private String element;
    private String result;
    private static final Map<String, String> tabela = null;

    public Translator(String key, String value){

        //instruções Load e Store
        this.tabela.put("lb","00110011 00110010");
        this.tabela.put("lh","00110011 00110011");
        this.tabela.put("lwl","00110011 00110100");
        this.tabela.put("lw","00110011 00110101");
        this.tabela.put("lbu","00110011 00110110");
        this.tabela.put("lhu","00110011 00110111");
        this.tabela.put("lwr","00110011 00111000");
        this.tabela.put("sb", "00110100 00110000");
        this.tabela.put("sh","00110100 00110001");
        this.tabela.put("swl","00110100 00110010");
        this.tabela.put("sw", "00110100 00110011");
        this.tabela.put("swr", "00110100 00110110");

        //instruções Lógicas e aritméticas

        /*
        this.tabela.put("add","");
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        this.tabela.put();
        */
    }
}
