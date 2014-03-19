/**
 * Joey Bloom
 * Extra Credit Assignment
 * Creates a 4x4 3D tic tac toe game. 
 * it is displayed as 4 grids, but imagine it
 * as if it were a 4x4x4 cube. likewise, one can
 * get 4 in a row in any direction on the cube.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe3DFrame extends JFrame
{
    private JLabel display;
    private GridBagConstraints displayC;
    private GridBagConstraints newGameC;
    private JLabel[][][] board;
    private TicTacToe3DSquare[][][] listeners;
    private GridBagConstraints boardC;
    private String iAmTheTitle;
    /**
     * Constructs with the given title
     */
    public TicTacToe3DFrame(String title)
    {
        super(title);
        iAmTheTitle = title;
        setSize(300,500);
        setLayout(new GridBagLayout());
        
        display = new JLabel();
        display.setText("It is X's turn!");
        display.setHorizontalAlignment(JLabel.CENTER);
        displayC = new GridBagConstraints();
        displayC.gridx = 0;
        displayC.gridy = 0;
        displayC.gridwidth = 1;
        displayC.gridheight = 2;
        displayC.fill = GridBagConstraints.BOTH;
        displayC.weightx = 0.5;
        displayC.weighty = 0.5;
        add(display, displayC);
        
        makeBoard();
        
        whoseTurn = "X";
    } 
    /**
     * Helper method to initialize the board
     */
    private void makeBoard()
    {
        class MoveListener implements MouseListener
        {
            public void mouseClicked(MouseEvent e)
            {
                TicTacToe3DSquare src = (TicTacToe3DSquare) e.getSource();
                int h = src.getHeight();
                int r = src.getRow();
                int c = src.getColumn();
                JLabel square = board[h][r][c];
                //if space is full, don't move there.
                if(!src.getText().equals(" "))
                    return;
                //if already a winner, don't move.
                if(display.getText().contains("Winner"))
                    return;
                square.setText(whoseTurn());
                if(hasWinner(src))
                {
                    display.setText("Winner: " + whoseTurn());
                    return;
                }
                nextTurn();
                display.setText("It is " + whoseTurn() + "'s turn!");
            }
            public void mouseEntered(MouseEvent e){}
            public void mouseExited(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        }
        board = new JLabel[4][4][4];
        listeners = new TicTacToe3DSquare[4][4][4];
        boardC = new GridBagConstraints();
        boardC.gridx = 2;
        boardC.gridy = 0;
        boardC.fill = GridBagConstraints.BOTH;
        boardC.weightx = 0.25;
        boardC.weighty = 0.5;
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0 ; j < 4; j++)
            {
                for(int k = 0; k < 4; k++)
                {
                    board[i][j][k] = new JLabel(" ");
                    listeners[i][j][k] = new TicTacToe3DSquare(" ",i,j,k);
                    JLabel square = board[i][j][k];
                    TicTacToe3DSquare listener = listeners[i][j][k];
                    square.setHorizontalAlignment(JLabel.CENTER);
                    square.setBorder(
                        BorderFactory.createLineBorder(Color.black));
                    listener.addMouseListener(new MoveListener());
                    boardC.gridx = 2 + k;
                    add(square, boardC);
                    add(listener, boardC);
                }
                boardC.gridy++;
            }
            GridBagConstraints spaceC = (GridBagConstraints) boardC.clone();
            spaceC.gridx = 2;
            spaceC.gridwidth = 4;
            add(new JPanel(), spaceC);
            boardC.gridy++;
        }
    }
    
    private String whoseTurn;
    /**
     * Checks if the most recent move has produced a winner.
     * @param sq the most recent move
     * @return <code>true</code> if there is a winner
     */
    private boolean hasWinner(TicTacToe3DSquare sq)
    {
        final int H = sq.getHeight();
        final int R = sq.getRow();
        final int C = sq.getColumn();
        //straight lines
        if(board[H][R][0].getText().equals(whoseTurn()) &&
           board[H][R][1].getText().equals(whoseTurn()) &&
           board[H][R][2].getText().equals(whoseTurn()) &&
           board[H][R][3].getText().equals(whoseTurn()))
        {
            for(int i = 0; i < 4 ; i++)
                board[H][R][i].setBorder(BorderFactory.createLineBorder(Color.red,2));
            return true;
        }
        if(board[H][0][C].getText().equals(whoseTurn()) &&
           board[H][1][C].getText().equals(whoseTurn()) &&
           board[H][2][C].getText().equals(whoseTurn()) &&
           board[H][3][C].getText().equals(whoseTurn()))
        {
            for(int i = 0; i < 4 ; i++)
                board[H][i][C].setBorder(BorderFactory.createLineBorder(Color.red,2));
            return true;
        }
        if(board[0][R][C].getText().equals(whoseTurn()) &&
           board[1][R][C].getText().equals(whoseTurn()) &&
           board[2][R][C].getText().equals(whoseTurn()) &&
           board[3][R][C].getText().equals(whoseTurn()))
        {
            for(int i = 0; i < 4 ; i++)
                board[i][R][C].setBorder(BorderFactory.createLineBorder(Color.red,2));
            return true;
        }
        //if on a RC diagonal
        if(R == C || R + C == 3)
        {
            //if top-left to bottom-right
            if(board[H][0][0].getText().equals(whoseTurn()) &&
               board[H][1][1].getText().equals(whoseTurn()) &&
               board[H][2][2].getText().equals(whoseTurn()) &&
               board[H][3][3].getText().equals(whoseTurn()))
            {
                for(int i = 0; i < 4 ; i++)
                {
                    board[H][i][i].setBorder(BorderFactory.createLineBorder(Color.red,2));
                }
                return true;
            }
            //if bottom-left to top-right
            if(board[H][3][0].getText().equals(whoseTurn()) &&
               board[H][2][1].getText().equals(whoseTurn()) &&
               board[H][1][2].getText().equals(whoseTurn()) &&
               board[H][0][3].getText().equals(whoseTurn()))
            {
                for(int i = 0; i < 4 ; i++)
                {
                    board[H][i][3-i].setBorder(BorderFactory.createLineBorder(Color.red,2));
                }
                return true;
            }
        }
        //if on a HR diagonal
        if(H == R || H + R == 3)
        {
            //if top-left to bottom-right
            if(board[0][0][C].getText().equals(whoseTurn()) &&
               board[1][1][C].getText().equals(whoseTurn()) &&
               board[2][2][C].getText().equals(whoseTurn()) &&
               board[3][3][C].getText().equals(whoseTurn()))
            {
                for(int i = 0; i < 4 ; i++)
                {
                    board[i][i][C].setBorder(BorderFactory.createLineBorder(Color.red,2));
                }
                return true;
            }
            //if bottom-left to top-right
            if(board[3][0][C].getText().equals(whoseTurn()) &&
               board[2][1][C].getText().equals(whoseTurn()) &&
               board[1][2][C].getText().equals(whoseTurn()) &&
               board[0][3][C].getText().equals(whoseTurn()))
            { 
                for(int i = 0; i < 4 ; i++)
                {
                    board[i][3-i][C].setBorder(BorderFactory.createLineBorder(Color.red,2));
                }
                return true;
            }
        }
        //if on a HC diagonal
        if(H == C || H + C == 3)
        {
            //if top-left to bottom-right
            if(board[0][R][0].getText().equals(whoseTurn()) &&
               board[1][R][1].getText().equals(whoseTurn()) &&
               board[2][R][2].getText().equals(whoseTurn()) &&
               board[3][R][3].getText().equals(whoseTurn()))
            { 
                for(int i = 0; i < 4 ; i++)
                {
                    board[i][R][i].setBorder(BorderFactory.createLineBorder(Color.red,2));
                }
                return true;
            }
            //if bottom-left to top-right
            if(board[3][R][0].getText().equals(whoseTurn()) &&
               board[2][R][1].getText().equals(whoseTurn()) &&
               board[1][R][2].getText().equals(whoseTurn()) &&
               board[0][R][3].getText().equals(whoseTurn()))
            { 
                for(int i = 0; i < 4 ; i++)
                {
                    board[i][R][3-i].setBorder(BorderFactory.createLineBorder(Color.red,2));
                }
                return true;
            }
        }
        //3D diagonals
        //these check always because too hard to 
        //determine when to and when not to
        
        //grid 0 top-left to grid 3 bottom-right
        if(board[0][0][0].getText().equals(whoseTurn()) &&
           board[1][1][1].getText().equals(whoseTurn()) &&
           board[2][2][2].getText().equals(whoseTurn()) &&
           board[3][3][3].getText().equals(whoseTurn()))
        {
            for(int i = 0; i < 4 ; i++)
            {
                board[i][i][i].setBorder(BorderFactory.createLineBorder(Color.red,2));
            }
            return true;
        }
        //grid 0 bottom-right to grid 3 top-left
        if(board[0][3][3].getText().equals(whoseTurn()) &&
           board[1][2][2].getText().equals(whoseTurn()) &&
           board[2][1][1].getText().equals(whoseTurn()) &&
           board[3][0][0].getText().equals(whoseTurn()))
        {
            for(int i = 0; i < 4 ; i++)
            {
                board[i][3-i][3-i].setBorder(BorderFactory.createLineBorder(Color.red,2));
            }
            return true;
        }
        //grid 0 top-right to grid 3 bottom-left
        if(board[0][0][3].getText().equals(whoseTurn()) &&
           board[1][1][2].getText().equals(whoseTurn()) &&
           board[2][2][1].getText().equals(whoseTurn()) &&
           board[3][3][0].getText().equals(whoseTurn()))
        {
            for(int i = 0; i < 4 ; i++)
            {
                board[i][i][3-i].setBorder(BorderFactory.createLineBorder(Color.red,2));
            }
            return true;
        }
        //grid 0 bottom-left to grid 3 top-right
        if(board[0][3][0].getText().equals(whoseTurn()) &&
           board[1][2][1].getText().equals(whoseTurn()) &&
           board[2][1][2].getText().equals(whoseTurn()) &&
           board[3][0][3].getText().equals(whoseTurn()))
        {
            for(int i = 0; i < 4 ; i++)
            {
                board[i][3-i][i].setBorder(BorderFactory.createLineBorder(Color.red,2));
            }
            return true;
        }
        return false;
    }
    /**
     * Returns whose turn it currently is
     * @return whose turn it is
     */
    public String whoseTurn()
    {
        return whoseTurn;
    }
    /**
     * Toggles whose turn it is
     */
    public void nextTurn()
    {
        if(whoseTurn().equals("X"))
            whoseTurn = "O";
        else
            whoseTurn = "X";
    }
}