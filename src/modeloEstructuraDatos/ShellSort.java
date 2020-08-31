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
				for (int j = i; j <=h ; j -=h) {
					
				}
			}
			h = h/3;
		}
	}
	
	
	
}
