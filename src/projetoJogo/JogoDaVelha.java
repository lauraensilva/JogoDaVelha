package projetoJogo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JogoDaVelha extends Frame implements ActionListener {
    private Button[] buttons = new Button[9];
    private char jogador = 'X';

    public JogoDaVelha() {
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
        setTitle("Jogo da Velha");
        setSize(400, 400);
        setVisible(true);
        addWindowListener(new Ouvidoria());
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clicarBotao = (Button) e.getSource();
        if (clicarBotao.getLabel().equals("")) {
            clicarBotao.setLabel(String.valueOf(jogador));
            if (verificarVencedor()) {
                mostrarVencedor(jogador + " venceu!");
                resetarJogo();
            } else if (isPreenchido()) {
                mostrarVencedor("Deu Velha!");
                resetarJogo();
            } else {
                jogador = (jogador == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean verificarVencedor() {
        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getLabel().equals(buttons[i + 1].getLabel()) &&
                buttons[i + 1].getLabel().equals(buttons[i + 2].getLabel()) &&
                !buttons[i].getLabel().equals("")) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getLabel().equals(buttons[i + 3].getLabel()) &&
                buttons[i + 3].getLabel().equals(buttons[i + 6].getLabel()) &&
                !buttons[i].getLabel().equals("")) {
                return true;
            }
        }
        // Check diagonals
        if (buttons[0].getLabel().equals(buttons[4].getLabel()) &&
            buttons[4].getLabel().equals(buttons[8].getLabel()) &&
            !buttons[0].getLabel().equals("")) {
            return true;
        }
        if (buttons[2].getLabel().equals(buttons[4].getLabel()) &&
            buttons[4].getLabel().equals(buttons[6].getLabel()) &&
            !buttons[2].getLabel().equals("")) {
            return true;
        }
        return false;
    }

    private boolean isPreenchido() {
        for (Button button : buttons) {
            if (button.getLabel().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void mostrarVencedor(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetarJogo() {
        for (Button button : buttons) {
            button.setLabel("");
        }
        jogador = 'X';
    }
    
    class Ouvidoria implements WindowListener {
        public void windowOpened(WindowEvent e) { }
        public void windowClosing(WindowEvent e)  { System.exit(0); }
        public void windowClosed(WindowEvent e) { } // fecha
        public void windowIconified(WindowEvent e) { } 
        public void windowDeiconified(WindowEvent e) { }
        public void windowActivated(WindowEvent e) { }
        public void windowDeactivated(WindowEvent e) { }
} 


    public static void main(String[] args) {
        new JogoDaVelha();
    }
}