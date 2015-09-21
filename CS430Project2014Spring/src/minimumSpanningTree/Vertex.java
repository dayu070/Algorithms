package minimumSpanningTree;

public class Vertex implements Comparable{
	private int id;
	private Vertex parent;
	private int rank;
	
	public Vertex(int id)
	{
		this.id = id;
		rank = 0;
		parent = this;
	}
	
	public Vertex(int id, int rank)       // For Prim's algorithm
	{
		this.id = id;
		this.rank = rank;
		parent = null;
	}
	
	public int getRank()
	{
		return rank;
	}
	
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	
	public void increaseRank()
	{
		rank++;
	}
	
	public void setParent(Vertex v)
	{
		parent = v;
	}
	
	public Vertex getParent()
	{
		return parent;
	}
	
	public int getID()
	{
		return id;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Vertex v = (Vertex)o;
		if (rank < v.getRank())
			return -1;
		else if (rank > v.getRank())
		    return 1;
		else
			return 0;
	}
}

class setOperation{
	
	private static void link (Vertex x, Vertex y)
	{
		if (x.getRank() > y.getRank())
			y.setParent(x);
		else
			x.setParent(y);
		if (x.getRank() == y.getRank())
			y.increaseRank();
	}
	
	public static Vertex findSet(Vertex x)
	{
		if (x != x.getParent())
			x.setParent(findSet(x.getParent()));
		return x.getParent();
	}
	
	public static void union(Vertex x, Vertex y)
	{
		link(findSet(x), findSet(y));
	}
}
