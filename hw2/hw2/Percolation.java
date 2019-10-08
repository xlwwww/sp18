package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Map;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    int N;
    int[][] grid;
    int num = 0;
    WeightedQuickUnionUF conn;
    WeightedQuickUnionUF extraconn;
    int top;
    int bottom;
    public Percolation(int N) {
        this.N = N;
        if(N <=0){
            throw new IllegalArgumentException();
        }
        conn = new WeightedQuickUnionUF(N*N+2);
        extraconn = new WeightedQuickUnionUF(N*N+1);//avoid backwash
        top = N*N;
        bottom = N*N+1;
        this.grid = new int[N][N];
        for(int i =0; i < N; i++){
            conn.union(i,top);
            extraconn.union(i,top);
            for(int j = 0; j < N; j++){
                grid[i][j] = -1;
                if(i==N-1){
                    conn.union(xyTo1D(i,j),bottom);
                }
            }
        }
    }
    private boolean vadidate(int row, int col){
        if(row>=N || row<0 || col>=N || col<0){
            return false;
        }
        return true;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if(!vadidate(row,col)){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(!isOpen(row,col)){
            grid[row][col] = 1;//open
            num++;
            int pos = xyTo1D(row,col);
            //examine 4 positions up down left right
            if(vadidate(row-1,col) && isOpen(row-1,col)){
                conn.union(xyTo1D(row-1,col),pos);
                extraconn.union(xyTo1D(row-1,col),pos);
            }
            if(vadidate(row+1,col) && isOpen(row+1,col)){
                conn.union(xyTo1D(row+1,col),pos);
                extraconn.union(xyTo1D(row+1,col),pos);
            }
            if(vadidate(row,col-1) && isOpen(row,col-1)){
                conn.union(xyTo1D(row,col-1),pos);
                extraconn.union(xyTo1D(row,col-1),pos);
            }
            if(vadidate(row,col+1) && isOpen(row,col+1)){
                conn.union(xyTo1D(row,col+1),pos);
                extraconn.union(xyTo1D(row,col+1),pos);
            }
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(!vadidate(row,col)){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(grid[row][col]==-1){
            return false;
        }
        return true;
    }
    private int xyTo1D(int r, int c){
        return r*N+c;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if(!vadidate(row,col)){
            throw new java.lang.IndexOutOfBoundsException();
        }
        if(isOpen(row,col) && extraconn.connected(xyTo1D(row,col),top)){
            return true;
        }
        return false;
    }
    public int numberOfOpenSites() {
        return num;
    }
    public boolean percolates()     {
        return conn.connected(top,bottom);
    }    // does the system percolate?
    public static void main(String[] args) {

    }  // use for unit testing (not required)
}
