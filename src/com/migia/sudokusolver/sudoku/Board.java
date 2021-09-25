package com.migia.sudokusolver.sudoku;

import com.migia.sudokusolver.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private List<Row> rows;
    private List<Column> columns;
    private List<Box> boxes;

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    private Cell[][] board;

    public Board(Cell[][] arr){
        this.board = arr;
        rows = new ArrayList<>();
        columns = new ArrayList<>();
        boxes = new ArrayList<>();
        pupolate(arr);
        populateBox(arr);
    }

    public boolean isFull(){
        for (var cells: board) {
            for(var cell : cells){
                if(cell.getData() == 0)
                return false;
            }
        }
        return true;
    }

    private boolean pupolate(Cell[][] arr){
        var rowPos = 1;
        var colPos = 1;
        for(int i = 0; i< 9; i++){
            Cell[] col = new Cell[arr.length];
            for(int j = 0; j < 9; j++){
                col[j] = arr[j][i];

            }
            var column = new Column(col,colPos++);
            columns.add(column);
            //Takes care of the row
            rows.add( new Row(arr[i],rowPos++));
        }
        return true;
    }

    private boolean populateBox(Cell[][] arr){
            var roww = 1;
            var col = 1;
        for (int k = 0; k < 7; k+=3) {
            for (int l = 0; l < 7; l+=3) {


                Cell[][] box = new Cell[3][3];

                // populates a single box
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        box[i][j] = arr[i + k][j + l];
                    }
                }
                var boxx = new Box(box, new int[]{roww,col++});
                boxes.add(boxx);

            }

            roww++;
            col=1;
        }
        return true;
    }

    public void start(){
        while(!isFull()) {
            for (var cells : board) {
                for (var cell : cells) {
                    if(cell.isEmpty()) {
                        //Main.show("Populating cell ..... " + cell.getLocation());

                        cell.populateCandidates();
                    }

                }
            }/*
            showRow();
            show();*/
        }

   }


    public void showRow(){
        Main.show("Showing the rows now...");
        for(var row : getRows()){
            row.show();
        }
        Main.show("Ending the rows now....");
    }
    public List<Row> getRows() {
        return rows;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Box> getBoxes() {
        return boxes;
    }


    public void show(){
        Main.showTowDimensionalArray(board);
    }
}
