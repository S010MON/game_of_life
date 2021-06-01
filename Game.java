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
    
    //-------------------------------------------------------
   
	public static void main(String[] args) 
    {
    	Game game = new Game();
    	game.loop();
    }
	
	//-------------------------------------------------------
    
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
    
  //-------------------------------------------------------
    
    public void loop()
    {
    	boolean[][] u = new boolean[WIDTH/CELL_SIZE][HEIGHT/CELL_SIZE];
    	u = randomise(u);
    	board.setBoard(u);
    	
    	while(true)
    	{
    		try 	
    		{	TimeUnit.MILLISECONDS.sleep(100); 			}
    		catch(InterruptedException E)	
    		{	Thread.currentThread().interrupt();	}
    		
    		u = nextTurn(u);
    		board.setBoard(u);
    	}
    }

	//-------------------------------------------------------
    
    public boolean[][] randomise(boolean[][] u)
    {
        for(int x = 0; x < u.length; x++)
        {
        	for(int y = 0; y < u[0].length; y++)
            {
        		if(Math.random() < PROB)
        			u[x][y] = true;
        		else
        			u[x][y] = false;
            }
        }
        return u;
    }
    
  //-------------------------------------------------------
    
    public boolean[][] nextTurn(boolean[][] u)
    {
    	boolean[][] uCopy = new boolean[u.length][u[0].length];
    	
    	for(int x = 0; x < u.length; x++)					// Cycle through every pixel
        {
    		for(int y = 0; y < u[0].length; y++)
            {
    			int n = 0;									// The number of alive neighbours to the target pixel			
    	    	for(int i = x-1; i <= x+1; i++)				// Iterate through every neighbour to the current pixel (x,y)
    	        {
    	    		for(int j = y-1; j <= y+1; j++)
    	            {										// Check that the (i,j) coord is not out of bounds
    	    			if(i >= 0 && i < u.length && j >= 0 && j < u[0].length) 
    	    			{
    	    				if((i != x || j != y) && u[i][j])	// If the neighbour is true
    	    					n++;
    	    			}
    	            }
    	        }
    	    	
    	    	if(u[x][y]) 								// Rules for live cells
    	    	{
	    	    	if(n == 3 || n == 2)					
	    	    		uCopy[x][y] = true;					// Stay alive
	    	    	else
	    	    		uCopy[x][y] = false;				// Under population
    	    	}
    	    	
    	    	if(!u[x][y]) 								// Rules for dead cells
    	    	{
    	    		if(n == 3)
    	    			uCopy[x][y] = true;					// Reproduction
    	    	}
    	    	
            }
        }
    	return uCopy;
    }
    
    
}

