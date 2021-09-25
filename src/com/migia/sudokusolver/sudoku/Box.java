package com.migia.sudokusolver.sudoku;


import com.migia.sudokusolver.Main;

import java.util.ArrayList;
import java.util.List;

public class Box extends Container{
    private Cell[][] elements;
    private int[] pos;

    public Box(Cell[][] row,int[] pos) {
        super();
        elements = row;
        this.pos = pos;
        setReference();
    }


    @Override
    public boolean isFull(){
        for(var cells: elements){
        for(var cell: cells){

            if(cell.getCanBeFilled())
            return false;
        }
    }
        return true;
    }
    @Override
    public void setReference() {
        for(var cells: elements){

            for(var cell: cells){
                if(cell == null)
                    System.out.println(cell);
                cell.setBox(this);
            }
        }
    }


@Override
    public void show(){
        Main.showTowDimensionalArray(elements);
        Main.show("");
    }

    @Override
    public boolean canStayInOtherCells(int value,Cell frm) {
        for(var cells: elements){
            for(var cell: cells){
                if(cell == frm)
                    continue;
                if(cell.canStay(value)){

                    //System.out.println("The value " + value + " is already there at box in"+ cell.getRow().getPos() + " " + cell.getRow().getPos());
                    return false;
                }
            }
        }
        //Main.show("This value " + value + " can stay in box " + pos[0] + "," +  pos[1] );
        frm.setData(value);
        return true;
    }

    @Override
    public void check() {

    }

public void unfilledCells(){
        var count = 0;
    List<Cell> celll = new ArrayList<>();
       for(var cells: elements){
           for(var cell: cells){
               if(cell.isEmpty()){
                   count++;
                   celll.add(cell);
               }

           }
       }
       if(count == 1){
           var cell = celll.get(0);
           var candidates = cell.getCandidates();
           //Main.showCandidates(candidates);
           var iter = candidates.iterator();
           while(iter.hasNext()){
               var next = iter.next();
               var used = false;
               for(var cel : elements){
                   for(var ce: cel){
                   if(ce == cell)
                       continue;
                   }
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
        for(var cells: elements) {
            for (var cell : cells) {
                ele.add(cell.getData());
            }
        }
        var newList = new ArrayList<>(Main.variables);
        newList.removeAll(ele);
        return newList;
    }
    @Override
    public void notifyDataChanged() {

        for (var cells : elements) {
            for (var cell : cells) {
                if (cell.getCanBeFilled()) {
                    cell.populateCandidates(true);
                }
            }
        }
    }
    @Override
    public boolean canStay(int value) {
        //show();
        for(var cells : elements){
            for(var cell : cells){
                if(cell.getData() == value)
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Box at " + pos[0] + " " + pos[1];
    }
}
