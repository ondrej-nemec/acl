package roles;

import java.io.Serializable;

import interfaces.Action;
import interfaces.Destination;
import interfaces.User;

public class Rule implements Serializable {

	private static final long serialVersionUID = 1L;

	private User who;
	
	private Destination where;
	
	private Action what;

	public Rule(User who, Destination where, Action what) {
		super();
		this.who = who;
		this.where = where;
		this.what = what;
	}

	public User getWho() {
		return who;
	}

	public void setWho(User who) {
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
		return equals((Rule)o);
	}
	
	public boolean equals(Rule r) {
		if (!who.equals(r.who))
			return false;
		if (!where.equals(r.where))
			return false;
		if (!what.equals(r.what))
			return false;
		return true;
	}
}
