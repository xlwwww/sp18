package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    double[] count;
    int T;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <=0){
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        count = new double[T];
        for(int i = 0;i < T;i++){
            Percolation per = pf.make(N);
            while(!per.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if(!per.isOpen(row,col)) {
                    per.open(row, col);
                    count[i] +=1;
                }
            }
            count[i] = (double) count[i]/(N*N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(count);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(count);
    }

    // low endpoint of 95% confidence interval
    private double computehelp(){
        double var = Math.sqrt(stddev());
        return 1.96*var/Math.sqrt(T);
    }
    public double confidenceLow() {
        return mean()-computehelp();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean()+computehelp();
    }
}

