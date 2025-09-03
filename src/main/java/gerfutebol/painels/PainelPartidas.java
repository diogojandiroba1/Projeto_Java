package src.main.java.gerfutebol.painels;
// Substitua o conteúdo de PainelPartidas.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.main.java.gerfutebol.model.Partida;
import src.main.java.gerfutebol.model.Time;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelPartidas extends JPanel {
    private Time time;
    private JFrame owner;
    private DefaultTableModel modeloTabela;
    private JTable tabelaPartidas;

    public PainelPartidas(JFrame owner, Time time) {
        this.owner = owner;
        this.time = time;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modeloTabela = new DefaultTableModel(new String[]{"Data", "Adversário", "Local", "Resultado"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaPartidas = new JTable(modeloTabela);
        add(new JScrollPane(tabelaPartidas), BorderLayout.CENTER);

        tabelaPartidas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabelaPartidas.getSelectedRow() != -1) {
                    editarPartida();
                }
            }
        });
        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdicionar = new JButton("Adicionar Partida");
        btnAdicionar.addActionListener(e -> adicionarPartida());
        painelBotoes.add(btnAdicionar);
        add(painelBotoes, BorderLayout.SOUTH);
        
        atualizarTabela();
    }
    
    private void editarPartida() {
        // (lógica de editar continua a mesma)
    }

    // MÉTODO ATUALIZADO
    private void adicionarPartida() {
        DialogoPartida dialogo = new DialogoPartida(owner);
        dialogo.setVisible(true);

        if (dialogo.foiSalvo()) {
            time.adicionarPartida(dialogo.getPartida());
            atualizarTabela();
        }
    }
    
    public void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Partida p : time.getPartidas()) {
            modeloTabela.addRow(new Object[]{p.getDataFormatada(), p.getAdversario(), p.getLocal(), p.getResultado()});
        }
    }

    public void setTime(Time novoTime) {
        this.time = novoTime;
        atualizarTabela();
    }
}