package minimumSpanningTree;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Graph {
	private int[][] graph;								//
	
	public Graph (int[][] graph) throws Exception 
	{
		if(graph.length != graph[0].length)         //If the input matrix is not a graph
			throw new Exception();
		else									
		{
			for (int i=0; i<graph.length; i++)			//The diagonal going from upper left to the lower right only contains non-positive numbers 
			{
				if(graph[i][i] > 0)
					throw new Exception();
			}
			this.graph = new int[graph.length][graph.length];
			for(int i=0;i<graph.length;i++)
			{
				for(int j=0;j<graph.length;j++)
					this.graph[i][j] = graph[i][j];
			}
		}
	}
	
	public boolean isConnected()                                   //Use breadth first search to determine the connectedness
	{
		Integer [] vertices = new Integer[graph.length];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i=0; i<graph.length; i++)
			vertices[i] = i;
		
		pq.add(0);
		
		while(pq.peek() != null)
		{
			al.add(pq.peek());
			int temp = pq.poll();
			for(int i=0; i<graph.length; i++)
			{
				if(graph[temp][i] > 0 && !al.contains(i) && !pq.contains(i))
				{
					pq.add(i);
				}
			}
		}
		
		for(int i=0; i<graph.length; i++)
		{
			if(!al.contains(i))
				return false;
		}
		
		return true;
	}

	public boolean isUndirected()
	{
		for(int i=1; i<graph.length;i++)
		{
			for(int j=0; j<i; j++)
			{
				if (graph[i][j] != graph[j][i])
					return false;
			}
		}
		return true;
	}
	
	public void adjustGraph()                             //Add edges until the graph is connected
	{
		int vertexUnconnected = -1;
		while(!isConnected())
		{
			Integer [] vertices = new Integer[graph.length];
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			ArrayList<Integer> al = new ArrayList<Integer>();
			for(int i=0; i<graph.length; i++)
				vertices[i] = i;
			
			pq.add(0);
			
			while(pq.peek() != null)
			{
				al.add(pq.peek());
				int temp = pq.poll();
				for(int i=0; i<graph.length; i++)
				{
					if(graph[temp][i] > 0 && !al.contains(i) && !pq.contains(i))
					{
						pq.add(i);
					}
				}
			}
			
			for(int i=0; i<graph.length; i++)                             //Find a vertex that isn't connected to Vertex0
			{
				if(!al.contains(i))
					vertexUnconnected = i;
			}
			
			Random r = new Random();
			int vertexConnected = 0;
			while (vertexConnected == 0)                                   //Randomly choose a vertex that is connected to Vertex0
				vertexConnected = al.get(r.nextInt(al.size()));
			
			int randomWeight = 100 - r.nextInt(100);          //Randomly generate the weight of the new edge within [1, 100]
			graph[vertexConnected][vertexUnconnected] = randomWeight;       //Add the new edge to the graph
			graph[vertexUnconnected][vertexConnected] = randomWeight;
		}
	}
	
	public ArrayList<Edge> generateKruskalMST()
	{
		ArrayList<Edge> edgesOfMST = new ArrayList<Edge>();
		try
		{
			if (!isUndirected())
				throw new Exception();
			if (!isConnected() )								//Randomly make the graph connnected if it's not connected
				adjustGraph();
					
			ArrayList<Vertex> vertices = new ArrayList<Vertex>();
			for(int i=0; i<graph.length; i++)      //Make set for each vertex
				vertices.add(new Vertex(i));
			
			Edge allEdges[] = new Edge[graph.length*graph.length];
			int numOfEdges = 0;
			for(int i=0; i<graph.length-1; i++)                  //Add all edges to the array
			{
				for(int j=i+1; j<graph.length; j++)
				{
					if (graph[i][j] > 0)					
						allEdges[numOfEdges++] = new Edge(i,j,graph[i][j]);					
				}
			}
			
			Edge temp[] = new Edge[numOfEdges];
			System.arraycopy(allEdges, 0, temp, 0, numOfEdges);
			allEdges = temp;
			
			QuickSort.quickSort(allEdges);      //Sort all edges into nondecreasing order by weight
			
			for (int i=0; i<numOfEdges; i++)
			{
				Edge e = allEdges[i];
				int vertex1 = e.getVertex1();
				int vertex2 = e.getVertex2();
				if (setOperation.findSet(vertices.get(vertex1)) != setOperation.findSet(vertices.get(vertex2)))
				{
					edgesOfMST.add(e);
					setOperation.union(vertices.get(vertex1), vertices.get(vertex2));
				}
			}			
		}catch(Exception e)
		{
			System.err.println("This graph cannot generate a Minimum Spanning Tree!");
		}
		finally
		{
			return edgesOfMST;
		}
	}
	
	public ArrayList<Edge> generagePrimMST()
	{
		ArrayList<Edge> edgesOfMST = new ArrayList<Edge>();
		try
		{
			int numberOfVertices = 0;
			if (!isUndirected())
				throw new Exception();
			if (!isConnected() )								//Randomly make the graph connnected if it's not connected
				adjustGraph();
			
			Vertex allVertices[] = new Vertex[graph.length];
			PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
			for (int i=0; i<graph.length; i++)
			{
				if(i==0)
				{
					allVertices[numberOfVertices] = new Vertex(i, 0);
					pq.add(allVertices[numberOfVertices]);         //Use the first vertex as the starting vertex
					numberOfVertices++;
				}						
				else
				{
					allVertices[numberOfVertices] = new Vertex(i, 99999);  
					pq.add(allVertices[numberOfVertices]);        
					numberOfVertices++;
				}	
			}
			while (pq.peek() != null)
			{
				Vertex v = pq.poll();
				for(int i=0; i<graph.length; i++)          
				{
					if(graph[v.getID()][i] != -1 && pq.contains(allVertices[i]) && graph[v.getID()][i] < allVertices[i].getRank())//Consider all connected vertices
					{
						pq.remove(allVertices[i]);
						allVertices[i].setRank(graph[v.getID()][i]);
						allVertices[i].setParent(v);
						pq.add(allVertices[i]);
					}
				}
			}
			
			for (int i=1; i<graph.length; i++)
			{
				edgesOfMST.add(new Edge(allVertices[i].getParent().getID(), i, allVertices[i].getRank()));
			}
		}catch(Exception e)
		{
			System.err.println("This graph cannot generate a Minimum Spanning Tree!");
		}finally
		{
			return edgesOfMST;
		}
		
	}
}
