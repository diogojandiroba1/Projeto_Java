package src.main.java.gerfutebol.painels;
// Salve como: PainelComissaoTecnica.java (Substitua o antigo PainelTecnico.java)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.main.java.gerfutebol.model.MembroComissaoTecnica;
import src.main.java.gerfutebol.model.Time;

import java.awt.*;

public class PainelComissaoTecnica extends JPanel {
    private Time time;
    private JFrame owner;
    private DefaultTableModel modeloTabela;
    private JTable tabelaComissao;

    public PainelComissaoTecnica(JFrame owner, Time time) {
        this.owner = owner;
        this.time = time;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        modeloTabela = new DefaultTableModel(new String[]{"Nome", "Idade", "Salário", "Cargo"}, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaComissao = new JTable(modeloTabela);
        add(new JScrollPane(tabelaComissao), BorderLayout.CENTER);
        
        // Menu de contexto
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem itemEditar = new JMenuItem("Editar Membro");
        JMenuItem itemRemover = new JMenuItem("Remover Membro");
        popupMenu.add(itemEditar);
        popupMenu.add(itemRemover);
        tabelaComissao.setComponentPopupMenu(popupMenu);
        
        itemEditar.addActionListener(e -> editarMembro());
        itemRemover.addActionListener(e -> removerMembro());
        
        // Painel de Botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAdicionar = new JButton("Adicionar Membro");
        btnAdicionar.addActionListener(e -> adicionarMembro());
        painelBotoes.add(btnAdicionar);
        add(painelBotoes, BorderLayout.SOUTH);

        atualizarTabela();
    }
    
    private void adicionarMembro() {
        DialogoComissao dialogo = new DialogoComissao(owner, null);
        dialogo.setVisible(true);

        if (dialogo.foiSalvo()) {
            time.adicionarMembroComissao(dialogo.getMembro());
            atualizarTabela();
        }
    }
    
    private void editarMembro() {
        int linha = tabelaComissao.getSelectedRow();
        if (linha != -1) {
            MembroComissaoTecnica membro = time.getComissaoTecnica().get(linha);
            DialogoComissao dialogo = new DialogoComissao(owner, membro);
            dialogo.setVisible(true);
            if (dialogo.foiSalvo()) {
                atualizarTabela();
            }
        }
    }

    private void removerMembro() {
        int linha = tabelaComissao.getSelectedRow();
        if (linha != -1) {
             if (JOptionPane.showConfirmDialog(owner, "Deseja remover este membro?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                MembroComissaoTecnica membro = time.getComissaoTecnica().get(linha);
                time.removerMembroComissao(membro);
                atualizarTabela();
            }
        }
    }

    public void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (MembroComissaoTecnica m : time.getComissaoTecnica()) {
            modeloTabela.addRow(new Object[]{
                m.getNome(), m.getIdade(), String.format("R$ %.2f", m.getSalario()), m.getCargo()
            });
        }
    }

    public void setTime(Time novoTime) {
        this.time = novoTime;
        atualizarTabela();
    }
}