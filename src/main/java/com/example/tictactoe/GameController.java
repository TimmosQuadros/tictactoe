package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class GameController {

    Board ticTacToeBoard = new Board();
    boolean turnPlayerOne = true;
    Alert a = new Alert(Alert.AlertType.INFORMATION);
    private int turnCounter = 0;

    public void draw(String eventSource, boolean turnPlayerOne){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/"+(turnPlayerOne ? "X.png":"O.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image image = new Image(inputStream);

        Button buttonClicked = getButtonClicked(eventSource);
        buttonClicked.setText("");
        buttonClicked.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,false))));

        ticTacToeBoard.printBoard();
    }

    private Button getButtonClicked(String eventSource){
        if(eventSource.contains("id=btnTopCenter")){
            return btnTopCenter;
        }else if(eventSource.contains("id=btnTopLeft")){
            return btnTopLeft;
        }else if(eventSource.contains("id=btnTopRight")){
            return btnTopRight;
        }else if(eventSource.contains("id=btnCenterCenter")){
            return btnCenterCenter;
        }else if(eventSource.contains("id=btnCenterLeft")){
            return btnCenterLeft;
        }else if(eventSource.contains("id=btnCenterRight")){
            return btnCenterRight;
        }else if(eventSource.contains("id=btnBottomCenter")){
            return btnBottomCenter;
        }else if(eventSource.contains("id=btnBottomLeft")){
            return btnBottomLeft;
        }else{
            return btnBottomRight;
        }
    }

    public void setPlayerTurn(){
        turnCounter++;
        if(turnCounter>9){
            a.setContentText("Draw, try again!!");
            a.show();
            resetBoard();
            return;
        }
        if(turnPlayerOne){
            turnPlayerOne=false;
        }else{
            turnPlayerOne=true;
        }
    }

    @FXML
    private GridPane board;

    @FXML
    private Button btnBottomCenter;

    @FXML
    private Button btnBottomLeft;

    @FXML
    private Button btnBottomRight;

    @FXML
    private Button btnCenterCenter;

    @FXML
    private Button btnCenterLeft;

    @FXML
    private Button btnCenterRight;

    @FXML
    private Button btnTopCenter;

    @FXML
    private Button btnTopLeft;

    @FXML
    private Button btnTopRight;

    boolean topCenter = false, topRight = false, topLeft = false, centerCenter = false, centerRight = false, centerLeft = false, bottomCenter = false, bottomRight = false, bottomLeft = false;

    /**
     * These values are used by both player one and player two after each turn and are set to true if the player
     * has a piece on that spot, therefore they need to be reset after each turn.
     */
    private void resetCheckValues(){
        topCenter = false;
        topRight = false;
        topLeft = false;
        centerCenter = false;
        centerRight = false;
        centerLeft = false;
        bottomCenter = false;
        bottomRight = false;
        bottomLeft = false;
    }

    /**
     * Checks for three in a row
     * @return
     */
    public boolean checkThreeInARow(){
        if(topCenter && topLeft && topRight){
            return true;
        }else if(centerCenter && centerLeft && centerRight){
            return true;
        }else if(bottomCenter && bottomLeft && bottomRight){
            return true;
        }else if(topLeft && centerLeft && bottomLeft){
            return true;
        }else if(topCenter && centerCenter && bottomCenter){
            return true;
        }else if(topRight && centerRight && bottomRight){
            return true;
        }else if(topLeft && centerCenter && bottomRight){
            return true;
        }else if(topRight && centerCenter && bottomLeft){
            return true;
        }
        return false;
    }

    /**
     * Player one is always cross, the value of the board for player one is 1 or true
     * @return
     */
    public boolean playerOneWon(){
        topLeft = ticTacToeBoard.getBoard()[0][0] == null ? false : (ticTacToeBoard.getBoard()[0][0] ? true : false);
        topCenter = ticTacToeBoard.getBoard()[0][1] == null ? false : (ticTacToeBoard.getBoard()[0][1] ? true : false);
        topRight = ticTacToeBoard.getBoard()[0][2] == null ? false : (ticTacToeBoard.getBoard()[0][2] ? true : false);
        centerLeft = ticTacToeBoard.getBoard()[1][0] == null ? false : (ticTacToeBoard.getBoard()[1][0] ? true : false);
        centerCenter = ticTacToeBoard.getBoard()[1][1] == null ? false : (ticTacToeBoard.getBoard()[1][1] ? true : false);
        centerRight = ticTacToeBoard.getBoard()[1][2] == null ? false : (ticTacToeBoard.getBoard()[1][2] ? true : false);
        bottomLeft = ticTacToeBoard.getBoard()[2][0] == null ? false : (ticTacToeBoard.getBoard()[2][0] ? true : false);
        bottomCenter = ticTacToeBoard.getBoard()[2][1] == null ? false : (ticTacToeBoard.getBoard()[2][1] ? true : false);
        bottomRight = ticTacToeBoard.getBoard()[2][2] == null ? false : (ticTacToeBoard.getBoard()[2][2] ? true : false);
        boolean playerOneWon = checkThreeInARow();
        if(playerOneWon){
            a.setContentText("X has won the game!!!");
            System.out.println("X has won the game!!!");
        }
        resetCheckValues();
        return playerOneWon;
    }

    /**
     * Player two is always circle, the value of the board for player two is 0 or false
     * @return
     */
    public boolean playerTwoWon(){
        topLeft = ticTacToeBoard.getBoard()[0][0] == null ? false : (ticTacToeBoard.getBoard()[0][0] ? false : true);
        topCenter = ticTacToeBoard.getBoard()[0][1] == null ? false : (ticTacToeBoard.getBoard()[0][1] ? false : true);
        topRight = ticTacToeBoard.getBoard()[0][2] == null ? false : (ticTacToeBoard.getBoard()[0][2] ? false : true);
        centerLeft = ticTacToeBoard.getBoard()[1][0] == null ? false : (ticTacToeBoard.getBoard()[1][0] ? false : true);
        centerCenter = ticTacToeBoard.getBoard()[1][1] == null ? false : (ticTacToeBoard.getBoard()[1][1] ? false : true);
        centerRight = ticTacToeBoard.getBoard()[1][2] == null ? false : (ticTacToeBoard.getBoard()[1][2] ? false : true);
        bottomLeft = ticTacToeBoard.getBoard()[2][0] == null ? false : (ticTacToeBoard.getBoard()[2][0] ? false : true);
        bottomCenter = ticTacToeBoard.getBoard()[2][1] == null ? false : (ticTacToeBoard.getBoard()[2][1] ? false : true);
        bottomRight = ticTacToeBoard.getBoard()[2][2] == null ? false : (ticTacToeBoard.getBoard()[2][2] ? false : true);
        boolean playerTwoWon = checkThreeInARow();
        if(playerTwoWon){
            a.setContentText("O has won the game!!!");
            System.out.println("O has won the game!!!");
        }
        resetCheckValues();
        return playerTwoWon;
    }

    public void resetBoard(){
        turnCounter = 0;
        ticTacToeBoard.reset();
        btnBottomRight.setBackground(Background.EMPTY);
        btnCenterRight.setBackground(Background.EMPTY);
        btnTopRight.setBackground(Background.EMPTY);

        btnBottomLeft.setBackground(Background.EMPTY);
        btnCenterLeft.setBackground(Background.EMPTY);
        btnTopLeft.setBackground(Background.EMPTY);

        btnBottomCenter.setBackground(Background.EMPTY);
        btnCenterCenter.setBackground(Background.EMPTY);
        btnTopCenter.setBackground(Background.EMPTY);
    }

    @FXML
    void btnBottomCenterClicked(ActionEvent event) {
        ticTacToeBoard.set(2,1,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnBottomLeftClicked(ActionEvent event) {
        ticTacToeBoard.set(2,0,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnBottomRightClicked(ActionEvent event) {
        ticTacToeBoard.set(2,2,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnCenterClicked(ActionEvent event) {
        ticTacToeBoard.set(1,1,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnCenterLeftClicked(ActionEvent event) {
        ticTacToeBoard.set(1,0,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnCenterRightClicked(ActionEvent event) {
        ticTacToeBoard.set(1,2,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnTopCenterClicked(ActionEvent event) {
        ticTacToeBoard.set(0,1,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnTopLeftClicked(ActionEvent event) {
        ticTacToeBoard.set(0,0,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void btnTopRightClicked(ActionEvent event) {
        ticTacToeBoard.set(0,2,turnPlayerOne);
        draw(event.getSource().toString(),turnPlayerOne);
        if((turnPlayerOne ? playerOneWon() : playerTwoWon())){
            a.show();
            resetBoard();
        };
        setPlayerTurn();
    }

    @FXML
    void newGame(ActionEvent event) {
        //TODO implement new game startup stuff
    }

}
