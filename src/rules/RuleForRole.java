package rules;

import helper.Action;
import interfaces.Destination;
import interfaces.Role;

public class RuleForRole extends Rule<Role> {

	private static final long serialVersionUID = 1L;

	public RuleForRole(Role who, Destination where, Action what) {
		super(who, where, what);
	}

}
