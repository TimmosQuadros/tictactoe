package com.example.tictactoe;

public class Score {
    private int wins;
    private int losses;

    public Score(){

    }

    public Score(int wins, int losses) {
        this.wins = wins;
        this.losses = losses;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void addWin(){
        this.wins++;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void addLoss(){
        this.losses++;
    }
}
