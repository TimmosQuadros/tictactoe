package com.example.tictactoe;

import java.util.ArrayList;
import java.util.Stack;

public class TreeBuilder {

    private Boolean[][] board;
    private boolean turn;
    private BoardTree boardTree;
    private Stack<BoardNode> queue;
    boolean topCenter = false, topRight = false, topLeft = false, centerCenter = false, centerRight = false, centerLeft = false, bottomCenter = false, bottomRight = false, bottomLeft = false;

    /**
     * I use two-dimensional bit array to represent the players, x is true and o is false.
     * @param board
     * @param turn
     */
    public TreeBuilder(Boolean[][] board, boolean turn) {
        this.board = board;
        this.turn = turn;
        queue = new Stack<>();
    }

    public BoardTree buildTree(){
        BoardNode root = new BoardNode(board);
        root.setTurn(this.turn);
        root.setRoot(true);
        boardTree = new BoardTree(root);
        queue.push(root);

        while(queue.isEmpty() == false){
            BoardNode boardNode = queue.pop();
            //System.out.println(queue.size());
            boolean turn = boardNode.getTurn();
            ArrayList<Point> points = getEmptyPoints(boardNode.getBoard());
            Boolean[][] tmpBoard = boardNode.getBoardClone();
            for(Point p : points){
                tmpBoard[p.getI()][p.getJ()] = !turn;

                BoardNode child = new BoardNode();
                child.setBoard(copy(tmpBoard));
                child.setParent(boardNode);
                child.setTurn(!turn);
                child.setLevel(boardNode.getLevel()+1);

                //ArrayList<BoardNode> Children
                if(boardNode.isRoot()){
                    boardNode.addChild(child);
                }

                //Check if board is a win or a loss
                if(!turn){
                    if(crossWon(tmpBoard)){
                        addCrossWin(child);
                    }else{
                        queue.push(child);
                    }
                }else{
                    if(circleWon(tmpBoard)){
                        addCircleWin(child);
                    }else{
                        queue.push(child);
                    }
                }

                tmpBoard[p.getI()][p.getJ()] = null;
            }
        }
        return boardTree;
    }

    public Boolean[][] copy(Boolean[][] original){
        Boolean[][] copy = new Boolean[3][3];
        for(int i = 0; i < original.length; i++) {
            for (int j = 0; j < original.length; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    private void addCircleWin(BoardNode child) {
        int level = child.getLevel();
        while(!child.getParent().isRoot()){
            child = child.getParent();
        }
        if(!this.turn){
            //child.addLoss();
            if(child.getScores().containsKey(level)){
                child.getScores().get(level).addLoss();
            }else{
                child.getScores().put(level,new Score(0,1));
            }
        }else{
            //child.addWin();
            if(child.getScores().containsKey(level)){
                child.getScores().get(level).addWin();
            }else{
                child.getScores().put(level,new Score(1,0));
            }
        }
    }

    private void addCrossWin(BoardNode child) {
        int level = child.getLevel();
        while(!child.getParent().isRoot()){
            child = child.getParent();
        }
        if(!this.turn){
            //child.addWin();
            if(child.getScores().containsKey(level)){
                child.getScores().get(level).addWin();
            }else{
                child.getScores().put(level,new Score(1,0));
            }
        }else{
            //child.addLoss();
            if(child.getScores().containsKey(level)){
                child.getScores().get(level).addLoss();
            }else{
                child.getScores().put(level,new Score(0,1));
            }
        }
    }

    /**
     * Returns all the empty points on the board, the board is
     * a two-dimensional array
     * @param board
     * @return
     */
    public ArrayList<Point> getEmptyPoints(Boolean[][] board) {
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j]==null){
                    points.add(new Point(i,j));
                }
            }
        }
        return points;
    }

    public boolean crossWon(Boolean[][] ticTacToeBoard){
        topLeft = ticTacToeBoard[0][0] == null ? false : (ticTacToeBoard[0][0] ? true : false);
        topCenter = ticTacToeBoard[0][1] == null ? false : (ticTacToeBoard[0][1] ? true : false);
        topRight = ticTacToeBoard[0][2] == null ? false : (ticTacToeBoard[0][2] ? true : false);
        centerLeft = ticTacToeBoard[1][0] == null ? false : (ticTacToeBoard[1][0] ? true : false);
        centerCenter = ticTacToeBoard[1][1] == null ? false : (ticTacToeBoard[1][1] ? true : false);
        centerRight = ticTacToeBoard[1][2] == null ? false : (ticTacToeBoard[1][2] ? true : false);
        bottomLeft = ticTacToeBoard[2][0] == null ? false : (ticTacToeBoard[2][0] ? true : false);
        bottomCenter = ticTacToeBoard[2][1] == null ? false : (ticTacToeBoard[2][1] ? true : false);
        bottomRight = ticTacToeBoard[2][2] == null ? false : (ticTacToeBoard[2][2] ? true : false);
        boolean crossWon = checkThreeInARow();
        resetCheckValues();
        return crossWon;
    }

    public boolean circleWon(Boolean[][] ticTacToeBoard){
        topLeft = ticTacToeBoard[0][0] == null ? false : (ticTacToeBoard[0][0] ? false : true);
        topCenter = ticTacToeBoard[0][1] == null ? false : (ticTacToeBoard[0][1] ? false : true);
        topRight = ticTacToeBoard[0][2] == null ? false : (ticTacToeBoard[0][2] ? false : true);
        centerLeft = ticTacToeBoard[1][0] == null ? false : (ticTacToeBoard[1][0] ? false : true);
        centerCenter = ticTacToeBoard[1][1] == null ? false : (ticTacToeBoard[1][1] ? false : true);
        centerRight = ticTacToeBoard[1][2] == null ? false : (ticTacToeBoard[1][2] ? false : true);
        bottomLeft = ticTacToeBoard[2][0] == null ? false : (ticTacToeBoard[2][0] ? false : true);
        bottomCenter = ticTacToeBoard[2][1] == null ? false : (ticTacToeBoard[2][1] ? false : true);
        bottomRight = ticTacToeBoard[2][2] == null ? false : (ticTacToeBoard[2][2] ? false : true);
        boolean circleWon = checkThreeInARow();
        resetCheckValues();
        return circleWon;
    }

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

}

