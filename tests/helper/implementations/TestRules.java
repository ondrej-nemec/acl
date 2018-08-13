package helper.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import helper.Action;
import rules.RuleForRole;
import rules.RuleForUser;
import rules.Rules;

public class TestRules implements Rules {

	private List<RuleForUser> userIdRules;
	
	private List<RuleForRole> rolesIdRules;
	
	private List<RuleForUser> userRankRules;
	
	private List<RuleForRole> rolesRankRules;

	public TestRules(List<RuleForUser> userIdRules, List<RuleForRole> rolesIdRules, List<RuleForUser> userRankRules,
			List<RuleForRole> rolesRankRules) {
		this.userIdRules = userIdRules;
		this.rolesIdRules = rolesIdRules;
		this.userRankRules = userRankRules;
		this.rolesRankRules = rolesRankRules;
	}

	//TODO check if list is not null
	@Override
	public Optional<RuleForUser> getRuleUserId(String userId, String destinationId, Action action) {
		if(userIdRules != null)
			for(RuleForUser r : userIdRules) {
				if (
					userId.equals(r.getWho().getId()) &&
					destinationId.equals(r.getWhere().getId()) &&
					thisOrMoreAction(action, r.getWhat())
				)
					return Optional.of(r);
			}
		return Optional.empty();
	}

	@Override
	public List<RuleForUser> getRuleUserRank(int userRank, String destinationId, Action action) {
		List<RuleForUser> result = new ArrayList<>();
		if(userRankRules != null)
			for(RuleForUser r : userRankRules) {
				if (
					userRank <= r.getWho().getRank() &&
					destinationId.equals(r.getWhere().getId()) &&
					thisOrMoreAction(action, r.getWhat())
				)
					result.add(r);
			}
		return result;
	}

	@Override
	public List<RuleForRole> getRuleRoleId(String roleId, String destinationId, Action action) {
		List<RuleForRole> result = new ArrayList<>();
		if(rolesIdRules != null)
			for(RuleForRole order : rolesIdRules) {
				if (
					roleId.equals(order.getWho().getId()) &&
					destinationId.equals(order.getWhere().getId()) &&
					thisOrMoreAction(action, order.getWhat())
				)
					result.add(order);
				
			}		
		return result;
	}

	@Override
	public List<RuleForRole> getRuleRoleRank(int roleRank, String destinationId, Action action) {
		List<RuleForRole> result = new ArrayList<>();
		if(rolesRankRules != null)
			for(RuleForRole order : rolesRankRules) {
				if (
					roleRank <= order.getWho().getRank() &&
					destinationId.equals(order.getWhere().getId()) &&
					thisOrMoreAction(action, order.getWhat())
				)
					result.add(order);
			}		
		return result;
	}
	
	private boolean thisOrMoreAction(Action userAc, Action ruleAc) {
		return userAc.ordinal() >= ruleAc.ordinal();
	}
}
