//GameStartUp
//This is the class where you run the game - incorporates model which works behind the scenes to do various calculations, as well as view class which is used to display different pieces of information useful to the user and a 3 x 3 grid used for traditional tic tac toe games
//Created By: Affan Rana and Ethan Cai
//Last Modified: January 23, 2022

//Importing classes & packages
import javax.swing.*;
import java.awt.*;

public class GameStartUp 
{
  //Main method
  public static void main(String[] args) 
  {
    GameModel gameModel = new GameModel();
    GameView gameView = new GameView(gameModel);
    GameController gameController = new GameController(gameModel,gameView);

    //Adding and setting up frame
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 400);
    frame.setContentPane(gameView);
    frame.setTitle("Tic Tac Toe");
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }//niam
}//ssalc