package src.main.java.gerfutebol.model;
// Salve como: Funcionario.java (Substitua o conteúdo)
public abstract class Funcionario extends Pessoa {
    private float salario;

    public Funcionario(String nome, int idade, float salario) {
        super(nome, idade);
        this.salario = salario;
    }

    // MÉTODO RENOMEADO (sem acento)
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public abstract String getFuncao();
}