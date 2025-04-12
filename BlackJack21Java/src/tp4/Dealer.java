package tp4;

public class Dealer extends Pessoa {

    //Dealer pede sempre até ter 17 ccomo valor de mão
public boolean pedeCarta() {
    if (mao.getValorMao() < 17){
        return true;
    } else return false;
}

}
