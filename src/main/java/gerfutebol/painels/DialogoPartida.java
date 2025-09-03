package src.main.java.gerfutebol.painels;
// Crie este novo arquivo: DialogoPartida.java
import javax.swing.*;

import src.main.java.gerfutebol.model.Partida;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DialogoPartida extends JDialog {
    private Partida partida;
    private boolean salvo = false;

    private JTextField txtAdversario, txtLocal, txtData, txtPlacarNosso, txtPlacarAdversario;

    public DialogoPartida(Frame owner) {
        super(owner, "Adicionar Nova Partida", true);
        setLayout(new GridLayout(6, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(owner);

        add(new JLabel("Adversário:"));
        txtAdversario = new JTextField();
        add(txtAdversario);

        add(new JLabel("Local:"));
        txtLocal = new JTextField();
        add(txtLocal);

        add(new JLabel("Data (dd/MM/yyyy):"));
        txtData = new JTextField(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        add(txtData);

        add(new JLabel("Placar Nosso (Opcional):"));
        txtPlacarNosso = new JTextField();
        add(txtPlacarNosso);
        
        add(new JLabel("Placar Adversário (Opcional):"));
        txtPlacarAdversario = new JTextField();
        add(txtPlacarAdversario);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        add(btnSalvar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void salvar() {
        try {
            LocalDate data = LocalDate.parse(txtData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Integer placarNosso = txtPlacarNosso.getText().isEmpty() ? null : Integer.parseInt(txtPlacarNosso.getText());
            Integer placarAdv = txtPlacarAdversario.getText().isEmpty() ? null : Integer.parseInt(txtPlacarAdversario.getText());

            this.partida = new Partida(txtAdversario.getText(), txtLocal.getText(), data, placarNosso, placarAdv);
            salvo = true;
            dispose();
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Dados inválidos. Verifique a data (dd/MM/yyyy) e o placar.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Partida getPartida() { return partida; }
    public boolean foiSalvo() { return salvo; }
}