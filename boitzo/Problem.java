package boitzo;

public class Problem {
	
	public enum RELATION {
		GREATER,
		GREATER_OR_EQUAL,
		EQUAL,
		SMALLER_OR_EQUAL,
		SMALLER,
		ANY
	};
	
	private Double[][] conditions;
	private RELATION[] relations;

	public Problem(Double[][] conditions, RELATION[] relations) throws Exception {
		this.conditions = conditions;
		this.relations = relations;
		if(conditions[0].length-1 != relations.length) {
			throw new Exception("wrong number of relations");
		}
	}
	
	public Problem(int conditionsRows, int conditionsCols) {
		this.conditions = new Double[conditionsRows][conditionsCols];
		this.relations = new RELATION[conditionsCols];
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
	
}
