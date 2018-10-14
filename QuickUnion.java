
public class QuickUnion {
	int[] id;
	public QuickUnion(int n){
		id= new int[n+1];
		for(int i=1; i<=n; ++i){
			id[i]=i;
		}
	}
	
	public int root(int i){
		while(i!=id[i]){
			id[i]=id[id[i]];
			i=id[i];
		}
		return i;
	}
	
	public void union(int i, int j){
		int rooti= root(i);
		int rootj= root(j);
		id[rooti]=rootj;
	}

}
