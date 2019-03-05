

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Field{
    private int width;
    private int height;
    private Cell[][] field;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[height][width];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public Cell place(int row, int col, Cell o) {
        Cell ret = field[row][col];
        field[row][col] = o;
        return ret;
    }

    public Cell get(int row, int col) {
        return field[row][col];
    }

    public Cell[] getNeighbour(int row, int col) {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for ( int i=-1; i<2; i++ ) {
            for ( int j=-1; j<2; j++ ) {
                int r = row+i;
                int c = col+j;
                if ( r >-1 && r<height && c>-1 && c<width && !(r== row && c == col) ) {
                    list.add(field[r][c]);
                }
            }
        }
        return list.toArray(new Cell[list.size()]);
    }

    public int numOfLives()
    {
        int num = 0;
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                Cell cell = get(i, j);
                if(cell.isAlive())
                    num++;
            }
        }
        return num;
    }

    public void changeCell(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                Cell cell = get(i , j);
                cell.nextToNow();
            }
        }
    }

}


class Cell{
    private boolean alive = false;
    private boolean nextValue = alive;

    public void die() { alive = false; }
    public void reborn() { alive = true; }
    public boolean isAlive() { return alive; }
    public void nextdie(){nextValue = false;}
    public void nextReborn(){nextValue = true;}
    public void nextToNow(){alive = nextValue;}
}

public class CellMachine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int weight = sc.nextInt();
        int height = sc.nextInt();
        Field field = new Field(weight,height);
        for ( int row = 0; row<field.getHeight(); row++ ) {
            for ( int col = 0; col<field.getWidth(); col++ ) {
                field.place(row, col, new Cell());
            }
        }
        int a = sc.nextInt();
        int b = sc.nextInt();
        while (a != -1 && b != -1) {
            Cell cell = field.get(a, b);
            cell.reborn();
            a = sc.nextInt();
            b = sc.nextInt();
        }
        int steps = sc.nextInt();
        for ( int i=0; i<steps; i++ ) {
            for ( int row = 0; row<field.getHeight(); row++ ) {
                for ( int col = 0; col<field.getWidth(); col++ ) {
                    Cell cell = field.get(row, col);
                    Cell[] neighbour = field.getNeighbour(row, col);
                    int numOfLive = 0;
                    for ( Cell c : neighbour ) {
                        if ( c.isAlive() ) {
                            numOfLive++;
                        }
                    }
                    if ( cell.isAlive() ) {
                        if ( numOfLive <2 || numOfLive >3 ) {
                            cell.nextdie();
                        }else {
                            cell.nextReborn();
                        }
                    } else if ( numOfLive == 3 ) {
                        cell.nextReborn();
                    }
                }
            }
            field.changeCell();
        }
        System.out.println(field.numOfLives());
    }

}

