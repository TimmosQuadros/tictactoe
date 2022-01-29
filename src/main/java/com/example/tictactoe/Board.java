package com.example.tictactoe;

public class Board {

    public Boolean[][] board = new Boolean[3][3];

    public Boolean[][] getBoard() {
        return board;
    }

    public void setBoard(Boolean[][] board) {
        this.board = board;
    }

    public void set(int x_pos,int y_pos,boolean value){
        board[x_pos][y_pos] = value;
    }

    public void printBoard(){
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

    public void reset() {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j]=null;
            }
        }
    }
}
