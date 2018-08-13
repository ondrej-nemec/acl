package rules;

import java.io.Serializable;

import helper.Action;
import interfaces.Destination;
import rules.Rule;

public class Rule<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T who;
	
	private Destination where;
	
	private Action what;

	public Rule(T who, Destination where, Action what) {
		this.who = who;
		this.where = where;
		this.what = what;
	}

	public T getWho() {
		return who;
	}

	public void setWho(T who) {
		this.who = who;
	}

	public Destination getWhere() {
		return where;
	}

	public void setWhere(Destination where) {
		this.where = where;
	}

	public Action getWhat() {
		return what;
	}

	public void setWhat(Action what) {
		this.what = what;
	}

	@Override
	public String toString() {
		return "{"
				+ "[" + who +"]"
				+ "[" + where +"]"
				+ "[" + what + "]"
				+ "}";
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Rule))
			return false;
		return equals((Rule<?>)o);
	}
	
	public boolean equals(Rule<T> r) {
		if (!who.equals(r.who))
			return false;
		if (!where.equals(r.where))
			return false;
		if (!what.equals(r.what))
			return false;
		return true;
	}
}
