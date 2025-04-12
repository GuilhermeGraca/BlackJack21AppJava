package tp4;

import java.io.Serial;

public class Carta {
    private String naipe;
    private int valor;


    public Carta(int valor, String naipe) {
        this.valor = valor;
        this.naipe = naipe;
    }

//métodos acessores:

    public int getValor() {
        return valor;
    }

    public String getNaipe() {
        return naipe;
    }

    @Override
    public String toString() {
        String valorString;
        switch (valor) {
            case 11:
                valorString = "Valete";
                break;
            case 12:
                valorString = "Dama";
                break;
            case 13:
                valorString = "Rei";
                break;
            case 14:
                valorString = "Ás";
                break;
            default:
                valorString = String.valueOf(valor);
        }
        return valorString + " de " + naipe;
    }

    public int getValorEmJogo() {
        if(valor == 14) {
            return 11;
            //O Ás tambem pode ser 1 dependendo da ocasião mas essa logica é defenida na Classe Mao
        }else if(valor >= 11 && valor <= 13){
            return 10;
            //Se for alguma da figuras o seu valor é 10
        }else{
            return valor;
        }

    }


}