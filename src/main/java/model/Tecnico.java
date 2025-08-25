class Tecnico extends Pessoa{

private List<String> taticas;


public Jogador(String Nome, String Idade, float Salario, List taticas){
super(Nome, Idade, Salario);
this.taticas = new ArrayList<>();
}


}