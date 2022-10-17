package br.com.aeviles.calc.view;

import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {


    public Calculadora() {

        organizarLayout();

        //abre uma tela pequena
        setVisible(true);
        //sair da aplicação
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TAMANHO DA TELA
        setSize(232,322);
        //a aplicação abre no centro da tela
        setLocationRelativeTo(null);

    }

    //método para organizar layout
    private void organizarLayout() {

        //BorderLayout gerenciador de layout , ele define a tela em norte, sul, leste e oeste
        setLayout(new BorderLayout());


        Display display= new Display();
            display.setPreferredSize(new Dimension(233, 60));
            add(display, BorderLayout.NORTH);

            Teclado teclado= new Teclado();
            add(teclado, BorderLayout.CENTER);



    }

    public static void main(String[] args) {
        new Calculadora();


    }



}
