package converter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;

import converter.Problem.RELATION;

public class ProblemConverter {

	public Problem convert(Problem primitive) {
		Double[][] conds = primitive.getConditions();
		Problem dual = new Problem(conds[0].length, conds.length);
		for(int i=0 ; i<conds.length ; ++i) {
			for(int j=0 ; j<conds[0].length ; ++j) {
				if(conds[i][j] == Double.MAX_VALUE) {
					dual.getConditions()[j][i] = Double.MIN_VALUE;
				} else if(conds[i][j] == Double.MIN_VALUE) {
					dual.getConditions()[j][i] = Double.MAX_VALUE;
				} else {
					dual.getConditions()[j][i] = conds[i][j];
				}
			}
		}
		for(int i=0 ; i<primitive.getRelations().length ; ++i) {
			RELATION rel = primitive.getRelations()[i];
			if(rel == null) continue;
			switch(rel) {
				case EQUAL: {
					dual.getVariablesRelations()[i] = RELATION.ANY;
					break;
				}
				case ANY: {
					dual.getVariablesRelations()[i] = RELATION.EQUAL;
					break;
				}
				case GREATER_OR_EQUAL:
				case GREATER: {
					if(primitive.getConditions()[primitive.getConditions().length-1][primitive.getConditions()[0].length-1] == 
							Double.MIN_VALUE) {
						dual.getVariablesRelations()[i] = RELATION.GREATER_OR_EQUAL;
					} else {
						dual.getVariablesRelations()[i] = RELATION.SMALLER_OR_EQUAL;
					}
					break;
				}
				case SMALLER_OR_EQUAL:
				case SMALLER: {
					if(primitive.getConditions()[primitive.getConditions().length-1][primitive.getConditions()[0].length-1] == 
							Double.MIN_VALUE) {
						dual.getVariablesRelations()[i] = RELATION.SMALLER_OR_EQUAL;
					} else {
						dual.getVariablesRelations()[i] = RELATION.GREATER_OR_EQUAL;
					}
					break;
				}
			}
		}
		for(int i=0 ; i<primitive.getVariablesRelations().length ; ++i) {
			RELATION rel = primitive.getVariablesRelations()[i];
			if(rel == null) continue;
			switch(rel) {
				case EQUAL: {
					dual.getRelations()[i] = RELATION.ANY;
					break;
				}
				case ANY: {
					dual.getRelations()[i] = RELATION.EQUAL;
					break;
				}
				case GREATER_OR_EQUAL:
				case GREATER: {
					if(dual.getConditions()[dual.getConditions().length-1][dual.getConditions()[0].length-1] == 
							Double.MIN_VALUE) {
						dual.getRelations()[i] = RELATION.GREATER_OR_EQUAL;
					} else {
						dual.getRelations()[i] = RELATION.SMALLER_OR_EQUAL;
					}
					break;
				}
				case SMALLER_OR_EQUAL:
				case SMALLER: {
					if(dual.getConditions()[dual.getConditions().length-1][dual.getConditions()[0].length-1] == 
							Double.MAX_VALUE) {
						dual.getRelations()[i] = RELATION.GREATER_OR_EQUAL;
					} else {
						dual.getRelations()[i] = RELATION.SMALLER_OR_EQUAL;
					}
					break;
				}
			}
		}
		return dual;
	}

	public void saveToFile(Problem problem, String fileName) {
		try {
			File file = new File(fileName);
			PrintWriter writer = new PrintWriter(file);
			writer.write("");
			writer.close();
			if(!file.exists()) {
				file.createNewFile();
			}
			String content = new Gson().toJson(problem);
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
