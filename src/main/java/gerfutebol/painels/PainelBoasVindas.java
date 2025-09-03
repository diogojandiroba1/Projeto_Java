package src.main.java.gerfutebol.painels;
// Salve como: PainelBoasVindas.java (Arquivo novo)
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PainelBoasVindas extends JPanel {
    public PainelBoasVindas(ActionListener listenerNovo, ActionListener listenerCarregar) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel titulo = new JLabel("Gerenciador de Time de Futebol");
        titulo.setFont(Estilos.FONTE_TITULO.deriveFont(32f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        JButton btnNovo = ComponentFactory.criarBotao("Criar Novo Time");
        btnNovo.addActionListener(listenerNovo);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnNovo, gbc);

        JButton btnCarregar = ComponentFactory.criarBotao("Carregar Time (.csv)");
        btnCarregar.addActionListener(listenerCarregar);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(btnCarregar, gbc);
    }
}