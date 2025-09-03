package src.main.java.gerfutebol.painels;
// ComponentFactory.java
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class ComponentFactory {

    public static JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(Estilos.FONTE_LABEL);
        label.setForeground(Estilos.COR_TEXTO);
        return label;
    }

    public static JTextField criarTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(Estilos.FONTE_INPUT);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    public static JButton criarBotao(String texto) {
        JButton button = new JButton(texto);
        button.setFont(Estilos.FONTE_BOTAO);
        button.setBackground(Estilos.COR_BOTAO);
        button.setForeground(Estilos.COR_TEXTO_BOTAO);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        return button;
    }
}