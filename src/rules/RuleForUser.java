package rules;

import helper.Action;
import interfaces.Destination;
import interfaces.User;
import rules.Rule;

public class RuleForUser extends Rule<User> {

	private static final long serialVersionUID = 1L;

	public RuleForUser(User who, Destination where, Action what) {
		super(who, where, what);
	}

}
