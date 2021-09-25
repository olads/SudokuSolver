package com.migia.sudokusolver;

import com.migia.sudokusolver.sudoku.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Main{
    public static Container current ;
    public static Cell[][] demoArray = new Cell[9][9];
    public static final int[][] demoSudoku = {{0,8,0,4,0,9,6,5,3},{6,4,2,8,0,0,0,7,0},{0,0,0,0,0,0,8,0,0},
            {0,0,7,0,0,5,0,4,2},{0,0,0,7,0,1,0,0,0},{8,5,0,6,0,0,1,0,0},
            {0,0,6,0,0,0,0,0,0},{0,1,0,0,0,4,7,3,6},{2,7,3,5,0,8,0,1,0}};
          public static final int[][] midsudoku = {{0,0,1,9,4,0,0,0,0},{9,3,0,5,0,0,6,7,4},{0,4,7,0,0,2,0,0,0},
                  {3,8,6,0,2,4,0,5,9},{7,2,0,8,9,0,0,6,0},{1,0,0,0,6,3,4,0,0},
                  {0,0,8,2,5,0,3,0,7},{5,6,3,0,0,9,2,0,0},{0,0,0,3,1,8,0,4,0}};
          public static final int[][] hard = {{0,0,0,0,0,6,0,0,5},{0,7,9,0,2,0,1,0,0},{2,0,0,0,9,0,8,7,0},
                  {0,3,0,0,0,5,0,0,9},{0,0,0,9,0,2,0,0,0},{8,0,0,3,0,0,0,2,0},
                  {0,2,5,0,3,0,0,0,4},{0,0,8,0,4,0,9,5,0},{4,0,0,6,0,0,0,0,0}};
          public static final int[][] med={{1,0,0,0,4,0,0,0,0},{0,0,6,0,8,5,0,0,4},{5,3,0,2,0,0,1,8,9},
                  {2,1,8,7,0,4,6,0,0},{6,5,3,9,1,8,0,0,7},{4,0,0,6,0,0,8,0,5},{0,0,0,0,0,0,0,3,0},
                  {3,4,1,5,0,6,7,2,0},{0,0,0,4,7,3,0,5,1}};
    public static final List<Integer> variables = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9});

    public static void main(String[] args){
        System.out.println("Hello World");
        populateDemo();
        Board board = new Board(convertToCellArray(med));
        show("Initial board");
        board.show();
        show("Starting solving the sudoku");
        board.start();

        board.show();
        show("End of process")
      /*  showCellInfo(board);
        var rows = board.getRows();
        var col = board.getColumns();
        var box = board.getBoxes();
*/
  /*     show("Rows .......");
        for (Row row: rows) {
            row.show();
        }
        show(".....Rows");

        show("Box ......");
        for(Box boxx: box){
            boxx.show();
        }
        show("...... Box");

        show("Column ...........");
        for(var coll : col){
            coll.show();
        }

        show("......... Column")*/;

    }

    public static Cell[][] convertToCellArray(int[][] arr){
        Cell[][] res = new Cell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                res[i][j] = new Cell(arr[i][j]);
            }
        }
        return res;
    }

    public static String showCandidates(Set<Integer> set){
        var iterator = set.iterator();
        var res = new StringBuilder();
        while(iterator.hasNext()){
            res.append(",");
            res.append(iterator.next());
        }
        return res.toString();
    }
    public static void showOneDimensionalArray(Cell[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" | " + arr[i]);

        }
    }

    public static void showTowDimensionalArray(Cell[][] arr){
        for (int i = 0; i < arr.length; i++) {
         showOneDimensionalArray(arr[i]);

         System.out.println();
        }
    }

    public static void populateDemo(){
        var pos = 1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                demoArray[i][j] = new Cell(pos++);
            }
        }
        demoArray[3][5] = new Cell(0);
    }

    public static void showBoardInfo(Board board){
        for(var cells: board.getBoard()){
            for(var cell:cells){
                System.out.println("The cell with data " + cell +
                        " Row " + cell.getRow() +
                        " Column " + cell.getCol() +
                        " Box " + cell.getBox()
                );
            }
        }
    }




    public static void show(String args){
        System.out.println(args);
    }
}
