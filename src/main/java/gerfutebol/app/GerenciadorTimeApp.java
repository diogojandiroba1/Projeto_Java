package src.main.java.gerfutebol.app;
// Substitua o conteúdo de GerenciadorTimeApp.java
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.main.java.gerfutebol.model.MembroComissaoTecnica;
import src.main.java.gerfutebol.model.Time;
import src.main.java.gerfutebol.painels.PainelBoasVindas;
import src.main.java.gerfutebol.painels.PainelComissaoTecnica;
import src.main.java.gerfutebol.painels.PainelGerenciamento;
import src.main.java.gerfutebol.painels.PainelJogadores;
import src.main.java.gerfutebol.painels.PainelPartidas;
import src.main.java.gerfutebol.service.GerenciadorCSV;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GerenciadorTimeApp {
    private Time time;
    private JFrame frame;
    private PainelJogadores painelJogadores;
    private PainelComissaoTecnica painelComissao;
    private PainelPartidas painelPartidas;
    private PainelGerenciamento painelGerenciamento;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GerenciadorTimeApp::new);
    }

    public GerenciadorTimeApp() {
        frame = new JFrame("Gerenciador de Time de Futebol ⚽");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        mostrarTelaBoasVindas();
        frame.setVisible(true);
    }

    private void mostrarTelaBoasVindas() {
        frame.setTitle("Bem-vindo!");
        frame.setJMenuBar(null);
        PainelBoasVindas painel = new PainelBoasVindas(e -> novoTime(), e -> carregarTime());
        frame.setContentPane(painel);
        frame.revalidate();
        frame.repaint();
    }

    private void mostrarInterfacePrincipal() {
        frame.setJMenuBar(criarBarraMenu());
        JTabbedPane abas = new JTabbedPane();
        
        painelJogadores = new PainelJogadores(frame, time);
        painelComissao = new PainelComissaoTecnica(frame, time);
        painelPartidas = new PainelPartidas(frame, time);
        painelGerenciamento = new PainelGerenciamento(time);
        
        abas.addTab("Elenco", painelJogadores);
        abas.addTab("Comissão Técnica", painelComissao);
        abas.addTab("Partidas", painelPartidas);
        abas.addTab("Financeiro", painelGerenciamento);
        
        frame.setContentPane(abas);
        frame.revalidate();
        frame.repaint();
        atualizarPaineis();
    }

    private JMenuBar criarBarraMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");

        JMenuItem itemNovo = new JMenuItem("Novo Time");
        itemNovo.addActionListener(e -> novoTime());

        JMenuItem itemCarregar = new JMenuItem("Carregar Time (.json)");
        itemCarregar.addActionListener(e -> carregarTime());

        JMenuItem itemSalvar = new JMenuItem("Salvar Time (.json)");
        itemSalvar.addActionListener(e -> salvarTime());

        menuArquivo.add(itemNovo);
        menuArquivo.add(itemCarregar);
        menuArquivo.add(itemSalvar);
        menuBar.add(menuArquivo);

        return menuBar;
    }
    
    private void novoTime() {
        String nomeTime = JOptionPane.showInputDialog(frame, "Qual o nome do novo time?", "Novo Time", JOptionPane.QUESTION_MESSAGE);
        if (nomeTime != null && !nomeTime.trim().isEmpty()) {
            this.time = new Time(nomeTime);
            this.time.adicionarMembroComissao(new MembroComissaoTecnica("A Definir", 40, 50000, "Técnico"));
            mostrarInterfacePrincipal();
        }
    }
    
    private void carregarTime() {
        JFileChooser chooser = new JFileChooser();
        // MUDANÇA AQUI
            chooser.setFileFilter(new FileNameExtensionFilter("Arquivos CSV", "csv"));        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                // MUDANÇA AQUI
                this.time = GerenciadorCSV.carregarTime(chooser.getSelectedFile());
                mostrarInterfacePrincipal();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao carregar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void salvarTime() {
        JFileChooser chooser = new JFileChooser();
        // MUDANÇA AQUI
        chooser.setFileFilter(new FileNameExtensionFilter("Arquivos JSON", "json"));
        chooser.setSelectedFile(new File(time.getNome() + ".csv"));        if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            try {
                // MUDANÇA AQUI
                GerenciadorCSV.salvarTime(time, chooser.getSelectedFile());
                JOptionPane.showMessageDialog(frame, "Time salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarPaineis() {
        frame.setTitle("Gerenciador de Time: " + time.getNome());
        if (painelJogadores != null) painelJogadores.setTime(time);
        if (painelComissao != null) painelComissao.setTime(time);
        if (painelPartidas != null) painelPartidas.setTime(time);
        if (painelGerenciamento != null) painelGerenciamento.setTime(time);
    }
}