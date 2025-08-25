Class Jogador extends Pessoa{

private String posicao;
private float altura;
private float peso;
private boolean lesionado;
private float imc;

public Jogador(String Nome, String Idade, float Salario, String Posicao, float Altura, float Peso, boolean Lesionado){
super(Nome, Idade, Salario);
posicao = Posicao;
altura = Altura;
peso = Peso;
lesionado = Lesionado;
imc =  peso / (altura * altura) 
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

return imc;

}



}