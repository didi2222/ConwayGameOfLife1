
public class SuccessorWithDelete {
	QuickUnion qu;
	public SuccessorWithDelete(int n){
		qu= new QuickUnion(n);
	}
	
	public void remove(int i){
		qu.union(i, i+1);
	}
	
	public int successor(int i){
		return qu.root(i);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=50;
		SuccessorWithDelete swd= new SuccessorWithDelete(50);
		swd.remove(30);
		System.out.println(swd.successor(30));
		swd.remove(31);
		System.out.println(swd.successor(30));
	}

}
