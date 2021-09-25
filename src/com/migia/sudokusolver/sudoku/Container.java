package com.migia.sudokusolver.sudoku;

import com.migia.sudokusolver.Main;

import java.util.Arrays;
import java.util.List;

public abstract class Container {

    private Cell[] elements;
    private int pos;
    private int[] data;


    public Container(){

    }

    public void extractNo(){
        var pos =0;
        for(var cell : elements){
            data[pos++] = cell.getData();
        }
    }

    public abstract void check();

    public  List<Integer> arrayDifference(int[] param){
        if(Main.variables.removeAll(Arrays.asList(param)))
            return Main.variables;
        return Main.variables;
    }
    public abstract boolean canStay(int value);
    abstract void unfilledCells();
    public abstract void setReference();
    public abstract List<Integer> remainingNo();
    public abstract void show();
    public abstract boolean isFull();
    public abstract boolean canStayInOtherCells(int value,Cell frm);
    public abstract void notifyDataChanged();

    @Override
    public String toString() {
        return "Row " + pos;
    }
}
