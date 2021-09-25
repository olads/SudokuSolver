package com.migia.sudokusolver.sudoku;

import com.migia.sudokusolver.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cell {

    private int data;
    private Row row;
    private Column col;
    private Box box;
    private boolean populated = false;
    private boolean canBeFilled = false;

    public Set<Integer> getCandidates() {
        return candidates;
    }


    private Set<Integer> candidates;
public boolean isEmpty(){
    return canBeFilled;
}

    public Cell(int data){
        this.data = data;
        var ss = new HashSet<Integer>();
        if(data == 0){
            canBeFilled = true;

        }
        candidates = new HashSet<>();

    }



    //getters and setters
    public int getData() {
        return data;
    }

    public Row getRow() {
        return row;
    }

    public Column getCol() {
        return col;
    }

    public Box getBox() {
        return box;
    }

    public boolean getCanBeFilled() {
        return canBeFilled;
    }

    public void setData(int data) {
       // System.out.println("tried setting data to " + data);
        if(canBeSetAsData(data)) {
            //Main.show("                     Setting the data of cell " + getLocation() + " to " + data);
            this.data = data;
            canBeFilled = false;
            candidates = new HashSet<>();
            row.notifyDataChanged();
            col.notifyDataChanged();
            box.notifyDataChanged();


        }
    }


    public void setRow(Row row) {
        this.row = row;
    }

    public void setCol(Column col) {
        this.col = col;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public String toString(){
        return (String.valueOf(data));
    }

    public void checkCandidateIsOne() {

        //Main.show("Check candidate has been called " + data);
        if(candidates != null && candidates.size() == 1)
     {
         //Main.show("Cell can still be filled");
           var val = (int)candidates.toArray()[0];
           //Main.show("The size of the candidate called is " + candidates.size());
           setData(val);
       }
    }

public String getLocation(){
       return "row " + row.getPos() + " col " + col.getPos();
}

private boolean canBeSetAsData(int value){
        return (row.canStay(value) && col.canStay(value) && box.canStay(value));
}

public void check() {
        //System.out.println("Checking through the cell at " + getLocation());
        var iterator = candidates.iterator();
    while (iterator.hasNext()) {
        var next = iterator.next();
        //System.out.println("Checkking "+ next );
        row.canStayInOtherCells(next,this);
        col.canStayInOtherCells(next,this);
        box.canStayInOtherCells(next,this);
        row.unfilledCells();
        col.unfilledCells();
        box.unfilledCells();
    }
}
    public void populateCandidates(boolean rePopulate){
        populated = false;
        populateCandidates();
    }
    public void populateCandidates(){
        if (populated) {
            check();
            return;

        }
        populated = true;
        if(!canBeFilled){
        //Main.show("This cell cannot be filled " + data);
        return ;
    }
        if(canBeFilled) {
            //Main.show("Starting populating " + getLocation()) ;
            for (var i : Main.variables) {
              //  Main.show("checking ..." + i);
                if (canStay(i)) {
                    candidates.add(i);

                }
            }
           // Main.show("The length of the candidate after population is " + candidates.size());
            checkCandidateIsOne();
        }
    }

    public boolean canStay(int value) {
        return (row.canStay(value) && box.canStay(value) && col.canStay(value));

    }
}
