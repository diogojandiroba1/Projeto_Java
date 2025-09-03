package src.main.java.gerfutebol.painels;
// Substitua o conteúdo de DialogoJogador.java
import javax.swing.*;

import src.main.java.gerfutebol.model.Jogador;

import java.awt.*;

public class DialogoJogador extends JDialog {
    private Jogador jogador;
    private boolean salvo = false;
    private JTextField txtNome, txtIdade, txtSalario, txtAltura, txtPeso;
    private JComboBox<String> comboPosicao;
    private JCheckBox chkLesionado;

    public DialogoJogador(Frame owner, Jogador jogador) {
        super(owner, true);
        this.jogador = (jogador == null) ? new Jogador("", 0, 0, "", 0, 0, false) : jogador;
        
        setTitle(jogador == null ? "Adicionar Jogador" : "Editar Jogador");
        setLayout(new GridLayout(8, 2, 10, 10));
        setSize(400, 400);
        setLocationRelativeTo(owner);

        add(new JLabel("Nome:"));
        txtNome = new JTextField(this.jogador.getNome());
        add(txtNome);
        add(new JLabel("Idade:"));
        txtIdade = new JTextField(String.valueOf(this.jogador.getIdade()));
        add(txtIdade);
        add(new JLabel("Salário:"));
        txtSalario = new JTextField(String.valueOf(this.jogador.getSalario()));
        add(txtSalario);
        add(new JLabel("Posição:"));
        String[] posicoes = {"Goleiro", "Zagueiro", "Lateral Direito", "Lateral Esquerdo", "Volante", "Meio-Campo", "Ponta Direita", "Ponta Esquerda", "Atacante"};
        comboPosicao = new JComboBox<>(posicoes);
        comboPosicao.setSelectedItem(this.jogador.getPosicao());
        add(comboPosicao);
        add(new JLabel("Altura (m):"));
        txtAltura = new JTextField(String.valueOf(this.jogador.getAltura()));
        add(txtAltura);
        add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField(String.valueOf(this.jogador.getPeso()));
        add(txtPeso);
        add(new JLabel("Status:"));
        chkLesionado = new JCheckBox("Lesionado", this.jogador.isLesionado());
        add(chkLesionado);
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        add(btnSalvar);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void salvar() {
        try {
            jogador.setNome(txtNome.getText());
            jogador.setIdade(Integer.parseInt(txtIdade.getText()));
            jogador.setSalario(Float.parseFloat(txtSalario.getText().replace(",", ".")));
            jogador.setPosicao((String) comboPosicao.getSelectedItem());
            jogador.setAltura(Float.parseFloat(txtAltura.getText().replace(",", ".")));
            jogador.setPeso(Float.parseFloat(txtPeso.getText().replace(",", ".")));
            jogador.setLesionado(chkLesionado.isSelected());
            salvo = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos numéricos corretamente.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
    public Jogador getJogador() { return jogador; }
    public boolean foiSalvo() { return salvo; }
}