package helper;

import java.util.logging.Level;
import java.util.logging.Logger;


import exception.AccessDeniedException;
import interfaces.DestinationInterface;
import interfaces.RoleInterface;
import interfaces.Rules;
import interfaces.UserInterface;

public class AuthorizationHelper {

	private Rules rules;
	
	private Logger logger;
	
	public AuthorizationHelper(final Rules rules) {
		this.rules = rules;
	}
	
	public AuthorizationHelper(final Rules rules, Logger logger) {
		this.rules = rules;
		this.logger = logger;
	}
	
	public boolean isAllowed(final UserInterface who, final DestinationInterface where, final Action what) {
		if (logger != null)
			logger.log(Level.INFO, "Access required: " + who + " -> " + where + " -> " + what);
		
		Status ruleUserId = rules.getRuleUserId(who.getId(), where.getId(), what);
		Status ruleUserRank = rules.getRuleUserRank(who.getRank(), where.getId(), what);
		Status ruleRoleId = Status.UNSPECIFIED;
		Status ruleRoleRank = Status.UNSPECIFIED;
		
		for (RoleInterface r : who.getRoles()) {
			ruleRoleId = resolveRolesStatus(
					ruleRoleId,
					rules.getRuleRoleId(r.getId(), where.getId(), what)
				);
			
			ruleRoleRank = resolveRolesStatus(
					ruleRoleRank,
					rules.getRuleRoleRank(r.getRank(), where.getId(), what)
				);
		}
				
		if (ruleUserId != Status.UNSPECIFIED)
			return resolveResult(ruleUserId);

		if (ruleUserRank != Status.UNSPECIFIED)
			return resolveResult(ruleUserRank);

		if (ruleRoleId != Status.UNSPECIFIED)
			return resolveResult(ruleRoleId);

		if (ruleRoleRank != Status.UNSPECIFIED)
			return resolveResult(ruleRoleRank);
		
		if (logger != null)
			logger.log(Level.WARNING, "No access rule for: " + who + " -> " + where + " -> " + what); 
		return false;
	}
	
	private boolean resolveResult(final Status status) {
		if (status == Status.ALLOWED)
			return true;
		return false;
	}
	
	private Status resolveRolesStatus(final Status actual, final Status newS) {
		if (actual == Status.UNSPECIFIED)
			return newS;
		if (newS == Status.UNSPECIFIED)
			return actual;
		if (actual == Status.ALLOWED && newS == Status.DISALLOWED)
			return Status.ALLOWED;
		if (actual == Status.DISALLOWED && newS == Status.ALLOWED)
			return Status.ALLOWED;
		return actual;
	}
	
	public void throwIfIsNotAllowed(final UserInterface who, final DestinationInterface where, final Action what) throws AccessDeniedException {
		if(!isAllowed(who, where, what))
			throw new AccessDeniedException(who, where, what);
	}
}
