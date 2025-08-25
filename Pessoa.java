class Pessoa{

private String nome_completo;
private int idade;
private float salario;

public Pessoa(String Nome, String Idade, float Salario){
nome_completo = Nome;
idade = Idade;
salario = Salario;
}


public String get_name(){

return nome_completo;


}

public void set_name(String Nome){

nome_completo = Nome;

}


public int get_idade(){

return idade;


}

public void set_idade(int Idade){

idade = Idade;

}


public float get_salario(){

return salario;


}

public void set_salario(float Salario){ 

salario = Salario;

}



}