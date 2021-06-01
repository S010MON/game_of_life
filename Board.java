package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Board extends JPanel 
{
	public boolean[][] u;
	private int cellSize;
    
	//-------------------------------------------------------
	
	public Board(int width, int height, int cellSize)
	{
		this.cellSize = cellSize;
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.BLACK);
        initBoard();
    }

	//-------------------------------------------------------
    /**
     * Paints all the pixels in the universe {@code u}
     */
    public void paint(Graphics g) 
    {
        super.paint(g); 
        for(int x = 0; x < u.length; x++)
        {
        	for(int y = 0; y < u[0].length; y++)
            {
        		if(u[x][y])
        		{
        			g.setColor(Color.white);
        			g.fillRect(x * cellSize, y * cellSize,cellSize,cellSize);
        		}
            }
        }
    }
    
  //-------------------------------------------------------
    
    public void setBoard(boolean[][] board)
    {
    	u = board;
    	repaint();    	
    }
       
    //-------------------------------------------------------

    private void initBoard()
    {
    	u = new boolean[WIDTH/cellSize][HEIGHT/cellSize];
    	for(int x = 0; x < u.length; x++)
        {
        	for(int y = 0; y < u[0].length; y++)
            {
       			u[x][y] = false;
            }
        }
    }
}
