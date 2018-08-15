package rules;

import helper.Action;

public interface Rules {
	
	default Status getRuleUserId(String userId, String destinationId, Action action) {
		return Status.UNSPECIFIED;
	}
	
	default Status getRuleUserRank(int userRank, String destinationId, Action action) {
		return Status.UNSPECIFIED;
	}
	
	default Status getRuleRoleId(String roleId, String destinationId, Action action) {
		return Status.UNSPECIFIED;
	}
	
	default Status getRuleRoleRank(int roleRank, String destinationId, Action action) {
		return Status.UNSPECIFIED;
	}
}
