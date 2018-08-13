package helper.implementations;

import interfaces.Role;

public class TestRole implements Role {

	private String id;
	
	public TestRole(String id, int rank) {
		this.id = id;
		this.rank = rank;
	}

	private int rank;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getRank() {
		return rank;
	}

	@Override
	public boolean equals(Role role) {
		return id.equals(role.getId());
	}

}
