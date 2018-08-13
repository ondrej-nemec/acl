package rules;

import java.util.List;
import java.util.Optional;

import helper.Action;

public interface Rules {
	
	public Optional<RuleForUser> getRuleUserId(String userId, String destinationId, Action action);
	
	public List<RuleForUser> getRuleUserRank(int userRank, String destinationId, Action action);
	
	public List<RuleForRole> getRuleRoleId(String roleId, String destinationId, Action action);
	
	public List<RuleForRole> getRuleRoleRank(int roleRank, String destinationId, Action action);

}
