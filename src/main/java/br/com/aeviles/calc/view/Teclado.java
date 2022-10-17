package br.com.aeviles.calc.view;

import br.com.aeviles.calc.view.model.Memoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Teclado extends JPanel implements ActionListener {

    private final Color COR_CINZA_ESCURO = new Color(68, 68, 68);
    private final Color COR_CINZA_CLARO = new Color(99, 99, 99);
    private final Color COR_LARANJA = new Color(242, 163, 60);

    public Teclado() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();


        //5 linhas e 4 colunas
        setLayout(layout);

        //elemento ocupe o mesmo espaço ou seja mesmo tamanho
        c.weightx = 1;
        c.weighty = 1;

        //PREENCHE O ESPAÇO EM BRANCO DOS BOTÕES
        c.fill = GridBagConstraints.BOTH;

        //lINHA 1
        c.gridwidth = 3;
        adicionarBotao("AC", COR_CINZA_ESCURO, c, 0, 0);
        c.gridwidth = 1;
        adicionarBotao("/", COR_LARANJA, c, 3, 0);
        //lINHA 2
        adicionarBotao("7", COR_CINZA_CLARO, c, 0, 1);
        adicionarBotao("8", COR_CINZA_CLARO, c, 1, 1);
        adicionarBotao("9", COR_CINZA_CLARO, c, 2, 1);
        adicionarBotao("X", COR_LARANJA, c, 3, 1);
        //lINHA 3
        adicionarBotao("4", COR_CINZA_CLARO, c, 0, 2);
        adicionarBotao("5", COR_CINZA_CLARO, c, 1, 2);
        adicionarBotao("6", COR_CINZA_CLARO, c, 2, 2);
        adicionarBotao("-", COR_LARANJA, c, 3, 2);
        //lINHA 4
        adicionarBotao("1", COR_CINZA_CLARO, c, 0, 3);
        adicionarBotao("2", COR_CINZA_CLARO, c, 1, 3);
        adicionarBotao("3", COR_CINZA_CLARO, c, 2, 3);
        adicionarBotao("+", COR_LARANJA, c, 3, 3);
        //lINHA 5
        c.gridwidth = 2;
        adicionarBotao("0", COR_CINZA_CLARO, c, 0, 4);
        c.gridwidth = 1;
        adicionarBotao(",", COR_CINZA_CLARO, c, 2, 4);
        adicionarBotao("=", COR_LARANJA, c, 3, 4);


    }

    private void adicionarBotao(String texto, Color cor,
                                GridBagConstraints c, int x, int y) {

        c.gridx = x;
        c.gridy = y;
        Botao botao = new Botao(texto, cor);
        botao.addActionListener(this);
        add(botao, c);
    }

    //ActionListener implementa o método abaixo e apartir dele é que terei um método para cada um dos botões
    @Override
    public void actionPerformed(ActionEvent e) {

        /*
         * O instanceof é um operador que permite testar se um objeto é uma instância de um tipo específico de uma class,
         * subclass ou interface. O instanceof em java também é conhecida como operador de comparação de tipos, isso
         * porque compara a instância com o tipo.
         * */
        if (e.getSource() instanceof JButton) {

            JButton botao = (JButton) e.getSource();
            //sempre um botão for pressionado vai ser o comando
            Memoria.getInstancia().processarComando(botao.getText());


        }
    }
}
