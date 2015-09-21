package minimumSpanningTree;

public class Edge implements Comparable{

	private int vertex1, vertex2, weight;
	public Edge (int vertex1, int vertex2, int weight)
	{
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public int getVertex1()
	{
		return vertex1;
	}
	
	public int getVertex2()
	{
		return vertex2;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		try{
			if(!(o instanceof Edge))
			{
				throw new Exception();
			}
			Edge e = (Edge)o;
			if (this.weight > e.getWeight())
				return 1;
			else if (this.weight < e.getWeight())
				return -1;
			else 
				return 0;
		}catch (Exception e)
		{
			System.err.println("Invalid object.");
			return 0;
		}
		
	}
	
	public String toString()
	{
		return "vertex1: " + vertex1 + " vertex2: " + vertex2 + " weight: " + weight;
	}
	
	
}
