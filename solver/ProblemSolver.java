package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import converter.Problem;

public class ProblemSolver {
	
	private Problem problem;
	
	public ProblemSolver(Problem problem) {
		this.problem = problem;
	}

	public Double solve() throws Exception {
		Double[][] problemConditions = problem.getConditions();
		List<Double[]> points = new ArrayList<>();
		for(int i=0 ; i<problemConditions.length-1 ; ++i) {
			for(int j=i+1 ; j<problemConditions.length-1 ; ++j) {
				Double[][] conds = new Double[2][];
				conds[0] = problemConditions[i];
				conds[1] = problemConditions[j];
				Double[] point = this.mutualPoint(conds);
				boolean add = true;
				//sprawdza czy zmienne sa ok
				for(int k=0 ; k<2 ; ++k) {
					switch(problem.getVariablesRelations()[k]) {
						case EQUAL: {
							if(point[k] != problem.getVariablesConditions()[k]) {
								add = false;
							}
							break;
						}
						case GREATER: {
							if(point[k] <= problem.getVariablesConditions()[k]) {
								add = false;
							}
							break;
						}
						case GREATER_OR_EQUAL: {
							if(point[k] < problem.getVariablesConditions()[k]) {
								add = false;
							}
							break;
						}
						case SMALLER: {
							if(point[k] >= problem.getVariablesConditions()[k]) {
								add = false;
							}
							break;
						}
						case SMALLER_OR_EQUAL: {
							if(point[k] > problem.getVariablesConditions()[k]) {
								add = false;
							}
							break;
						}
						default: {
							break;
						}
					}
				}
				if(!add) {
					break;
				}
				//sprawdza czy punkt spelnia wszystkie nierownosci
				for(int k=0 ; k<problemConditions.length-1 ; ++k) {
					Double[] cond = problemConditions[k];
					switch(problem.getRelations()[k]) {
						case EQUAL: {
							if(cond[0]*point[0] + cond[1]*point[1] != cond[2]) {
								add = false;
							}
							break;
						}
						case GREATER: {
							if(cond[0]*point[0] + cond[1]*point[1] <= cond[2]) {
								add = false;
							}
							break;
						}
						case GREATER_OR_EQUAL: {
							if(cond[0]*point[0] + cond[1]*point[1] < cond[2]) {
								add = false;
							}
							break;
						}
						case SMALLER: {
							if(cond[0]*point[0] + cond[1]*point[1] >= cond[2]) {
								add = false;
							}
							break;
						}
						case SMALLER_OR_EQUAL: {
							if(cond[0]*point[0] + cond[1]*point[1] <= cond[2]) {
								add = false;
							}
							break;
						}
						default: {
							break;
						}
					}
					if(!add) {
						break;
					}
				}
				if(add) {
					points.add(point);
				}
			}
		}
		List<Double> destinationValues = new ArrayList<>();
		for(Double[] point : points) {
			destinationValues.add(problemConditions[problemConditions.length-1][0]*point[0]+
					problemConditions[problemConditions.length-1][1]*point[1]);
		}
		if(destinationValues.isEmpty()) {
			return null;
		}
		if(problemConditions[problemConditions.length-1][2] == Double.MAX_VALUE) {
			return Collections.max(destinationValues);
		} else {
			return Collections.min(destinationValues);
		}
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
