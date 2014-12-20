package puzzle8;

public class Puzzle {

	private Puzzle parent;

	private int[][] matrix;

	public Puzzle() {
		this.matrix = new int[3][3];
	}

	public Puzzle(int[][] matrix) {
		this.matrix = matrix;
	}

	public void printMatrix() {
		System.out.println("\n");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	public boolean isGoal(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {

				if (matrix[i][j] != m[i][j]) {
					return false;
				}

			}
		}

		return true;
	}

	public int[][] cloneMatrix() {
		int[][] clone = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone[0].length; j++) {
				clone[i][j] = matrix[i][j];
			}
		}

		return clone;
	}

	public Puzzle getParent() {
		return parent;
	}

	public void setParent(Puzzle parent) {
		this.parent = parent;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

}
