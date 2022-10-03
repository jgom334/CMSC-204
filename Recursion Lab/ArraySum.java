public class ArraySum {
	public ArraySum() {
	}
	
	public int sumOfArray(Integer[] a, int index) {
		
		if (index < 0)
			return 0;
		else {
			return a[index]+ sumOfArray(a, index-1);

		}
	}
}