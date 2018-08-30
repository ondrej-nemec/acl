package helper.implementations;

import interfaces.RoleInterface;

public class TestRole implements RoleInterface {

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
	public boolean equals(RoleInterface role) {
		return id.equals(role.getId());
	}
	@Override
	public String toString() {
		return id;
	}
}
