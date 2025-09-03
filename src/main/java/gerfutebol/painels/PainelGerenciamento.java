package src.main.java.gerfutebol.painels;
// Substitua o conteúdo de PainelGerenciamento.java
import javax.swing.*;

import src.main.java.gerfutebol.model.Time;

import java.awt.*;

public class PainelGerenciamento extends JPanel {
    private Time time;
    private JLabel lblFolhaSalarial;
    private JTextField txtCustoManutencao, txtLucroBilheteria, txtLucroPatrocinio;

    public PainelGerenciamento(Time time) {
        this.time = time;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        add(ComponentFactory.criarLabel("Folha Salarial Mensal (Calculado):"), gbc);
        gbc.gridx = 1;
        lblFolhaSalarial = ComponentFactory.criarLabel("");
        add(lblFolhaSalarial, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        add(ComponentFactory.criarLabel("Custo de Manutenção Mensal:"), gbc);
        gbc.gridx = 1;
        txtCustoManutencao = ComponentFactory.criarTextField();
        add(txtCustoManutencao, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        add(ComponentFactory.criarLabel("Lucro com Bilheteria (Mês):"), gbc);
        gbc.gridx = 1;
        txtLucroBilheteria = ComponentFactory.criarTextField();
        add(txtLucroBilheteria, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        add(ComponentFactory.criarLabel("Lucro com Patrocínio (Mês):"), gbc);
        gbc.gridx = 1;
        txtLucroPatrocinio = ComponentFactory.criarTextField();
        add(txtLucroPatrocinio, gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JButton btnSalvar = ComponentFactory.criarBotao("Salvar Dados Financeiros");
        btnSalvar.addActionListener(e -> salvar());
        add(btnSalvar, gbc);

        atualizarDados();
    }

    private void salvar() {
        try {
            time.setCustoManutencao(Float.parseFloat(txtCustoManutencao.getText().replace(",", ".")));
            time.setLucroBilheteria(Float.parseFloat(txtLucroBilheteria.getText().replace(",", ".")));
            time.setLucroPatrocinio(Float.parseFloat(txtLucroPatrocinio.getText().replace(",", ".")));
            JOptionPane.showMessageDialog(this, "Dados financeiros salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos com valores numéricos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarDados() {
        lblFolhaSalarial.setText(String.format("R$ %.2f", time.getFolhaSalarialMensal()));
        txtCustoManutencao.setText(String.valueOf(time.getCustoManutencao()));
        txtLucroBilheteria.setText(String.valueOf(time.getLucroBilheteria()));
        txtLucroPatrocinio.setText(String.valueOf(time.getLucroPatrocinio()));
    }
    
    public void setTime(Time novoTime) {
        this.time = novoTime;
        atualizarDados();
    }
}