package br.com.aeviles.calc.view.model;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

    private enum TipoComando{
            ZERAR,NUMERO,DIV,MULT,SUB,SOMA,IGUAL, VIRGULA;
    };

//singleton é um padrão de projeto que vai ter uma única instancia de uma determinada classe
    //para você ter uma unica instancia usando o construtor da classe como privado
    //eu contolo a criação dentro da própria classe
    private  static  Memoria instancia = new Memoria();

    //esse arrayList vai armazenar todos os observadores que foram cadastrados aqui
    private final List<MemoryObservador> observadores = new ArrayList<>();

    private TipoComando ultimaOperacao=null;
    private boolean substituir = false;
    private String textoAtual = "";
    private String textoBuffer = "";

    private Memoria(){}



    public static Memoria getInstancia() {
        return instancia;
    }

    //A mémoria precisa ter um método que registra todos os observadores


    public void adicionarObservador(MemoryObservador o) {
        observadores.add(o);

    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    //método que processo novos carateres

    public void processarComando(String text) {
        TipoComando tipoComando=detectarTipoComando(text);
        if(tipoComando == null){
            return;
        } else if (tipoComando==TipoComando.ZERAR) {
            textoAtual="";
            textoBuffer="";
            substituir=false;
            ultimaOperacao=null;
            
        } else if (tipoComando ==TipoComando.NUMERO || tipoComando==TipoComando.VIRGULA) {
                                //substituindo   acrescentando
            textoAtual=substituir ? text: textoAtual+text;
            substituir=false;
        }else {
            substituir = true;
            textoAtual = obterResultadoOperacao();

            textoBuffer=textoAtual;
            ultimaOperacao=tipoComando;
        }

        observadores.forEach(o->o.valorAlterado(getTextoAtual()));



    }

    private String obterResultadoOperacao() {

        if(ultimaOperacao==null || ultimaOperacao ==TipoComando.IGUAL ){
            return textoAtual;
        }

        double numeroBuffer = Double.parseDouble((textoBuffer.replace(",",".")));
        double numeroAtual = Double.parseDouble((textoAtual.replace(",",".")));

        double resultado = 0;

        if(ultimaOperacao==TipoComando.SOMA){
            resultado = numeroBuffer + numeroAtual;
        }else if(ultimaOperacao==TipoComando.SUB){
            resultado = numeroBuffer - numeroAtual;
        }else if(ultimaOperacao==TipoComando.MULT){
            resultado = numeroBuffer * numeroAtual;
        }else if(ultimaOperacao==TipoComando.DIV){
            resultado = numeroBuffer / numeroAtual;
        }

        String resultadoString = Double.toString(resultado).replace(".",",");
        boolean inteiro = resultadoString.endsWith(",0");
        return  inteiro ? resultadoString.replace(",0",""): resultadoString;
    }

    private TipoComando detectarTipoComando(String text) {
        if(textoAtual.isEmpty() && text=="0"){
            return null;
        }
        try {
            Integer.parseInt(text);
            return TipoComando.NUMERO;
        } catch (NumberFormatException e) {
            //Quando não for numero fazer outros testes
            if("AC".equals(text)){
                return  TipoComando.ZERAR;
            }else if ("/".equals(text)){
                return TipoComando.DIV;
            } else if ("X".equals(text)) {
                return TipoComando.MULT;
            } else if ("+".equals(text)) {
                return TipoComando.SOMA;
            } else if ("-".equals(text)) {
                return TipoComando.SUB;
            } else if ("=".equals(text)) {
                return TipoComando.IGUAL;
            } else if (",".equals(text) && !textoAtual.contains(",")) { //se dentro de texto atual tiver uma virgula e não estiver contido uma virgula
                return TipoComando.VIRGULA;
            }

        }
        return null;
    }
}
