package src.main.java.gerfutebol.painels;
// Salve como: PainelJogadores.java (Substitua o conteúdo)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.main.java.gerfutebol.model.Jogador;
import src.main.java.gerfutebol.model.Time;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelJogadores extends JPanel {
    private Time time;
    private JFrame owner;
    private DefaultTableModel modeloTabela;
    private JTable tabelaJogadores;

    public PainelJogadores(JFrame owner, Time time) {
        this.owner = owner;
        this.time = time;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tabela de Jogadores
        modeloTabela = new DefaultTableModel(new String[]{"Nome", "Idade", "Salário", "Posição", "Lesionado"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaJogadores = new JTable(modeloTabela);
        add(new JScrollPane(tabelaJogadores), BorderLayout.CENTER);

        // Menu de contexto (botão direito)
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem itemEditar = new JMenuItem("Editar Jogador");
        JMenuItem itemRemover = new JMenuItem("Remover Jogador");
        popupMenu.add(itemEditar);
        popupMenu.add(itemRemover);
        tabelaJogadores.setComponentPopupMenu(popupMenu);
        
        itemEditar.addActionListener(e -> editarJogador());
        itemRemover.addActionListener(e -> removerJogador());

        // Painel de Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdicionar = new JButton("Adicionar Jogador");
        btnAdicionar.addActionListener(e -> adicionarJogador());
        painelBotoes.add(btnAdicionar);
        add(painelBotoes, BorderLayout.SOUTH);
        
        atualizarTabela();
    }

    private void adicionarJogador() {
        DialogoJogador dialogo = new DialogoJogador(owner, null);
        dialogo.setVisible(true);

        if (dialogo.foiSalvo()) {
            time.adicionarJogador(dialogo.getJogador());
            atualizarTabela();
        }
    }

    private void editarJogador() {
        int linhaSelecionada = tabelaJogadores.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(owner, "Selecione um jogador para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Jogador jogadorParaEditar = time.getJogadores().get(linhaSelecionada);
        DialogoJogador dialogo = new DialogoJogador(owner, jogadorParaEditar);
        dialogo.setVisible(true);
        
        if (dialogo.foiSalvo()) {
            atualizarTabela();
        }
    }

    private void removerJogador() {
        int linhaSelecionada = tabelaJogadores.getSelectedRow();
         if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(owner, "Selecione um jogador para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirmacao = JOptionPane.showConfirmDialog(owner, "Tem certeza que deseja remover este jogador?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            Jogador jogadorParaRemover = time.getJogadores().get(linhaSelecionada);
            time.removerJogador(jogadorParaRemover);
            atualizarTabela();
        }
    }

    public void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Jogador j : time.getJogadores()) {
            modeloTabela.addRow(new Object[]{
                j.getNome(), j.getIdade(), String.format("R$ %.2f", j.getSalario()),
                j.getPosicao(), j.isLesionado() ? "Sim" : "Não"
            });
        }
    }
    
    public void setTime(Time novoTime) {
        this.time = novoTime;
        atualizarTabela();
    }
}