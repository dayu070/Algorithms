package minimumSpanningTree;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestProgram {
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			/*Edge e = new Edge(1,2,3);
			Edge e2 = new Edge(1,2,4);
			System.out.println(e.compareTo(e2));
			System.out.print(e + "\n");
			System.out.println(e2);
			Vertex v1 = new Vertex(1);
			Vertex v2 = new Vertex(2);
			System.out.println(v1.getParent().getRank());
			setOperation.union(v1, v2);
			System.out.println(v1.getParent().getID());
			System.out.println(v2.getRank());*/
		
		
		
//	int graph[][] = {
	//	        /* A   B   C   D   E   F   G   H   I        */
	//	         {-1,  8, -1, 10, -1, -1, 12, -1, -1}, /* A */
	//	         { 8, -1, -1, -1, 12, 18, -1, -1, -1}, /* B */
	//	         {-1, -1, -1, -1, -1,  2, -1, 10, -1}, /* C */
	//	         {10, -1, -1, -1, -1,  8, -1, -1, -1}, /* D */
	//	         {-1, 12, -1, -1, -1, -1, 24, -1, -1}, /* E */
	//	         {-1, 18,  2,  8, -1, -1, -1, -1, -1}, /* F */
	//	         {12, -1, -1, -1, 24, -1, -1, -1, -1}, /* G */
	//	         {-1, -1, 10, -1, -1, -1, -1, -1,  1}, /* H */
	//	         {-1, -1, -1, -1, -1, -1, -1,  1, -1}, /* i */
	//	      };
		      Scanner scan = new Scanner(System.in);
		      System.out.println("			CS430 Project");
		      System.out.println("		The range of the edge's weight is (1~100)");
		      System.out.println("*************************************************************");
		     System.out.println("the num. of vertices: "); int len = scan.nextInt();
		     System.out.println("the num. of edges("+len+"~"+len*(len-1)/2+"): ");int edges =scan.nextInt();
		int[][] graph = new int[len][len];
		for(int i = 0 ;i<len ; i++)
			for(int j= 0 ; j<len;j++)
			{
				graph[i][j]=-1;
			}
		
		RandomGraph ranG = new RandomGraph();
		Graph g;
		do{
		ranG.randomGenerate(graph,edges);
		for(int i = 0 ;i<len ; i++)
		{
			for(int j= 0 ; j<len;j++)
			{
				System.out.print(graph[i][j]+"\t");
			}
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
		g = new Graph(graph);
		}while(!g.isConnected());
		System.out.println(g.isConnected());
		System.out.println(g.isUndirected());
		g.adjustGraph();
		
		System.out.println(g.isConnected());
	/*	for(int i=0; i<graph.length;i++)
		{
			System.out.print(" " + g.graph[8][i] + " ");
		}*/
		
		/*Integer inte[] = {5,7,1,9,8,10,21,3,33,6};
		QuickSort.quickSort(inte);
		for(Integer i : inte)
		{
			System.out.println(i);
		}*/
		ArrayList<Edge> ale;
		double start_0 = System.nanoTime();
		 ale = g.generateKruskalMST();
		 double finish_0 =System.nanoTime();
		int totalWeight = 0;
		for(int i=0;i<ale.size();i++)
		{	
			System.out.println(ale.get(i));
			totalWeight += ale.get(i).getWeight();
		}
		
		System.out.println("From the Kruskal's algorithm, the total weight of a MST is "+ totalWeight + ".");
		System.out.println("From the Kruskal's algorithm, the total running time is "+(finish_0-start_0)/1000000+"ms");
		System.out.println();
		double start_1 = System.nanoTime();
		ale = g.generagePrimMST();
		double  finish_1 =System.nanoTime();
		totalWeight = 0;
		for(int i=0;i<ale.size();i++)
		{	
			System.out.println(ale.get(i));
			totalWeight += ale.get(i).getWeight();
		}
		System.out.println("From the Prim's algorithm, the total weight of a MST is "+ totalWeight + ".");
		System.out.println("From the Prim's algorithm, the total running time is "+(finish_1-start_1)/1000000+"ms");
	}

}
