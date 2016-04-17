package solver;

import converter.Problem;

public class ProblemSolver {
	
	private Problem problem;
	
	public ProblemSolver(Problem problem) {
		this.problem = problem;
	}

	public void solve() {
		//...
	}
	
	public Double[] mutualPoint(Double[][] conditions) throws Exception {
		Double[] result = new Double[2];
		if(conditions.length != 2) {
			throw new Exception("2 conditions expected");
		}
		for(int i=0 ; i<conditions.length ; ++i) {
			if(conditions[i].length>3) {
				throw new Exception("this cannot be done on 2D");
			}
		}
		Double w = conditions[0][0]*conditions[1][1]-conditions[0][1]*conditions[1][0];
		if(w==0) {
			throw new Exception("parallel");
		}
		Double wx = conditions[0][1]*conditions[1][2]-conditions[0][2]*conditions[1][1];
		Double wy = conditions[0][0]*conditions[1][2]-conditions[1][0]*conditions[0][2];
		result[0] = wx/w;
		result[1] = wy/w;
		return result;
	}
	
	public Double[] parseIntoFunction(Double[] condition) throws Exception {
		if(condition.length>3) {
			throw new Exception("this cannot be done on 2D");
		}
		double factor = condition[1];
		for(int i=0 ; i<condition.length ; ++i) {
			condition[i] /= factor;
		}
		Double[] result = new Double[2];
		result[0] = condition[2];
		result[1] = -condition[0];
		return result;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

}
