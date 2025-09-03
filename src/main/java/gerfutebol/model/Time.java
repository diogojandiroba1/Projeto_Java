package src.main.java.gerfutebol.model;
import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private List<MembroComissaoTecnica> comissaoTecnica;
    private List<Jogador> jogadores;
    private List<Partida> partidas;
    private float custoManutencao;
    private float lucroBilheteria;
    private float lucroPatrocinio;

    public Time(String nome) {
        this.nome = nome;
        this.comissaoTecnica = new ArrayList<>();
        this.jogadores = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }
    
    public float getFolhaSalarialMensal() {
        float total = 0;
        for (Jogador j : jogadores) {
            total += j.getSalario();
        }
        for (MembroComissaoTecnica m : comissaoTecnica) {
            total += m.getSalario();
        }
        return total;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; } // <-- MÉTODO ADICIONADO AQUI

    public float getCustoManutencao() { return custoManutencao; }
    public void setCustoManutencao(float custoManutencao) { this.custoManutencao = custoManutencao; }
    public float getLucroBilheteria() { return lucroBilheteria; }
    public void setLucroBilheteria(float lucroBilheteria) { this.lucroBilheteria = lucroBilheteria; }
    public float getLucroPatrocinio() { return lucroPatrocinio; }
    public void setLucroPatrocinio(float lucroPatrocinio) { this.lucroPatrocinio = lucroPatrocinio; }
    
    public void adicionarJogador(Jogador jogador) { jogadores.add(jogador); }
    public void removerJogador(Jogador jogador) { jogadores.remove(jogador); }
    public List<Jogador> getJogadores() { return new ArrayList<>(jogadores); }
    public void adicionarMembroComissao(MembroComissaoTecnica membro) { comissaoTecnica.add(membro); }
    public void removerMembroComissao(MembroComissaoTecnica membro) { comissaoTecnica.remove(membro); }
    public List<MembroComissaoTecnica> getComissaoTecnica() { return new ArrayList<>(comissaoTecnica); }
    public void adicionarPartida(Partida partida) { partidas.add(partida); }
    public List<Partida> getPartidas() { return new ArrayList<>(partidas); }
}