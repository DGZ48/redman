package xyz.dgz48.redman.domain.user;

/**
 * Fixture for User.
 */
public final class UserFixture {

	/**
	 * Utility.
	 */
	private UserFixture() {

	}

	/**
	 * Create user fixture.
	 * @return user
	 */
	public static User create() {
		return new User("userId", "idpUserName", "test@example.com", IdpType.GITHUB);
	}
}
