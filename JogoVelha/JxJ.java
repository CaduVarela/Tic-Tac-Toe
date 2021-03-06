package JogoVelha;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.metal.MetalButtonUI;

public class JxJ extends JFrame implements ActionListener {

    private final JPanel grid = new JPanel(new GridLayout(0, 3));

    private int x, y, i, j;

    palette cor = new palette();

    private final Font fonte = new Font("SansSerif", Font.BOLD, 50);
    private final Font fonteVelha = new Font("SansSerif", Font.BOLD, 45);
    private final Font fonteMenor = new Font("SansSerif", Font.PLAIN, 30);
    private final JLabel textVelha = new JLabel("Velha", JLabel.CENTER);
    private final JLabel marcaRodada = new JLabel("", JLabel.CENTER);

    private final JPanel bloco01 = new JPanel(null);

    // Game components
    private final String velha[][] = new String[3][3];
    private final JButton pos[] = new JButton[9];

    private final boolean ocupado[] = new boolean[9];
    private int rodada = 0;
    private String jogador;
    public int ganhador = 0;

    public void play(Point localTela) {

        // Inicializando JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Jogo da velha - java");
        setSize(400, 600);
        setLayout(null);
        setLocation(localTela);

        // Desenhando tela
        iniciarVelha();
        iniciarElementos();

        add(bloco01);
        add(marcaRodada);
        add(grid);

        getContentPane().setBackground(cor.fundo);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {

        if (rodada % 2 == 0) {
            jogador = "X";
        } else {
            jogador = "O";
        }
        rodada++;

        whichButton(evt);

        ocupado[j] = true;
        pos[j].setUI(new MetalButtonUI() {
            protected Color getDisabledTextColor() {
                if (jogador.equals("X")) {
                    return cor.colorX;
                } else {
                    return cor.colorO;
                }
            }
        });
        pos[j].setEnabled(false);
        pos[j].setText("" + jogador);

        marcaRodada.setText("Rodada: " + (rodada + 1));

        winCondition();
    }

    private void winCondition() {

        int countX, countY;

        for (y = 0; y < 3; y++) {
            countX = 0;
            countY = 0;
            for (x = 0; x < 3; x++) {
                // Condi????o horizontal
                if (velha[y][0].equals(velha[y][x])) {
                    countX++;
                }
                if (countX == 3) {
                    if (velha[y][0].equals("X")) {
                        ganhador = 1;
                    } else {
                        ganhador = 2;
                    }
                }

                // Condi????o vertical
                if (velha[0][y].equals(velha[x][y])) {
                    countY++;
                }
                if (countY == 3) {
                    if (velha[y][0].equals("X")) {
                        ganhador = 1;
                    } else {
                        ganhador = 2;
                    }
                }
            }
        }

        if (velha[0][0].equals(velha[1][1]) && velha[0][0].equals(velha[2][2])) {
            if (velha[1][1].equals("X")) {
                ganhador = 1;
            } else {
                ganhador = 2;
            }
        } else if (velha[2][0].equals(velha[1][1]) && velha[2][0].equals(velha[0][2])) {
            if (velha[1][1].equals("X")) {
                ganhador = 1;
            } else {
                ganhador = 2;
            }
        }

        if (ganhador != 0 || rodada == 9) {
            dispose();
            new TelaVencedor().play(getLocation(), ganhador, jogador, "JxJ");
        }
    }

    private void iniciarElementos() {

        textVelha.setFont(fonte);
        textVelha.setForeground(cor.texto);
        textVelha.setBounds(0, 0, 400, 150);
        bloco01.add(textVelha);

        marcaRodada.setText("Rodada: " + (rodada + 1));
        marcaRodada.setFont(fonteMenor);
        marcaRodada.setForeground(cor.bloco);
        marcaRodada.setBounds(0, 480, 400, 50);

        bloco01.setBackground(cor.bloco);
        bloco01.setBounds(0, 0, 400, 150);
    }

    private void iniciarVelha() {

        for (i = 0; i < 9; i++) {
            pos[i] = new JButton("");
            pos[i].setSize(80, 80);
            pos[i].setFont(fonteVelha);
            pos[i].addActionListener(this);
            pos[i].setBackground(cor.fundo);

            grid.add(pos[i]);
        }

        for (y = 0; y < 3; y++) {
            for (x = 0; x < 3; x++) {
                velha[y][x] = "" + x + "" + y;
            }
        }
        // BorderFactory.createMatteBorder(top, left, botton, right, color)
        pos[0].setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, cor.velha));
        pos[1].setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, cor.velha));
        pos[2].setBorder(BorderFactory.createMatteBorder(0, 2, 2, 0, cor.velha));
        pos[3].setBorder(BorderFactory.createMatteBorder(2, 0, 2, 2, cor.velha));
        pos[4].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, cor.velha));
        pos[5].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 0, cor.velha));
        pos[6].setBorder(BorderFactory.createMatteBorder(2, 0, 0, 2, cor.velha));
        pos[7].setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, cor.velha));
        pos[8].setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, cor.velha));

        grid.setBounds(80, 200, 240, 240);

    }

    private void whichButton(ActionEvent evt) {

        if (evt.getSource() == pos[0] && ocupado[0] == false) {
            j = 0;
            velha[0][0] = jogador;
        }

        else if (evt.getSource() == pos[1] && ocupado[1] == false) {
            j = 1;
            velha[0][1] = jogador;
        }

        else if (evt.getSource() == pos[2] && ocupado[2] == false) {
            j = 2;
            velha[0][2] = jogador;
        }

        else if (evt.getSource() == pos[3] && ocupado[3] == false) {
            j = 3;
            velha[1][0] = jogador;
        }

        else if (evt.getSource() == pos[4] && ocupado[4] == false) {
            j = 4;
            velha[1][1] = jogador;
        }

        else if (evt.getSource() == pos[5] && ocupado[5] == false) {
            j = 5;
            velha[1][2] = jogador;
        }

        else if (evt.getSource() == pos[6] && ocupado[6] == false) {
            j = 6;
            velha[2][0] = jogador;
        }

        else if (evt.getSource() == pos[7] && ocupado[7] == false) {
            j = 7;
            velha[2][1] = jogador;
        }

        else if (evt.getSource() == pos[8] && ocupado[8] == false) {
            j = 8;
            velha[2][2] = jogador;
        }
    }
}
