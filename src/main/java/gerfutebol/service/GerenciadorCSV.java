package src.main.java.gerfutebol.service;
import javax.swing.*;

import src.main.java.gerfutebol.model.Jogador;
import src.main.java.gerfutebol.model.MembroComissaoTecnica;
import src.main.java.gerfutebol.model.Partida;
import src.main.java.gerfutebol.model.Time;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCSV {
    
    public static Time carregarTime(File arquivo) throws IOException {
    Time time = new Time(arquivo.getName().replace(".csv", ""));
    
    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
        String linha;
        boolean primeiraLinha = true;
        
        while ((linha = reader.readLine()) != null) {
            if (primeiraLinha) {
                primeiraLinha = false;
                continue; // Pular cabeçalho
            }
            
            // Usar -1 no split para garantir que campos vazios no final sejam contados
            String[] dados = linha.split(";", -1);
            
            if (dados.length > 0 && !dados[0].isEmpty()) {
                String tipo = dados[0];
                
                if (tipo.equals("JOGADOR") && dados.length >= 8) {
                    Jogador jogador = new Jogador(
                        dados[1], 
                        Integer.parseInt(dados[2]),
                        Float.parseFloat(dados[3].replace(',', '.')), // Salário
                        dados[4],
                        Float.parseFloat(dados[5].replace(',', '.')), // Altura
                        Float.parseFloat(dados[6].replace(',', '.')), // Peso
                        Boolean.parseBoolean(dados[7])
                    );
                    time.adicionarJogador(jogador);
                } 
                else if (tipo.equals("COMISSAO") && dados.length >= 5) {
                    MembroComissaoTecnica membro = new MembroComissaoTecnica(
                        dados[1],
                        Integer.parseInt(dados[2]),
                        Float.parseFloat(dados[3].replace(',', '.')), // Salário
                        dados[4]
                    );
                    time.adicionarMembroComissao(membro);
                }
                else if (tipo.equals("PARTIDA") && dados.length >= 13) { // <-- CORREÇÃO AQUI
                    // Usar os índices corretos para os dados da partida
                    String adversario = dados[8];
                    String local = dados[9];
                    String dataString = dados[10];
                    Integer placarNosso = dados[11].isEmpty() ? null : Integer.parseInt(dados[11]);
                    Integer placarAdv = dados[12].isEmpty() ? null : Integer.parseInt(dados[12]);
                    
                    Partida partida = new Partida(
                        adversario,
                        local,
                        LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        placarNosso,
                        placarAdv
                    );
                    time.adicionarPartida(partida);
                }
            }
        }
    }
    
    return time;
}
    
    // O método salvarTime já está correto, pois o String.format usa o ponto por padrão.
    // No entanto, é bom garantir que o Locale do sistema não interfira.
    // Para forçar o uso do ponto, podemos especificar o Locale.US.
    public static void salvarTime(Time time, File arquivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            // Escrever cabeçalho
            writer.println("TIPO;NOME;IDADE;SALARIO;POSICAO/CARGO;ALTURA;PESO;LESIONADO;ADVERSARIO;LOCAL;DATA;PLACAR_NOSSO;PLACAR_ADV");
            
            // Salvar jogadores
            for (Jogador jogador : time.getJogadores()) {
                // Usando java.util.Locale.US para garantir o ponto como separador decimal
                writer.println(String.format(java.util.Locale.US, "JOGADOR;%s;%d;%.2f;%s;%.2f;%.2f;%b;;;;;",
                    jogador.getNome(), jogador.getIdade(), jogador.getSalario(),
                    jogador.getPosicao(), jogador.getAltura(), jogador.getPeso(),
                    jogador.isLesionado()));
            }
            
            // Salvar comissão técnica
            for (MembroComissaoTecnica membro : time.getComissaoTecnica()) {
                 // Usando java.util.Locale.US para garantir o ponto como separador decimal
                writer.println(String.format(java.util.Locale.US, "COMISSAO;%s;%d;%.2f;%s;;;;;;;",
                    membro.getNome(), membro.getIdade(), membro.getSalario(),
                    membro.getCargo()));
            }
            
            // Salvar partidas
            for (Partida partida : time.getPartidas()) {
                writer.println(String.format("PARTIDA;;;;;;;%s;%s;%s;%s;%s",
                    partida.getAdversario(), partida.getLocal(),
                    partida.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    partida.getPlacarNosso() != null ? partida.getPlacarNosso() : "",
                    partida.getPlacarAdversario() != null ? partida.getPlacarAdversario() : ""));
            }
        }
    }
}