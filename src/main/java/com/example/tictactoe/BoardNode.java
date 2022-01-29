package com.example.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardNode {

    private Boolean[][] board;
    private BoardNode parent;
    private int wins;
    private int losses;
    private boolean isRoot;
    private boolean turn;
    private ArrayList<BoardNode> children;
    private int level;
    private HashMap <Integer,Score> scores;

    public BoardNode(){
        children = new ArrayList<>();
        scores = new HashMap<>();
    }

    public BoardNode(Boolean[][] board) {
        this.board = board;
        children = new ArrayList<>();
        scores = new HashMap<>();
    }

    public BoardNode getParent() {
        return parent;
    }

    public void setParent(BoardNode parent) {
        this.parent = parent;
    }

    public Boolean[][] getBoard() {
        return board;
    }

    public Boolean[][] getBoardClone(){
        Boolean[][] newBoard = new Boolean[3][3];
        for(int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    public void setBoard(Boolean[][] board) {
        this.board = board;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public void addWin(){
        this.wins++;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public void addLoss(){
        this.losses++;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public boolean isTurn() {
        return turn;
    }

    public void addChild(BoardNode child){
        this.children.add(child);
    }

    public ArrayList<BoardNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<BoardNode> children) {
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void increaseLevel(){
        this.level++;
    }

    public HashMap<Integer, Score> getScores() {
        return scores;
    }

    public void setScores(HashMap<Integer, Score> scores) {
        this.scores = scores;
    }

}
