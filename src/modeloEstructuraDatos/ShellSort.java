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

	private static void exch(Comparable[] array, int j, int i) {
		// TODO Auto-generated method stub
		
	}

	private static boolean less(Comparable comparable, Comparable comparable2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
