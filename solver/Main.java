package solver;

import converter.Problem;

public class Main {

	public static void main(String[] args) {
		try {
			ProblemGenerator generator = new ProblemGenerator();
			Problem p = generator.generateFromInput(System.in);
			System.out.println(p);

			ProblemSolver solver = new ProblemSolver(p);
			/*Double conds[][] = new Double[2][];
			conds[0] = p.getConditions()[0];
			conds[1] = p.getConditions()[1];
			Double[] pt = solver.mutualPoint(conds);
			System.out.println(pt[0]+","+pt[1]);*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
