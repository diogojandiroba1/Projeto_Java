package src.main.java.gerfutebol.model;
// Jogador.java
public class Jogador extends Funcionario {
    private String posicao;
    private float altura;
    private float peso;
    private boolean lesionado;

    public Jogador(String nome, int idade, float salario, String posicao, float altura, float peso, boolean lesionado) {
        super(nome, idade, salario);
        this.posicao = posicao;
        this.altura = altura;
        this.peso = peso;
        this.lesionado = lesionado;
    }

    public float getImc() {
        if (altura > 0) {
            return peso / (altura * altura);
        }
        return 0;
    }

    @Override
    public String getFuncao() {
        return "Jogador";
    }

    // Getters e Setters
    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isLesionado() {
        return lesionado;
    }

    public void setLesionado(boolean lesionado) {
        this.lesionado = lesionado;
    }
}