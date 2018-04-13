package xyz.dgz48.redman.domain.user;

/**
 * Fixture for UserEntity.
 */
public final class UserEntityFixture {

	/**
	 * Utility.
	 */
	private UserEntityFixture() {

	}

	/**
	 * Create user fixture.
	 * @return user
	 */
	public static UserEntity create() {
		return new UserEntity("userId", "idpUserName", "test@example.com", IdpType.GITHUB);
	}
}
