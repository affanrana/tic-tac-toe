//GameView
//This is the class used to display all the different pieces of information useful to the user and also to show a 3 x 3 table of buttons (this is used in traditional tic tac toe)
//Created By: Affan Rana 
//Last Modified: January 23, 2022

//Importing classes & packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameView extends JPanel
{
  //Variable declaration 
  private GameModel gameModel;//Contains the games model
  private static GameController gameController;//Contains the games controller
  private JPanel gameBox = new JPanel(new FlowLayout());//Contains a panel which includes the other panels
  private JPanel threeByThreeLayout = new JPanel();//Conatins a panel that contains the 9 buttons which are used as a grid for the Xs and Os
  private JPanel top = new JPanel(new FlowLayout());//Contains the panel which includes the reset, exit, and score
  protected static JButton[][] box = new JButton[3][3];//Contains the buttons
  protected static JButton resetButton = new JButton("Restart Game");//Contains the button to reset the threeByThreeLayout
  protected static JButton nextRoundButton = new JButton("Next Round");//Contains the button to reset the threeByThreeLayout
  protected static int win = 0;//Contains the amount of wins
  protected static int loss = 0;//Contains the amount of losses
  protected static JLabel winCounter = new JLabel("Current wins: " + win);//Contains the number of wins in the current session
  protected static JLabel lossCounter = new JLabel("Current losses: " + loss);//Contains the number of losses in the current session
  protected int xLocation = 0;//Contains the x and y location of teh button that is pressed
  protected int yLocation = 0; 
  
  //Creates a new constructor
  public GameView (GameModel model)
  {
    super();
    this.gameModel = model;
    layoutView();
  }
  
  //Creates a method where the frame for the tic tac toe game is made
  private void layoutView()
  {
    threeByThreeLayout.setLayout(new GridLayout(3, 3));
    gameBox.add(threeByThreeLayout, BorderLayout.CENTER);
    gameBox.setBackground(Color.ORANGE);
    nextRoundButton.setEnabled(false);
    top.add(nextRoundButton);
    top.add(resetButton);
    top.add(winCounter);
    top.add(lossCounter);
    top.setBackground(Color.ORANGE);
    this.add(gameBox);
    this.add(top);
    this.setBackground(Color.LIGHT_GRAY);
    for(int x = 0; x < 3 ; x++) 
    {
      for(int y = 0; y < 3 ; y++) 
      {
        box[x][y] = new JButton();
        box[x][y].setPreferredSize(new Dimension(100,100));
        box[x][y].setText("");
        threeByThreeLayout.add(box[x][y]);
      }
    }
    //this.setVisible(true);
  }
  
  //Creates a method that is used by the model class, where it finds the x and y-coordinates of the pressed button
  public void obtainLocation(ActionEvent e) 
  {
    for(int x = 0; x < 3 ; x++) 
    {
      for(int y = 0; y < 3 ; y++) 
      {
        if(((JButton) e.getSource()) == box[x][y]) 
        {
          gameModel.grid[x][y] = "X";
        }
      }
    }
  }
  
  //Creates a method where it updates the view and the win counter
  public void updateWinCounter()
  {
    winCounter.setText("Current wins: " + win);
  }
  
  //Creates a method where it updates the view and the loss counter
  public void updateLossCounter()
  {
    lossCounter.setText("Current losses: " + loss);
  }
  
  //Creates a method where it updates the next round button to become enabled after winning or losing
  public void updateNextRoundEnable()
  {
    nextRoundButton.setEnabled(true);
  }
  
  //Creates a method where it updates the view and the buttons with an X
  public void updateX(ActionEvent e)
  {
    if (gameModel.winner == true)
    {
      for(int x = 0; x < 3; x++) 
      {
        for(int y = 0; y < 3; y++) 
        {
          box[x][y].setEnabled(false);
        }
      } 
    }
    else
    {
      ((JButton) e.getSource()).setText("X");
      ((JButton) e.getSource()).setEnabled(false);
    }
  }
  
  //Creates a method where it updates the view and the buttons with an O
  public void updateO(double xRowRandom, double yColumnRandom)
  {
    if (gameModel.winner == false)
    {
      box[(int)xRowRandom][(int)yColumnRandom].setText("O");
      box[(int)xRowRandom][(int)yColumnRandom].setEnabled(false);
      gameModel.grid[(int)xRowRandom][(int)yColumnRandom] = "O";
    }
    else if (gameModel.winner == true)
    {
      for(int x = 0; x < 3; x++)
      {
        for(int y = 0; y < 3; y++) 
        {
          box[x][y].setEnabled(false);
        }
      }
    }
  }
  
  //Creates a method where it updates the view and starts the next round
  public void updateNextRound(int x, int y) 
  {
    box[x][y].setText("");
    box[x][y].setEnabled(true);
    gameModel.grid[x][y] = "";
    gameModel.moveCounter = 0;
    gameModel.winner = false;
    nextRoundButton.setEnabled(false);
  }
  
  //Creates a method where it updates the view and resets the game
  public void updateResetGame(int x, int y) 
  {
    box[x][y].setText("");
    box[x][y].setEnabled(true);
    gameModel.grid[x][y] = "";
    gameModel.moveCounter = 0;
    win = 0;
    winCounter.setText("Current wins: " + win);
    loss = 0;
    lossCounter.setText("Current losses: " + loss);
    gameModel.winner = false;
    nextRoundButton.setEnabled(false);
  }
}//end of class