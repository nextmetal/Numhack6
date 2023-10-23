package Cell;

import Alg.Numhack;

import java.util.ArrayList;

public class Cell {

    // Do not initialize if not necessary
    public ArrayList<Integer> stNum;

    public double rangel;
    public double range;
    public Cell[] CellChain;


    public Cell(Integer storedNum, int rangel, int range){
        // used when inserting the first element, root must always be cellchain

        this.rangel = rangel;
        this.range = range;
        CellChain = new Cell[Numhack.n];
        this.handle(storedNum);
    }

    Cell(Integer storedNum, Cell vexCell, int index){
        // hypoInsert
        this.stNum = new ArrayList<>();
        this.stNum.add(storedNum);

        this.range = vexCell.range/Numhack.n;
        this.rangel = vexCell.rangel + range*index;
    }

    Cell(ArrayList<Integer> storedNum, Cell vexCell, int index){
        // hypoInsert
        this.stNum = storedNum;

        this.range = vexCell.range/Numhack.n;
        this.rangel = vexCell.rangel + range*index;
    }

    public void handle(Integer i){

        if(stNum!=null){
            if(i.equals(stNum.get(0))){
                stNum.add(i);
            }
            else{
                CellChain = new Cell[Numhack.n];
                insertToCellChain(stNum);
                insertToCellChain(i);
                stNum=null;
            }
        }
        else{
            insertToCellChain(i);
        }
    }

    private void insertToCellChain(Integer i){
        int nexti = numhackF(i);

        if(CellChain[nexti]==null) {
            CellChain[nexti] = new Cell(i, this, nexti);
        }
        else{
            CellChain[nexti].handle(i);
        }
    }
    private void insertToCellChain(ArrayList<Integer> i){
        int nexti = numhackF(i.get(0));

        // note CellChain[nexti] is always null before this
        CellChain[nexti] = new Cell(i, this, nexti);
    }

    private int numhackF(Integer i){
        return (int) Math.floor((Numhack.n*(i-rangel))/range);
    }

}
