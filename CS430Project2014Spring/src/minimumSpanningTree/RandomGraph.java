package minimumSpanningTree;

import java.util.Random;

public class RandomGraph {
	public static void randomGenerate(int[][] graph,int edges)
	{
		Random ran = new Random();
		int[] arr = new int[edges];
		int recordTimes=0;
		if(edges<=2*(graph.length)*graph.length/2&&edges>=graph.length)
		{
			
			for(int i=0; i<edges;i++)
			{
				arr[i]=ran.nextInt(99)+1;
			}
			

				
		while(recordTimes<edges)
		{
			int len;
			int weight;
			do
			{
				len = ran.nextInt(graph.length);
				weight = ran.nextInt(graph.length);
			}
				while(len>=weight);
			if(graph[len][weight]==-1)
			{
			graph[len][weight]=arr[recordTimes++];
			graph[weight][len]=graph[len][weight];
			}
					
		}
		}
		else 
			{System.out.println("NOT IN RANGE!!!!");
			System.exit(0);
			}
			
	}
}
