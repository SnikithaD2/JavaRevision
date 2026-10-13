public class UFD{
    int max;
    int components;
    int[] leader;
    int[] size;
    public void UFDS(int n){
        leader = new int[n+1];
        size = new int[n+1];
        max = 1;
        components = n;
        for(int i=1; i<=n; i++){
            leader[i] = i;
            size[i] = 1;
        }
    }
    public int find(int x){
        if(x == leader[x]) return x;
        return leader[x] = find(leader[x]);
    }
    public boolean merge(int a, int b){
        int sa = find(a);
        int sb = find(b);
        if(sa == sb) return false;
        if(size[sa] < size[sb]){
            leader[sa] = sb;
            size[sb] += size[sa];
            if(size[sb] > max) max = size[sb];
        } 
        else {
            leader[sb] = sa;
            size[sa] += size[sb];
            if(size[sa] > max) max = size[sa];
        }
        components--;
        return true;
    }
}
