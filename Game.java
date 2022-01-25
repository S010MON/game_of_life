import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class Game 
{
	final private double PROB = 0.5;
	public int WIDTH;
	public int HEIGHT;
	public int CELL_SIZE = 3;
	private Board board;
    private JFrame frame;
   
	public static void main(String[] args) 
    {
    	Game game = new Game();
    	game.loop();
    }

    public Game()
    {
        Dimension screenSize  = Toolkit.getDefaultToolkit().getScreenSize();
    	WIDTH = (int) screenSize.getWidth();
    	HEIGHT = (int) screenSize.getHeight();
       	frame = new JFrame("The Game Of Life");
        board = new Board(WIDTH, HEIGHT, CELL_SIZE);
        frame.setSize(WIDTH, HEIGHT+20);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(board);
        frame.setVisible(true);
    }

    public void loop()
    {
    	boolean[][] u = new boolean[WIDTH/CELL_SIZE][HEIGHT/CELL_SIZE];
    	u = randomise(u);
    	board.update(u);
    	
    	while(true)
    	{
    		try 	
    		{
				TimeUnit.MILLISECONDS.sleep(200);
			}
    		catch(InterruptedException E)	
    		{
				Thread.currentThread().interrupt();
			}
    		u = nextTurn(u);
    		board.update(u);
    	}
    }

    public boolean[][] randomise(boolean[][] u)
    {
        for(int x = 0; x < u.length; x++)
        {
        	for(int y = 0; y < u[0].length; y++)
            {
				u[x][y] = Math.random() < PROB;
            }
        }
        return u;
    }

    public boolean[][] nextTurn(boolean[][] alive)
    {
    	boolean[][] aliveCopy = new boolean[alive.length][alive[0].length];
    	
    	for(int x = 0; x < alive.length; x++)
        {
    		for(int y = 0; y < alive[0].length; y++)
            {
				int neighbours = countLiveNeighbours(x, y, alive);
				aliveCopy[x][y] = applyConwayRules(alive[x][y], neighbours);
            }
        }
    	return aliveCopy;
    }

	private int countLiveNeighbours(int x, int y, boolean[][] alive)
	{
		int neighbours = 0;
		for(int i = x-1; i <= x+1; i++)				// Iterate through every neighbour to the current pixel (x,y)
		{
			for(int j = y-1; j <= y+1; j++)
			{										// Check that the (i,j) coord is not out of bounds
				if(i >= 0 && i < alive.length && j >= 0 && j < alive[0].length)
				{
					if((i != x || j != y) && alive[i][j])	// If the neighbour is true
						neighbours++;
				}
			}
		}
		return neighbours;
	}

	private boolean applyConwayRules(boolean alive, int neighbours)
	{
		if(alive)
		{
			return neighbours == 3 || neighbours == 2;
		}
		else return neighbours == 3;
	}
}

