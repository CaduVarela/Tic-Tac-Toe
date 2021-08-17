package JogoVelha;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TelaVencedor extends JFrame implements ActionListener {

    palette cor = new palette();

    JPanel bloco01 = new JPanel(null);
    JPanel bloco02 = new JPanel(null);

    Font fonteTitle = new Font("SansSerif", Font.BOLD, 50);
    Font fonteTexto = new Font("SansSerif", Font.PLAIN, 30);

    JLabel title = new JLabel("Fim de Jogo!", JLabel.CENTER);
    JLabel texto = new JLabel("", JLabel.CENTER);

    JButton sair = new JButton("Sair");
    JButton menu = new JButton("Menu");

    public void play(Point localTela, int ganhador, String jogador, String modo) {

        // Inicializando JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Jogo da velha - java");
        setSize(400, 600);
        setLocation(localTela);
        setLayout(null);

        title.setFont(fonteTitle);
        title.setForeground(cor.texto);
        title.setBounds(0, 0, 400, 150);

        bloco01.add(title);
        bloco01.setBackground(cor.bloco);
        bloco01.setBounds(0, 0, 400, 150);

        texto.setFont(fonteTexto);
        texto.setText(
                "<html><center>Vitória do jogador " + ganhador + "<br><center>(simbolo: \"" + jogador + "\")</html>");
        texto.setForeground(cor.texto);
        texto.setBounds(0, 0, 400, 100);

        bloco02.setBackground(cor.colorX);
        bloco02.add(texto);
        bloco02.setBackground(cor.colorX);
        bloco02.setBounds(0, 150, 400, 100);

        menu.setFont(fonteTitle);
        menu.setBackground(cor.fundo);
        menu.setForeground(cor.bloco);
        menu.setBorder(BorderFactory.createLineBorder(cor.bloco, 2, true));
        menu.setBounds(40, 300, 170, 200);
        menu.addActionListener(this);

        sair.setFont(fonteTitle);
        sair.setBackground(cor.fundo);
        sair.setForeground(cor.bloco);
        sair.setBorder(BorderFactory.createLineBorder(cor.bloco, 2, true));
        sair.setBounds(220, 300, 130, 200);
        sair.addActionListener(this);

        add(bloco01);
        add(bloco02);
        add(menu);
        add(sair);

        getContentPane().setBackground(cor.fundo);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == menu) {
            dispose();
            new MenuPrincipal().play(getLocation(), 1);
        } else if (evt.getSource() == sair) {
            System.exit(0);
        }
    }
}
