package solver;

import java.util.Scanner;

import converter.Problem;

public class Main {

	public static void main(String[] args) {
		try {
			ProblemGenerator generator = new ProblemGenerator();
			Problem p = generator.generateFromInput(System.in);
			System.out.println(p);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
