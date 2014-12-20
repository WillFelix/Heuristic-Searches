package puzzle8;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static Date tic, toc;
	public static final Puzzle goal = new Puzzle();
	public Puzzle puzzle = new Puzzle();
	public List<Puzzle> puzzles = new ArrayList<Puzzle>();
	public List<Puzzle> visiteds = new ArrayList<Puzzle>();
	
	public static void main(String[] args) {
		Main main = new Main();
		
		main.init();
		main.puzzle8();
		main.finish();
	}
	
	private void puzzle8() {
		
		boolean solved = false;
		while (!solved && !puzzles.isEmpty()) {
			puzzle = puzzles.remove(0);
			
			if (!puzzle.isGoal(goal.getMatrix())) {
				
				if (!contains(visiteds, puzzle)) {
					visiteds.add(puzzle);
					puzzles = childrenBirth(puzzles, puzzle);
				}
				
			} else {
				solved = true;
			}
			
		}
		
		if (!solved) {
			System.out.println("-------  GOAL NOT FOUND - 404  -------");
		}
		
	}

	private boolean contains(List<Puzzle> visiteds2, Puzzle puzzle2) {
		
		for (int w = 0; w < visiteds2.size(); w++) {
			Puzzle v = visiteds2.get(w);
			int count = 0;
			
			for (int i = 0; i < v.getMatrix().length; i++) {
				for (int j = 0; j < v.getMatrix()[0].length; j++) {
					if (v.getMatrix()[i][j] == puzzle2.getMatrix()[i][j]) {
						count++;
					}
				}
			}
			
			if (count == 9) {
				return true;
			}
			
		}
		
		return false;
	}

	private List<Puzzle> childrenBirth(List<Puzzle> puzzles, Puzzle puzzle) {
		
		int[][] m = puzzle.getMatrix();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				
				if (m[i][j] == 0) {
					puzzles.addAll(checkNeighboors(puzzle, i,j));
					
					i = 3;
					break;
				}
				
			}
		}
		
		return puzzles;
	}

	private List<Puzzle> checkNeighboors(Puzzle puzzle, int i, int j) {
		List<Puzzle> puzzles = new ArrayList<Puzzle>();
		
		if (checkBounds(i - 1, j)) {
			int[][] m = puzzle.cloneMatrix();
			m[i][j] = m[i - 1][j];
			m[i - 1][j] = 0;

			Puzzle p = new Puzzle(m);
			p.setParent(puzzle);
			
			puzzles.add(p);
		}
		
		if (checkBounds(i + 1, j)) {
			int[][] m = puzzle.cloneMatrix();
			m[i][j] = m[i + 1][j];
			m[i + 1][j] = 0;

			Puzzle p = new Puzzle(m);
			p.setParent(puzzle);
			
			puzzles.add(p);
		}
		
		if (checkBounds(i, j - 1)) {
			int[][] m = puzzle.cloneMatrix();
			m[i][j] = m[i][j - 1];
			m[i][j - 1] = 0;

			Puzzle p = new Puzzle(m);
			p.setParent(puzzle);
			
			puzzles.add(p);
		}
		
		if (checkBounds(i, j + 1)) {
			int[][] m = puzzle.cloneMatrix();
			m[i][j] = m[i][j + 1];
			m[i][j + 1] = 0;
			
			Puzzle p = new Puzzle(m);
			p.setParent(puzzle);
			
			puzzles.add(p);
		}
		
		return puzzles;
	}
	
	private boolean checkBounds(int i, int j) {
		
		if (i < 0 || i >= 3 || j < 0 || j >= 3)
			return false;
		
		return true;
	}

	private void init() {
		int[][] g = {{1,2,3},{4,5,6},{7,8,0}};
		goal.setMatrix(g);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inteligência Artifical");
		System.out.println("-------------------------------------------\n\n");
		
		System.out.print("OBJETIVO");
		goal.printMatrix();
		
		System.out.println("\n\nDigite o estado inicial de seu puzzle:");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print("Linha " + i + ", Coluna " + j + ": ");
				puzzle.getMatrix()[i][j] = scanner.nextInt();
			}
		}
		
		puzzle.printMatrix();
		System.out.println("\nProcessando....");
		puzzles.add(puzzle);
		
		tic = new Date();
		scanner.close();
	}
	
	
	private void finish() {
		toc = new Date();
		System.out.println("FINISHED in " + ((double) (toc.getTime() - tic.getTime()) / 1000) + " seconds!!\n");
		
		List<Puzzle> result = new ArrayList<Puzzle>();
		while (puzzle != null) {
			result.add(puzzle);
			puzzle = puzzle.getParent();
		}
		
		for (int i = result.size() - 1; i >= 0; i--) {
			result.get(i).printMatrix();
		}
		
	}
	
}
