package solver;

import java.io.InputStream;
import java.util.Scanner;

import converter.Problem;
import converter.Problem.RELATION;

public class ProblemGenerator {
	
	public Problem generateFromInput(InputStream input) throws Exception {
		Scanner scanner = new Scanner(input);
		
		System.out.println("set number of conditions");
		int conditions = scanner.nextInt();
		System.out.println("set number of variables");
		int variables = scanner.nextInt();
		Problem result = new Problem(conditions, variables+1);
		
		for(int i=0,j=0 ; i<conditions ; ++i) {
			System.out.println("setting condition " + (i+1));
			for(j=0 ; j<variables ; ++j) {
				System.out.println("set variable x" + (j+1));
				result.getConditions()[i][j] = scanner.nextDouble();
			}
			System.out.println("set relation");
			for(int k=0 ; k<RELATION.values().length-1 ; ++k) {
				System.out.println("["+ k +"]" + RELATION.values()[k]);
			}
			int relationIndex = scanner.nextInt();
			if(relationIndex==5) throw new Exception("relation ANY forbidden here");
			result.getRelations()[i] = RELATION.values()[relationIndex];
			System.out.println("set righthand value");
			result.getConditions()[i][j] = scanner.nextDouble();
		}
		
		System.out.println("set variables conditions");
		for(int i=0 ; i<variables ; ++i) {
			System.out.println("x" + (i+1));
			for(int k=0 ; k<RELATION.values().length ; ++k) {
				System.out.println("["+ k +"]" + RELATION.values()[k]);
			}
			result.getVariablesRelations()[i] = RELATION.values()[scanner.nextInt()];
			System.out.println("righthand value");
			result.getVariablesConditions()[i] = scanner.nextDouble();
		}
		
		return result;
	}
	
}
