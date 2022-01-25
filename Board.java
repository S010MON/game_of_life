import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Board extends JPanel
{
	public boolean[][] current;
	private int cellSize;
    private int width;
    private int height;

	public Board(int width, int height, int cellSize)
	{
		this.cellSize = cellSize;
        this.current = initBoard();
        this.width = width;
        this.height = height;

		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.BLACK);
    }

    public void paint(Graphics g) 
    {
        super.paint(g);
        paintAllCells(g);
    }

    private void paintAllCells(Graphics g)
    {
        for(int x = 0; x < current.length; x++)
        {
            for (int y = 0; y < current[0].length; y++)
            {
                drawCell(g, x, y);
            }
        }
    }

    private void drawCell(Graphics g, int x, int y)
    {
        if(current[x][y])
        {
            g.setColor(Color.white);
        }
        else
        {
            g.setColor(Color.black);
        }
        g.fillRect(x * cellSize, y * cellSize,cellSize,cellSize);
    }

    public void update(boolean[][] board)
    {
        current = board;
    	repaint();    	
    }

    private boolean[][] initBoard()
    {
    	boolean[][] board = new boolean[width/cellSize][height/cellSize];
    	for(int x = 0; x < board.length; x++)
        {
        	for(int y = 0; y < board[0].length; y++)
            {
       			board[x][y] = false;
            }
        }
        return board;
    }
}
