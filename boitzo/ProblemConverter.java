package boitzo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import boitzo.Problem.Condition;
import boitzo.Problem.RELATION;

public class ProblemConverter {

	public Problem primitiveToDual(Problem primitive) {
		Problem dual = new Problem();
		int primitiveCondCounter = 0;
		//zamiana nierownosci(bez znakow relacji i z prawej)
		for(;;) {
			boolean goNextLoop = false;
			Condition dualCond = dual.new Condition(new Double[primitive.getConditions().size()], null, 0);
			int dualCondCounter = 0;
			for(Condition primitiveCond : primitive.getConditions()) {
				double value;
				try {
					value = primitiveCond.getLeftHandValues().get(primitiveCondCounter);
					goNextLoop = true;
				} catch(IndexOutOfBoundsException e) {
					value = 0;
				}
				dualCond.getLeftHandValues().set(dualCondCounter++, value);
			}
			++primitiveCondCounter;
			if(!goNextLoop) {
				break;
			}
		}
		//
		for(Condition variableSignCond : primitive.getConditions()) {
			int counter = 0;
			for(;;) {
				double value;
				try {
					value = variableSignCond.getLeftHandValues().get(counter++);
				} catch(IndexOutOfBoundsException e) {
					break;
				}
				if(value == 1) {
					RELATION newRelation = variableSignCond.getRelation();
					if(variableSignCond.getRelation() == RELATION.EQUAL) {
						newRelation = RELATION.ANY;
					} else if(variableSignCond.getRelation() == RELATION.ANY) {
						newRelation = RELATION.EQUAL;
					}
					dual.getConditions().get(counter).setRelation(newRelation);
					break;
				}
			}
		}
		return dual;
	}
	
	public Problem dualToPrimitive() {
		return null;
	}
	
	public void saveToFile(String fileName, Problem problem) {
		try {
			File file = new File(fileName);
			if(!file.exists()) {
				file.createNewFile();
			}
			String content = new Date() + "\n" + new Gson().toJson(problem) + "\n";
			Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.APPEND);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Problem readFromFile(String fileName) {
		try {
			String contents = new String(Files.readAllBytes(Paths.get(fileName)));
			return new Gson().fromJson(contents, Problem.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
