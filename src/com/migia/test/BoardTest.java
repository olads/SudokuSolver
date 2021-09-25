package com.migia.test;

import com.migia.sudokusolver.Main;
import com.migia.sudokusolver.sudoku.Board;

public class BoardTest {
    public static void main(String[] args){
        Main.populateDemo();
        Board board = new Board(Main.demoArray);
        var isFull = board.isFull();
        System.out.println("The board is full " + isFull);
    }
}
