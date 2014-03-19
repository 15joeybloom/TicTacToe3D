/**
 * @author Joey Bloom
 * Assignment #
 * A TicTacToe3DSquare is a JLabel with 3 additional instance
 * variables that hold its height, row, and column.
 */

import javax.swing.JButton;
import javax.swing.JLabel;

public class TicTacToe3DSquare extends JLabel
{
    private int height;
    private int row;
    private int column;
    
    public TicTacToe3DSquare(String title, int h, int r, int c)
    {
        super(title);
        height = h;
        row = r;
        column = c;
    }
    /**
     * Returns the height
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }
    /**
     * Returns the row
     * @return the row
     */
    public int getRow()
    {
        return row;
    }
    /**
     * Returns the column
     * @return the column
     */
    public int getColumn()
    {
        return column;
    }
}

