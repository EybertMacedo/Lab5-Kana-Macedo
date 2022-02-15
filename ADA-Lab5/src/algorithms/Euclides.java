package algorithms;

public class Euclides {

	public static void main(String[] args) {

		System.out.println(MCM(45, 27));
	}

	public static int MCM(int i, int j) {
		return i * j / MCD(i, j);
	}

	public static int MCD(int i, int j) {
		if (i < j)
			return MCDEuclides(i, j);
		return MCDEuclides(j, i);
	}

	public static int MCDEuclides(int i, int j) {
		if (j == 0)
			return i;
		return MCDEuclides(j, i % j);
	}

}