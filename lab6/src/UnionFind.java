public class UnionFind {
    private int[] set;
    int n;
    public UnionFind(int n){
        this.set = new int[n];
        this.n = n;
        for(int i =0;i<n;i++){
            set[i] = i;
        }
    }
    public void validate(int v1){
        if(v1>=n||v1<0){
            throw new IndexOutOfBoundsException();
        }
    }
    public int sizeOf(int v1){
        return -set[find(v1)];
    }
    public int parent(int v1){
        return set[v1];
    }
    public boolean connected(int v1, int v2){
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }
    public int find(int v1){
        validate(v1);
        if(v1==parent(v1)){
            return v1;
        }
        int p = set[v1];
        if(p<0){
            return v1;
        }
        while(set[p]>=0){
            p = set[p];
        }
        return p;
    }
    public void union(int v1, int v2){
        validate(v1);
        validate(v2);
        int r1= find(v1);
        int r2 = find(v2);
        if(set[r1]>set[r2]){
            set[r1] = r2;
            if(r2==set[r2]){
                set[r2] = -1;
            }
            set[r2]--;
        } else{
            set[r2] = r1;
            if(r1==set[r1]){
                set[r1] = -1;
            }
            set[r1]--;
        }
    }
    /*
    public static void main(String[] args) {
        UnionFind u = new UnionFind(10);
        u.union(0,1);
        u.union(1,2);
        u.union(2,3);
        u.union(0,4);
        u.union(5,6);
        u.union(6,8);
        u.union(8,9);
        System.out.println(u.connected(2,4));
        System.out.println(u.sizeOf(9));
    }

     */
}
