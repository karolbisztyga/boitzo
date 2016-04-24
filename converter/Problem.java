package converter;

import com.google.gson.Gson;

public class Problem {
	
	public enum RELATION {
		GREATER,
		GREATER_OR_EQUAL,
		EQUAL,
		SMALLER_OR_EQUAL,
		SMALLER,
		ANY
	};
	
	/**
	 * jako ostatnia trzeba podac funkcje celu
	 */
	private Double[][] conditions;
	private Double[] variablesConditions;
	private RELATION[] variablesRelations;
	private RELATION[] relations;

	public Problem(Double[][] conditions, RELATION[] relations, Double[] variablesConditions,
			RELATION[] variablesRelations) throws Exception {
		super();
		this.conditions = conditions;
		this.variablesConditions = variablesConditions;
		this.variablesRelations = variablesRelations;
		this.relations = relations;
		if(conditions[0].length-1 != relations.length || variablesConditions.length != conditions.length) {
			throw new Exception("wrong number of relations");
		}
	}

	public Problem(int conditions, int variables) {
		this.conditions = new Double[conditions][variables];
		this.relations = new RELATION[conditions];
		this.variablesConditions = new Double[variables];
		this.variablesRelations = new RELATION[variables];
		for(int i=0 ; i<this.variablesConditions.length ; ++i) {
			this.variablesConditions[i] = 0.0;
		}
	}

	public Double[][] getConditions() {
		return conditions;
	}

	public void setConditions(Double[][] conditions) {
		this.conditions = conditions;
	}

	public RELATION[] getRelations() {
		return relations;
	}

	public void setRelations(RELATION[] relations) {
		this.relations = relations;
	}
	
	public Double[] getVariablesConditions() {
		return variablesConditions;
	}

	public void setVariablesConditions(Double[] variablesConditions) {
		this.variablesConditions = variablesConditions;
	}

	public RELATION[] getVariablesRelations() {
		return variablesRelations;
	}

	public void setVariablesRelations(RELATION[] variablesRelations) {
		this.variablesRelations = variablesRelations;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
