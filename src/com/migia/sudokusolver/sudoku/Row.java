package com.migia.sudokusolver.sudoku;

import com.migia.sudokusolver.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Row extends Container {
    
    private Cell[] elements;
    private int pos;
    public Row(Cell[] row, int pos) {
        elements = row;
        this.pos = pos;
       setReference();
    }

    //checks if a number, value can stay in this row
    public boolean canStay(int value){
     //  System.out.println(" Can stay method called with data "+ value);
        for(var cell: elements){
            if(!cell.isEmpty()) {
                if (cell.getData() == value) {
                   // System.out.println(cell.getLocation() + " value " + value + " cannot stay at that location");
                    return false;
                }
            }
        }
        return true;
    }

    //returns the position of the row on the board
        public int getPos(){
        return pos;
    }

    //Checks if the row is full
    @Override
    public boolean isFull(){
        for(var cell: elements){
            if(cell.getCanBeFilled())
            return false;
        }
        return true;
    }

    public void check(){
        if(Main.current == this)
            return;
        for(var cell: elements){
            cell.populateCandidates();
            Main.current = this;
        }

    }
    public void unfilledCells(){
        var count = 0;
        List<Cell> celll = new ArrayList<>();
        for(var cell: elements){
                if(cell.isEmpty()){
                    count++;
                    celll.add(cell);
                }


        }
        //if there is only one space left to be filled
        if(count == 1){
            var cell = celll.get(0);
            var candidates = cell.getCandidates();
            Main.showCandidates(candidates);
            var iter = candidates.iterator();
            while(iter.hasNext()){
                var next = iter.next();
                var used = false;
                for(var ce : elements){
                        if(ce == cell)
                            continue;
                    if(cell.getData() == next){
                        used = true;
                    }
                }
                if(!used){
                    cell.setData(next);
                }
            }
        }

        //if there is only two spaces to be filled
        else if(count == 2) {
            var remaining = remainingNo();
            if(remaining.size() == 2) {
                    var cell1 = celll.get(0);
                    var cell2 = celll.get(1);
                    cell1.populateCandidates(true);
                    cell2.populateCandidates(true);
                    checkDouble(cell1,cell2);
                    checkDouble(cell2,cell1);

                }
            }

    }

    private void checkDouble(Cell a, Cell b){
        var candids = a.getCandidates();
        var iter = candids.iterator();
        while(iter.hasNext()){
            var next = iter.next();
            if(!b.getCandidates().contains(next)){
                a.setData(next);
                return;
            }
        }
    }
    @Override
    public List<Integer> remainingNo() {
        var res = new int[2];
        List<Integer> ele = new ArrayList<>();
        for(var cell: elements){
           ele.add(cell.getData());
        }
        var newList = new ArrayList<>(Main.variables);
        newList.removeAll(ele);
        return newList;
    }

    @Override
    public void notifyDataChanged() {
        for(var cell : elements){
            if(cell.getCanBeFilled()){
                cell.populateCandidates(true);
            }
        }
    }

    @Override
    public boolean canStayInOtherCells(int value,Cell frm){

        for(var cell : elements){

            if(cell == frm)
                continue;
            else if(cell.canStay(value)){
                //System.out.println("The value " + value + " is already there at row" + getPos());
                return false;
            }
        }
       // Main.show("This value " + value + " can stay in row" + getPos());
        frm.setData(value);
        return true;
    }


    public void setReference(){
        for( var cell: elements){
            if(cell != null)
            cell.setRow(this);
        }
    }
    
    public void show(){
        Main.showOneDimensionalArray(elements);
        Main.show("");
    }

    @Override
    public String toString() {
        return "Row " + pos;
    }
}
