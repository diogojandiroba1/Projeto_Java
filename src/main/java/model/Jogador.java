public class Jogador extends Pessoa{

private String posicao;
private float altura;
private float peso;
private boolean lesionado;

public Jogador(String Nome, int Idade, float Salario, String Posicao, float Altura, float Peso, boolean Lesionado){
super(Nome, Idade, Salario);
posicao = Posicao;
altura = Altura;
peso = Peso;
lesionado = Lesionado;
}


public String get_posicao(){

return posicao;

}

public void set_posicao(String Posicao){ 

posicao = Posicao;

}


public float get_altura(){

return altura;


}

public void set_altura(float Altura){ 

altura = Altura;

}

public float get_peso(){

return peso;


}

public void set_peso(float Peso){ 

peso = Peso;

}

public boolean get_lesionado(){

return lesionado;


}

public void set_lesionado(boolean Lesionado){ 

lesionado = Lesionado;

}

public float get_imc(){

return peso / (altura * altura);

}



}