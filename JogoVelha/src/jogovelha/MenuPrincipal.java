package jogovelha;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuPrincipal extends JFrame implements ActionListener {

    palette cor = new palette();

    JPanel bloco01 = new JPanel(null);
    JPanel menu01 = new JPanel(null);
    JPanel menu02 = new JPanel(null);

    Font fonteTexto = new Font("Comic sans", Font.BOLD, 50);
    Font fonteButton = new Font("Comic sans", Font.BOLD, 24);

    JLabel title = new JLabel("Jogo da Velha", JLabel.CENTER);

    JButton jogar = new JButton("Jogar");
    JButton sair = new JButton("Sair");

    JButton JxJ = new JButton("<html><center>Jogador<br>X<br>Jogador</center></html>");
    JButton JxC = new JButton("<html><center>Jogador<br>X<br>computador</center></html>");

    public static void main(String[] args) {
        new MenuPrincipal().play(null, 0);
    }

    public void play(Point localTela, int first) {

        // Inicializando JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Jogo da velha - java");
        setSize(400, 600);
        setLayout(null);

        if (first == 0) {
            first++;
            setLocationRelativeTo(null);
        } else {
            setLocation(localTela);
        }

        bloco01.setBounds(0, 0, 400, 150);
        bloco01.setBackground(cor.bloco);

        title.setFont(fonteTexto);
        title.setBounds(0, 0, 400, 150);
        title.setForeground(cor.texto);
        bloco01.add(title);
        add(bloco01);

        jogar.setFont(fonteTexto);
        jogar.setBackground(cor.fundo);
        jogar.setForeground(cor.bloco);
        jogar.setBorder(BorderFactory.createLineBorder(cor.bloco, 2, true));
        jogar.setBounds(100, 225, 200, 100);
        jogar.addActionListener(this);
        menu01.add(jogar);

        sair.setFont(fonteTexto);
        sair.setBackground(cor.fundo);
        sair.setForeground(cor.bloco);
        sair.setBorder(BorderFactory.createLineBorder(cor.bloco, 2, true));
        sair.setBounds(100, 375, 200, 100);
        sair.addActionListener(this);
        menu01.add(sair);

        menu01.setBackground(cor.fundo);
        menu01.setBounds(0, 0, 400, 600);
        add(menu01);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == jogar) {
            remove(menu01);

            JxJ.setFont(fonteButton);
            JxJ.setBackground(cor.fundo);
            JxJ.setForeground(cor.bloco);
            JxJ.setBorder(BorderFactory.createLineBorder(cor.bloco, 2, true));
            JxJ.setBounds(100, 200, 200, 100);
            JxJ.addActionListener(this);
            menu02.add(JxJ);

            JxC.setFont(fonteButton);
            JxC.setBackground(cor.fundo);
            JxC.setForeground(cor.bloco);
            JxC.setBorder(BorderFactory.createLineBorder(cor.bloco, 2, true));
            JxC.setBounds(100, 350, 200, 100);
            JxC.addActionListener(this);
            menu02.add(JxC);

            menu02.setBounds(0, 0, 400, 600);
            menu02.setBackground(cor.fundo);

            add(menu02);
            validate();
            invalidate();
            repaint();
        }

        else if (evt.getSource() == sair) {
            System.exit(0);
        }

        if (evt.getSource() == JxJ) {
            dispose();
            new jogovelha.JxJ().play(getLocation());
        } else if (evt.getSource() == JxC) {
            dispose();
            new jogovelha.JxC().play(getLocation());
        }
    }

    /*
     * private void aguarde(int ms) { try
     * 
     * { Thread.sleep(ms); }catch( InterruptedException e) {
     * Thread.currentThread().interrupt(); } }
     */

}
