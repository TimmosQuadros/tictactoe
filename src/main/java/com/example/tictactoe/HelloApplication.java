package com.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setTitle("TicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();
        System.out.println("hello");
        Boolean[][] board = new Boolean[3][3];
        board[0][0]=true;
        board[1][1]=false;
        board[0][1]=true;
        printBoard(board);
        TreeBuilder treeBuilder = new TreeBuilder(board, true);
        BoardTree boardTree = treeBuilder.buildTree();

        //BoardNode bestMove = boardTree.getRoot().getChildren().get(0);
        for(BoardNode boardNode : boardTree.getRoot().getChildren()){
            HashMap<Integer, Score> scores = boardNode.getScores();
            printBoard(boardNode.getBoard());
            for(Integer key: scores.keySet().stream().toList()){
                System.out.println("At level: "+ key + " there are " + scores.get(key).getWins() + " wins and "
                + scores.get(key).getLosses() + " losses");
            }
           /*bestMove = calculateBestMove(bestMove,boardNode);
            System.out.println("wins: " + boardNode.getWins() + " losses: " + boardNode.getLosses());
            printBoard(boardNode.getBoard());*/
        }

        /*System.out.println("The best move: ");
        printBoard(bestMove.getBoard());*/
    }

    public static BoardNode calculateBestMove(BoardNode currentBestMove, BoardNode candidate){
        if(currentBestMove.equals(candidate)){
            return currentBestMove;
        }
        double current_winner_chance = (currentBestMove.getWins().doubleValue()/(currentBestMove.getWins().doubleValue()+currentBestMove.getLosses().doubleValue()))*100.0;
        //double current_loss_chance = (currentBestMove.getLosses()/(currentBestMove.getWins()+currentBestMove.getLosses()))*100;
        double candidate_winner_chance = (candidate.getWins().doubleValue()/(candidate.getWins().doubleValue()+candidate.getLosses().doubleValue()))*100.0;
        //double candidate_loss_chance = (candidate.getLosses()/(candidate.getWins()+candidate.getLosses()))*100;

        if(current_winner_chance>candidate_winner_chance){
            return currentBestMove;
        }else{
            return candidate;
        }
    }

    public static void printBoard(Boolean[][] board){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j]==null){
                    System.out.print("_ ");
                }else{
                    System.out.print((board[i][j]==true ? "X ":"O "));
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}