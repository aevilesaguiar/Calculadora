package br.com.aeviles.calc.view;

import br.com.aeviles.calc.view.model.Memoria;
import br.com.aeviles.calc.view.model.MemoryObservador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


//ActionListener é que eu vou ter um método para cada um dos botões
public class Display extends JPanel implements MemoryObservador {

    //os numeros que estão no label
    private final JLabel label;

    public Display() {

        //ele está dizendo que está interessado em ser notificado sempre   que o valor for modificado
        Memoria.getInstancia().adicionarObservador(this);


        setBackground(new Color(46,49,50));

        //ao inves de usar o valor mocado ele usou o valor direto da classe memoria
       label=new JLabel(Memoria.getInstancia().getTextoAtual());
       label.setForeground(Color.white);
       label.setFont(new Font("courier", Font.PLAIN,30));


       //GERENCIADOR DE LAYOUT MOSTRA O ALINHAMENTO TAMBÉM E OUTRAS
        setLayout( new FlowLayout(FlowLayout.RIGHT, 10,25));
       add(label);
    }

    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);

    }
}
