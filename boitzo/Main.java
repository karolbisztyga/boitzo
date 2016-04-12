package boitzo;

import boitzo.Problem.RELATION;

public class Main {

	public static void main(String[] args) {
		try {
			ProblemConverter converter = new ProblemConverter();
			Double[][] d = {
				{1.0,8.0,3.0,2.0,25.0},
				{5.0,-2.0,0.0,6.0,4.0},
				{2.0,5.0,-4.0,3.0,7.0},
				{4.0,3.0,-2.0,1.0, Double.MAX_VALUE}
			};
			RELATION[] r = {
					RELATION.GREATER_OR_EQUAL,
					RELATION.SMALLER_OR_EQUAL,
					RELATION.ANY,
					RELATION.GREATER_OR_EQUAL
			};
			Problem pp = new Problem(d,r);
			//Problem p = converter.readFromFile("primitive.txt");
			converter.saveToFile(pp, "primitive.txt");
			
			Problem pd = converter.convert(pp);
			converter.saveToFile(pd, "dual.txt");
			
			Problem dualRead = converter.readFromFile("dual.txt");
			Problem primitiveRead = converter.convert(dualRead);
			converter.saveToFile(primitiveRead, "primitive2.txt");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
