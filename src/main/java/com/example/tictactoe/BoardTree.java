package com.example.tictactoe;

public class BoardTree {
    private BoardNode root;

    public BoardTree(BoardNode root){
        this.root = root;
    }

    public BoardNode getRoot() {
        return root;
    }

    public void setRoot(BoardNode root) {
        this.root = root;
    }
}
