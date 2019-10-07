import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class BubbleGrid {
    int[][] grid;
    int rown;
    int coln;
    int answer = 0;
    int[][] flag;
    WeightedQuickUnionUF u;
    public BubbleGrid(int[][] points){
        this.grid = points;
        this.rown = points.length;
        this.coln = points[0].length;
        this.flag = new int[rown][coln];
        u = new WeightedQuickUnionUF(rown*coln);
        init();
    }
    private void init(){
        int pos;
        int cur;
        for (int i =0;i<rown;i++){
            for(int j = 0;j<coln;j++){
                //up
                pos = to1Dpos(i,j);
                cur = grid[i][j];
                if(cur!=1){
                    continue;
                }
                if(i-1>=0 && grid[i-1][j]==cur && !u.connected(pos,to1Dpos(i-1,j))){
                    u.union(pos,to1Dpos(i-1,j));
                }
                //left
                if(j-1>=0 && grid[i][j-1]==cur && !u.connected(pos,to1Dpos(i,j-1))){
                    u.union(pos,to1Dpos(i,j-1));
                }
                //right
                if(j+1<coln && grid[i][j+1]==cur &&!u.connected(pos,to1Dpos(i,j+1))){
                    u.union(pos,to1Dpos(i,j+1));
                }
            }
        }
    }
    private int to1Dpos(int i,int j){
        if(i<0 || j<0){
            return 0;
        }
        return i*coln+j;
    }
    private int helper(int i, int j){
        int pos = to1Dpos(i,j);
        flag[i][j] = -1;
        //up
        if(i-1>=0 && flag[i-1][j]!=-1 && u.connected(pos,to1Dpos(i-1,j))){
            answer++;
            flag[i-1][j] = -1;
            helper(i-1,j);
        }
        if(j-1>=0 && flag[i][j-1]!=-1 &&u.connected(pos,to1Dpos(i,j-1))){
            answer++;
            flag[i][j-1] = -1;
            helper(i,j-1);
        }
        if(j+1<coln && flag[i][j+1]!=-1 && u.connected(pos,to1Dpos(i,j+1))){
            answer++;
            flag[i][j+1] = -1;
            helper(i,j+1);
        }
        return answer;
    }
    public int[] popBubbles(int[][] darts){
        int[] ans = new int[darts.length];
        int row;
        int con;
        for(int i =0;i<darts.length;i++){
            row = darts[i][0];
            con = darts[i][1];
            if(grid[row][con]==0){
                ans[i] = 0;
            }else{
                //todo 找到所有与此元素在同一集合中的元素
                ans[i] = helper(row,con);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1,1,0},{1, 0, 0},{1,1,0},{1, 1, 1}};
        int[][] darts = new int[][]{{2,2},{2,0}};
        BubbleGrid b = new BubbleGrid(a);
        int[] ans = new int[darts.length];
        ans = b.popBubbles(darts);
    }
}
