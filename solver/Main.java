package solver;

import converter.Problem;
import converter.ProblemConverter;

public class Main {

	public static void main(String[] args) {
		try {
			/* *
			ProblemGenerator generator = new ProblemGenerator();
			Problem p = generator.generateFromInput(System.in);
			System.out.println(p);
			/* *
			ProblemSolver s = new ProblemSolver(null);
			Double[][] conds = {{4.0,2.0,8.0},{9.0,3.0,9.0}};
			Double[] p = s.mutualPoint(conds);
			System.out.println(p[0]+","+p[1]);
			* */
			ProblemConverter conv = new ProblemConverter();
			Problem p = conv.readFromFile("src/miesz.txt");
			ProblemSolver solver = new ProblemSolver(p);
			Double result = solver.solve();
			System.out.println("RESULT: " + result);
			/* */
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
