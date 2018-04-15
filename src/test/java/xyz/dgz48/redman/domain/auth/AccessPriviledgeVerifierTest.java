package xyz.dgz48.redman.domain.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.dgz48.redman.domain.user.Ownable;

/**
 * Test for {@link AccessPriviledgeVerifier}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AccessPriviledgeVerifierTest {

	/**
	 * Test target.
	 */
	@InjectMocks
	private AccessPriviledgeVerifier sut;

	/**
	 * UserId for Test.
	 */
	private static final String USER_ID1 = "userId1";

	/**
	 * OWNABLE for Test.
	 */
	private static final Ownable OWNABLE1 = new Ownable() {
		@Override
		public String getUserId() {
			return "userId1";
		}
	};

	/**
	 * OWNABLE for Test.
	 */
	private static final Ownable OWNABLE2 = new Ownable() {
		@Override
		public String getUserId() {
			return "userId2";
		}
	};

	/**
	 * Test for verifyAccessPrivilege.
	 * @throws Exception exception
	 */
	@Test
	public void verifyAccessPrivilege() {
		// exercise
		sut.verifyAccessPrivilege(USER_ID1, OWNABLE1);
	}

	/**
	 * Test for verifyAccessPrivilege(Denied).
	 * @throws Exception exception
	 */
	@Test(expected = AccessDeniedException.class)
	public void verifyAccessPrivilegeAccessDenied() {
		// exercise
		sut.verifyAccessPrivilege(USER_ID1, OWNABLE2);
	}

	/**
	 * Test for verifyAccessPrivilege for Optional.
	 * @throws Exception exception
	 */
	@Test
	public void verifyAccessPrivilegeOptional() {
		// exercise
		sut.verifyAccessPrivilege(USER_ID1, Optional.of(OWNABLE1));
	}


	/**
	 * Test for verifyAccessPrivilege for Optional(Denied).
	 * @throws Exception exception
	 */
	@Test(expected = AccessDeniedException.class)
	public void verifyAccessPrivilegeOptionalAccessDenied() {
		// exercise
		sut.verifyAccessPrivilege(USER_ID1, Optional.of(OWNABLE2));
	}

	/**
	 * Test for verifyAccessPrivilege for Optional.empty.
	 * @throws Exception exception
	 */
	@Test
	public void verifyAccessPrivilegeOptionalEmpty() {
		// exercise
		sut.verifyAccessPrivilege(USER_ID1, Optional.empty());
	}

	/**
	 * Test for verifyAccessPrivilege for Iterable.
	 * @throws Exception exception
	 */
	@Test
	public void verifyAccessPrivilegeIterable() {
		// setup
		List<Ownable> list = new ArrayList<>();
		list.add(OWNABLE1);
		list.add(OWNABLE1);

		// actual
		sut.verifyAccessPrivilege(USER_ID1, list);
	}

	/**
	 * Test for verifyAccessPrivilege for Iterable(Denied).
	 * @throws Exception exception
	 */
	@Test(expected = AccessDeniedException.class)
	public void verifyAccessPrivilegeIterableAccessDenied() {
		// setup
		List<Ownable> list = new ArrayList<>();
		list.add(OWNABLE1);
		list.add(OWNABLE2);

		// actual
		sut.verifyAccessPrivilege(USER_ID1, list);
	}
}
