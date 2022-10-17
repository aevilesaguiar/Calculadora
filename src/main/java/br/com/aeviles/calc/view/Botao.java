package br.com.aeviles.calc.view;

import javax.swing.*;
import java.awt.*;

public class Botao extends JButton {
    public Botao(String texto , Color cor) {
        setText(texto);
        setOpaque(true);
        setBackground(cor);
        setFont(new Font("courier", Font.PLAIN,25));
        setForeground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
