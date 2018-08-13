package helper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import exception.AccessDeniedException;
import helper.implementations.TestDestination;
import helper.implementations.TestRole;
import helper.implementations.TestRules;
import helper.implementations.TestUser;
import interfaces.Destination;
import interfaces.Role;
import interfaces.User;
import rules.RuleForRole;
import rules.RuleForUser;
import rules.Rules;

@RunWith(Parameterized.class)
public class AuthorizationHelperTest {

	private User user;
	
	private Destination dest;
	
	private Action act;
	
	private boolean isAlloved;
	
	private AuthorizationHelper helper = new AuthorizationHelper(getRules());

	public AuthorizationHelperTest(User user, Destination dest, Action act, boolean isAlloved) {
		this.user = user;
		this.dest = dest;
		this.act = act;
		this.isAlloved = isAlloved;
	}

	@Test
	public void testIsAllowedWorks() {
		assertEquals(
				isAlloved,
				helper.isAllowed(user, dest, act)
		);
		fail();
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testThrowIfIsNotAllowedThrows() throws AccessDeniedException {
		helper.throwIfIsNotAllowed(users().get(2), destinations().get(0), Action.READ);
	}
	
	@Test
	public void testThrowIfIsNotAllowedWorks() {
		try {
			helper.throwIfIsNotAllowed(users().get(2), destinations().get(0), Action.READ);
		} catch (AccessDeniedException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	private Rules getRules() {
		return new TestRules(
				Arrays.asList(
						new RuleForUser(users().get(2), destinations().get(0), Action.FORBIDDEN),
						new RuleForUser(users().get(2), destinations().get(1), Action.FORBIDDEN),
						new RuleForUser(users().get(2), destinations().get(2), Action.FORBIDDEN),
						new RuleForUser(users().get(2), destinations().get(3), Action.FORBIDDEN),
						new RuleForUser(users().get(2), destinations().get(4), Action.FORBIDDEN),
						new RuleForUser(users().get(2), destinations().get(5), Action.FORBIDDEN),
						
						new RuleForUser(users().get(3), destinations().get(3), Action.DELETE)
					),
				Arrays.asList(
						new RuleForRole(roles().get(1), destinations().get(3), Action.READ),
						new RuleForRole(roles().get(1), destinations().get(4), Action.READ),
						new RuleForRole(roles().get(1), destinations().get(5), Action.READ),

						new RuleForRole(roles().get(2), destinations().get(3), Action.UPDATE),
						new RuleForRole(roles().get(2), destinations().get(4), Action.UPDATE),
						new RuleForRole(roles().get(2), destinations().get(5), Action.UPDATE)
					),
				null,
				/*
				Arrays.asList(
						new RuleForUser(null, null, null)
					),*/
				Arrays.asList(
						new RuleForRole(roles().get(3), destinations().get(3), Action.ADMIN)
					)
				);
	}
	
	@Parameters
	public static Collection<Object[]> getDataSet() {
		return Arrays.asList(
				new Object[]{
						users().get(2), destinations().get(5), Action.READ, false,
					},
				new Object[]{
						users().get(0), destinations().get(5), Action.ADMIN, false,
					},
				new Object[]{
						users().get(4), destinations().get(3), Action.DELETE, false,
					},
				new Object[]{
						users().get(3), destinations().get(3), Action.DELETE, true,
					},
				new Object[]{
						users().get(5), destinations().get(3), Action.DELETE, true,
					}
				);
	}
	
	private static List<User> users() {
		return Arrays.asList(
				new TestUser("admin", 100, roles()),
				new TestUser("IT manager", 50, Arrays.asList(
						roles().get(1), roles().get(2)
						)),
				new TestUser("guest", 0, Arrays.asList(
							roles().get(0)
						)),
				new TestUser("Programmer", 40, Arrays.asList(
						roles().get(1)
					)),
				new TestUser("Web-designer", 20, Arrays.asList(
						roles().get(1)
					)),
				new TestUser("DB-admin", 30, Arrays.asList(
						roles().get(1), roles().get(3)
					))
			);
	}
	 //TODO destination -> rank, this destination and less/more
	private static List<Destination> destinations() {
		return Arrays.asList(
				new TestDestination("home page"),
				new TestDestination("articles"),
				new TestDestination("articles.description"),
				new TestDestination("articles.description.no1"),
				new TestDestination("articles.description.no2"),
				new TestDestination("articles.description.no3")
			);
	}
	
	private static List<Role> roles() {
		return Arrays.asList(
				new TestRole("guest", 0),
				new TestRole("it-group", 10),
				new TestRole("managers", 20),
				new TestRole("owners", 30)
			);
	}
}
