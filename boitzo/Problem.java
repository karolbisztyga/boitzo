package boitzo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem {
	
	public enum RELATION {
		GREATER,
		GREATER_OR_EQUAL,
		EQUAL,
		SMALLER_OR_EQUAL,
		SMALLER,
		ANY
	};
	
	public enum CONDITION_TYPE {
		DESTINATION_FUNCTION,
		CONDITION,
		VARIABLE_SIGN
	};

	private List<Condition> conditions = new ArrayList<>();
	private List<Condition> variablesSigns = new ArrayList<>();
	Condition destinadtionFunction = null;

	public List<Condition> getVariablesSigns() {
		return variablesSigns;
	}

	public void setVariablesSigns(List<Condition> variablesSigns) {
		this.variablesSigns = variablesSigns;
	}

	public Condition getDestinadtionFunction() {
		return destinadtionFunction;
	}

	public void setDestinadtionFunction(Condition destinadtionFunction) {
		this.destinadtionFunction = destinadtionFunction;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public class Condition {
		
		private List<Double> leftHandValues;
		private RELATION relation;
		private double rightHandValue;
		private CONDITION_TYPE type;
		
		public Condition(Double[] leftHandValuesArray, RELATION relation, double rightHandValue, CONDITION_TYPE type) {
			this.leftHandValues = Arrays.asList(leftHandValuesArray);
			this.relation = relation;
			this.rightHandValue = rightHandValue;
			this.type = type;
			switch(this.type) {
				case DESTINATION_FUNCTION: {
					Problem.this.setDestinadtionFunction(this);
					break;
				}
				case CONDITION: {
					Problem.this.getConditions().add(this);
					break;
				}
				case VARIABLE_SIGN: {
					Problem.this.getVariablesSigns().add(this);
					break;
				}
			}
		}
		
		public Condition(Double[] leftHandValuesArray, RELATION relation, double rightHandValue) {
			this(leftHandValuesArray, relation, rightHandValue, CONDITION_TYPE.CONDITION);
		}

		public List<Double> getLeftHandValues() {
			return leftHandValues;
		}

		public void setLeftHandValues(List<Double> leftHandValues) {
			this.leftHandValues = leftHandValues;
		}

		public RELATION getRelation() {
			return relation;
		}

		public void setRelation(RELATION relation) {
			this.relation = relation;
		}

		public double getRightHandValue() {
			return rightHandValue;
		}

		public void setRightHandValue(double rightHandValue) {
			this.rightHandValue = rightHandValue;
		}

		public CONDITION_TYPE getType() {
			return type;
		}

		public void setType(CONDITION_TYPE type) {
			this.type = type;
		}
		
	}

	@Override
	public String toString() {
		return "Problem [conditions=" + conditions + ", variablesSigns=" + variablesSigns + ", destinadtionFunction="
				+ destinadtionFunction + "]";
	}
	
	
	
}
