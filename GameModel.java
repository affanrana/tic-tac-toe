/*GameModel
 * Programmer name: Ethan Cai
 * Last modified: 01/23/22
 * Description of program: this is the class behind the scenes to do various calculations and determine winners, who's turn it is, etc.*/

//importing classes & packages
import java.awt.*;
import java.awt.event.*;

public class GameModel
{
  //declare and initialize variables
  protected int moveCounter = 0;                      //how many moves player did
  protected String [][] grid;                           //the 3 x 3 grid
  private GameView gameView = new GameView(this);         //the view for the game
  protected boolean winner = false;                      //if there is a winner or not
  private int xRowRandom;                          //contains x location of computer move
  private int yColumnRandom;                       //contains y location of computer move
  
  //constructor for default game
  public GameModel()
  {
    this.grid = new String[3][3];
    this.moveCounter = 0;
  }
  
  //set view class
  public void setView(GameView gameView)
  {
    this.gameView = gameView;
  }
  
  //method used to keep count of moves
  public int getMoveCounter()
  {
    return moveCounter; 
  }
  
  //method for setting moveCounter
  public void setMoveCounter(int moveCounter)
  {
    this.moveCounter = moveCounter;
  }
  
  //method for adding 1 to move counter
  public void addMoveCounter()
  {
    moveCounter++;
  }
  
  //method used to get the grid
  public String[][] getGrid()
  {
    return grid;
  }
  
  //method that occurs when button is clicked on grid
  //it updates view, tells view to have comp update random position, and checks winner
  public void buttonContinue(ActionEvent e)
  {
    gameView.obtainLocation(e);
    gameView.updateX(e);
    addMoveCounter();
    if (moveCounter < 5)
    {
      for (int counter = 0; counter < 1;) //loop for comp
      {
        xRowRandom = (int)Math.round(Math.random()*2);
        yColumnRandom = (int)Math.round(Math.random()*2);
        if (gameView.box[xRowRandom][yColumnRandom].isEnabled())
        {
          checkWinner(false);
          gameView.updateO(xRowRandom, yColumnRandom);
          checkWinner(false);
          counter++;
        }
      }
    }
    gameView.updateX(e);
    checkWinner(true);
  }
  
  //method updates view to reset game
  public void resetGameView() 
  {
    for(int x = 0; x < 3; x++) 
    {
      for(int y = 0; y < 3; y++) 
      {
        gameView.updateResetGame(x , y);
      }
    }
  }
  
  //method updates view to start next round
  public void nextRoundView()
  {
    for(int x = 0; x < 3; x++) 
    {
      for(int y = 0; y < 3; y++) 
      {
        gameView.updateNextRound(x , y);
      }
    }
  }
  
  //method checks for winner
  private void checkWinner(boolean increment)
  {
    //declare and initialize variables
    int firstRowX =0;   //contains each win condition for player
    int secondRowX =0;
    int thirdRowX=0;
    int firstColumnX=0;
    int secondColumnX=0;
    int thirdColumnX=0;
    int topLeftX=0;
    int topRightX=0;
    
    int firstRowO =0;  //contains each win condition for comp
    int secondRowO =0;
    int thirdRowO=0;
    int firstColumnO=0;
    int secondColumnO=0;
    int thirdColumnO=0;
    int topLeftO=0;
    int topRightO=0;
    
    //check for win in first row
    for(int y = 0; y < 3; y++)
    {
      if (grid[0][y] == "X")
        firstRowX++;
      if (grid[0][y] == "O")
        firstRowO++;
    }
    
    //check for win in second row
    for(int y = 0; y < 3; y++)
    {
      if (grid[1][y] == "X")
        secondRowX++;
      if (grid[1][y] == "O")
        secondRowO++;
    }
    
    //check for win in third row
    for(int y = 0; y < 3; y++)
    {
      if (grid[2][y] == "X")
        thirdRowX++;
      if (grid[2][y] == "O")
        thirdRowO++;
    }
    
    //check for win in first column
    for(int x = 0; x < 3; x++)
    {
      if (grid[x][0] == "X")
        firstColumnX++;
      if (grid[x][0] == "O")
        firstColumnO++;
    }
    
    //check for win in second column
    for(int x = 0; x < 3; x++)
    {
      if (grid[x][1] == "X")
        secondColumnX++;
      if (grid[x][1] == "O")
        secondColumnO++;
    }
    
    //check for win in third column
    for(int x = 0; x < 3; x++)
    {
      if (grid[x][2] == "X")
        thirdColumnX++;
      if (grid[x][2] == "O")
        thirdColumnO++;
    }
    
    //check for win in top left diagonal
    if (grid[0][0] == "X" && grid[1][1] == "X" && grid[2][2] == "X")
      topLeftX+=3;
    if (grid[0][0] == "O" && grid[1][1] == "O" && grid[2][2] == "O")
      topLeftO+=3;
    
    //check for in top right diagonal
    if (grid[0][2] == "X" && grid[1][1] == "X" && grid[2][0] == "X")
      topRightX+=3;
    if (grid[0][2] == "O" && grid[1][1] == "O" && grid[2][0] == "O")
      topRightO+=3;
    
    //determine if player won by looking through each win condition
    if (firstRowX == 3 || secondRowX  == 3 || thirdRowX == 3 || firstColumnX == 3 || secondColumnX == 3 || thirdColumnX == 3 ||topLeftX == 3 || topRightX == 3)
    {
      winner = true;
      if (increment == true) //checks whether win counter should be updated
      {
        gameView.win++;
      }
      gameView.updateNextRoundEnable();
      gameView.updateWinCounter();
    }
    
    //determine if comp won by looking through win conditions
    else if (firstRowO  == 3 || secondRowO  == 3 || thirdRowO == 3 || firstColumnO == 3 || secondColumnO == 3 || thirdColumnO == 3 || topLeftO == 3 || topRightO == 3)
    {
      winner = true;
      if (increment == true) //checks whether loss counter should be updated
      {
        gameView.loss++;
      }
      gameView.updateNextRoundEnable();
      gameView.updateLossCounter();
    }
    
    //determine if tie has occured
    else if (moveCounter == 5)
    {
      gameView.updateNextRoundEnable();
    }
  }
}//ssalc