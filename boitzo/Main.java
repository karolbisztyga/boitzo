package boitzo;

import boitzo.Problem.CONDITION_TYPE;
import boitzo.Problem.RELATION;

public class Main {

	public static void main(String[] args) {
		
		Problem p = new Problem();
		p.new Condition(new Double[]{4.0,3.0,-2.0,1.0}, null, Integer.MAX_VALUE, CONDITION_TYPE.DESTINATION_FUNCTION);
		p.new Condition(new Double[]{1.0,8.0,3.0,2.0}, RELATION.SMALLER_OR_EQUAL, 25);
		p.new Condition(new Double[]{5.0,-2.0,0.0,6.0}, RELATION.GREATER_OR_EQUAL, 24);
		p.new Condition(new Double[]{2.0,5.0,-4.0,3.0}, RELATION.EQUAL, 7);
		p.new Condition(new Double[]{0.0,0.0,0.0,1.0}, RELATION.GREATER_OR_EQUAL, 0, CONDITION_TYPE.VARIABLE_SIGN);
		p.new Condition(new Double[]{0.0,1.0,0.0,0.0}, RELATION.SMALLER_OR_EQUAL, 0, CONDITION_TYPE.VARIABLE_SIGN);
		p.new Condition(new Double[]{0.0,0.0,1.0,0.0}, RELATION.EQUAL, 0, CONDITION_TYPE.VARIABLE_SIGN);
		//System.out.println(new JSONObject(p).toString());
		
		ProblemConverter converter = new ProblemConverter();
		converter.saveToFile("test.txt", p);
		//System.out.println(converter.readFromFile("test.txt").toString());
		
	}

}
