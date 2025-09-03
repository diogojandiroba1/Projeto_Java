package src.main.java.gerfutebol.model;
// Salve como: Partida.java (Substitua o conteúdo)
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Partida {
    private String adversario;
    private LocalDate data;
    private Integer placarNosso;
    private Integer placarAdversario;
    private String local;

    public Partida(String adversario, String local, LocalDate data, Integer placarNosso, Integer placarAdversario) {
        this.adversario = adversario;
        this.local = local;
        this.data = data;
        this.placarNosso = placarNosso;
        this.placarAdversario = placarAdversario;
    }

    public String getResultado() {
        if (placarNosso == null || placarAdversario == null) { return "A Jogar"; }
        return placarNosso + " x " + placarAdversario;
    }

    public String getDataFormatada() { return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); }
    public String getAdversario() { return adversario; }
    public String getLocal() { return local; }
    public LocalDate getData() { return data; }
    
    // MÉTODOS QUE FALTAVAM
    public Integer getPlacarNosso() { return placarNosso; }
    public Integer getPlacarAdversario() { return placarAdversario; }

    public void setPlacar(Integer nosso, Integer adversario) {
        this.placarNosso = nosso;
        this.placarAdversario = adversario;
    }
}