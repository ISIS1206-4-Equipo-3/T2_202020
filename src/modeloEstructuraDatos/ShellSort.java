package modeloEstructuraDatos;

public class ShellSort {

	public static void sort (Comparable[] array)
	{
		int N = array.length;
		int h = 1;
		while (h < N/3)
			h = 3*h +1;
		while ( h>=1)
		{
			for(int i=h; i<N; i++)
			{
				for (int j = i; j <=h && less(array[j], array[j-h]); j -=h) {
					
						exch (array, j, j-h);
				}
			}
			h = h/3;
		}
	}

	private static void exch(Comparable[] array, int i, int j) {
		
		Comparable temporal = array[i];
		array[i] = array[j];
		array[j] = temporal;
		
	}

	private static boolean less(Comparable z, Comparable x) {
	
		return z.compareTo(x) < 0 ;
	}
	
	
	
}
