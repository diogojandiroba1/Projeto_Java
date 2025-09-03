package src.main.java.gerfutebol.painels;
// Substitua o conteúdo de DialogoComissao.java
import javax.swing.*;

import src.main.java.gerfutebol.model.MembroComissaoTecnica;

import java.awt.*;

public class DialogoComissao extends JDialog {
    private MembroComissaoTecnica membro;
    private boolean salvo = false;
    private JTextField txtNome, txtIdade, txtSalario, txtCargo;

    public DialogoComissao(Frame owner, MembroComissaoTecnica membro) {
        super(owner, true);
        this.membro = (membro == null) ? new MembroComissaoTecnica("", 0, 0, "") : membro;

        setTitle(membro == null ? "Adicionar Membro" : "Editar Membro");
        setLayout(new GridLayout(5, 2, 10, 10));
        setSize(400, 250);
        setLocationRelativeTo(owner);
        
        add(new JLabel("Nome:"));
        txtNome = new JTextField(this.membro.getNome());
        add(txtNome);
        add(new JLabel("Idade:"));
        txtIdade = new JTextField(String.valueOf(this.membro.getIdade()));
        add(txtIdade);
        add(new JLabel("Salário:"));
        txtSalario = new JTextField(String.valueOf(this.membro.getSalario()));
        add(txtSalario);
        add(new JLabel("Cargo:"));
        txtCargo = new JTextField(this.membro.getCargo());
        add(txtCargo);
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        add(btnSalvar);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }
    
    private void salvar() {
        try {
            membro.setNome(txtNome.getText());
            membro.setIdade(Integer.parseInt(txtIdade.getText()));
            membro.setSalario(Float.parseFloat(txtSalario.getText().replace(",", ".")));
            membro.setCargo(txtCargo.getText());
            salvo = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha os campos numéricos corretamente.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
    public MembroComissaoTecnica getMembro() { return membro; }
    public boolean foiSalvo() { return salvo; }
}