package minimumSpanningTree;

public class QuickSort {
	public static void quickSort(Object[] x)
	{
		quick(x, 0, x.length);
	}
	
	private static void quick(Object[] x, int off, int len)
	{
		int m = off + (len >> 1),
			l = off,
			n = off + len - 1;
		
		m = med3 (x, l, m, n);
		Object v = x[m];
		
		int b = off,
			c = off + len - 1;
		while(true)
		{
			while (b<=c && ((Comparable)x[b]).compareTo(v)<0)
				b++;
			while (c>=b && ((Comparable)x[c]).compareTo(v)>0)
				c--;
			if (b>c)
				break;
			swap (x, b++, c--);
		}
		
		if (c+1-off>1)
			quick(x, off, c+1-off);
		if (off+len-b>1)
			quick(x, b, off+len-b);
	}
	
	private static int med3 (Object[] x, int a, int b, int c)
	{
		if (((Comparable)x[a]).compareTo(x[b])<0)
		{
			if (((Comparable)x[b]).compareTo(x[c])<0)
				return b;
			else if (((Comparable)x[a]).compareTo(x[c])<0)
				return c;
			else
				return a;
		}
		else
		{
			if (((Comparable)x[b]).compareTo(x[c])>0)
				return b;
			else if (((Comparable)x[a]).compareTo(x[c])>0)
				return c;
			else
				return a;
		}
	}
	
	public static void swap(Object [] x, int a, int b) 
	   {
	      Object t = x[a];
	      x[a] = x[b];
	      x[b] = t;
	   }
}
