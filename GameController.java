//GameController
//This is the class containing actionPerformed(ActionEvent e) - it is the controller for the buttons on the 3 x 3 grid
//Created By: Affan Rana and Ethan Cai
//Last Modified: January 23, 2022

//importing classes & packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameController implements ActionListener
{
  private GameModel gameModel;
  private static GameView gameView;

  //Creates a new constructor
  public GameController(GameModel gameModel, GameView gameView)
  {
    this.gameModel = gameModel;
    this.gameView = gameView;
    addActionListeners();
  }

  //Creates a method where it makes the buttons functional
  private void addActionListeners() 
  {
    for(int x = 0; x < 3; x++) 
    {
      for(int y = 0; y < 3; y++) 
      {
        gameView.box[x][y].addActionListener(this);
      }
    }
    gameView.resetButton.addActionListener(this);
    gameView.nextRoundButton.addActionListener(this);
  }

  //method that detects where the player clicks and performs action depending on where the click occured
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == gameView.nextRoundButton)
    {
      gameModel.nextRoundView();
    }
    else if (e.getSource() == gameView.resetButton)
    {
      gameModel.resetGameView();
    }
    else
    {
      gameModel.buttonContinue(e);
    }
  }
}//end of class