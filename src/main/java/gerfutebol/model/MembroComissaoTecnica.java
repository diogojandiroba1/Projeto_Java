package src.main.java.gerfutebol.model;
public class MembroComissaoTecnica extends Funcionario {
    private String cargo;

    public MembroComissaoTecnica(String nome, int idade, float salario, String cargo) {
        super(nome, idade, salario);
        this.cargo = cargo;
    }

    @Override
    public String getFuncao() { return getCargo(); }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
}