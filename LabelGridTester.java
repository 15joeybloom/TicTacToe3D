import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author Joey Bloom
 * Assignment #
 * 
 */
public class LabelGridTester extends JFrame
{
    public static void main(String[] args)
    {
        new LabelGridTester();
    }
    public LabelGridTester()
    {
        setSize(500,500);
        setLayout(new GridBagLayout());
        JLabel[][][] labels = new JLabel[4][4][4];
        TicTacToe3DSquare[][][] listeners = new TicTacToe3DSquare[4][4][4];
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = .5;
        c.weighty = .5;
        class MoveListener implements MouseListener
        {
            public void mouseClicked(MouseEvent e)
            {
                TicTacToe3DSquare src = (TicTacToe3DSquare) e.getSource();
                System.out.println("You clicked: (" + src.getHeight()
                                   + ", " + src.getRow() + ", " +
                                   src.getColumn() + ")");
            }
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        }
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                for(int k = 0; k < 4; k++)
                {
                    labels[i][j][k] = new JLabel(i + ", " + j + ", " + k);
                    listeners[i][j][k] = new TicTacToe3DSquare("X",i,j,k);
                    listeners[i][j][k].addMouseListener(new MoveListener());
                    JLabel label = labels[i][j][k];
                    label.setBorder(
                        BorderFactory.createLineBorder(Color.black));
                    label.setHorizontalAlignment(JLabel.CENTER);
                    c.gridx = k;
                    add(label, c);
                    add(listeners[i][j][k],c);
                }
                c.gridy++;
            }
            GridBagConstraints spaceC = (GridBagConstraints) c.clone();
            spaceC.gridx = 0;
            spaceC.gridwidth = 4;
            add(new JPanel(), spaceC);
            c.gridy++;
        }
        setVisible(true);
    }
}

