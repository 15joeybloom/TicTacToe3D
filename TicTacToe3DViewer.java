import javax.swing.JFrame;

/**
 * @author Joey Bloom
 * Extra Credit Assignement TicTacToe
   This program runs a TicTacToe3D game. User can
   click where to move. When there is a winner,
   the program will highlight.
*/
public class TicTacToe3DViewer
{
    public static void main(String[] args)
    {
        JFrame frame = new TicTacToe3DFrame("Tic Tac Toe 3D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
