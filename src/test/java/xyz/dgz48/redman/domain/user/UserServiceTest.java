package xyz.dgz48.redman.domain.user; // NOPMD

import static org.assertj.core.api.Assertions.assertThat; // NOPMD
import static org.mockito.ArgumentMatchers.any; // NOPMD
import static org.mockito.ArgumentMatchers.anyString; // NOPMD
import static org.mockito.Mockito.times; // NOPMD
import static org.mockito.Mockito.verify; // NOPMD
import static org.mockito.Mockito.when; // NOPMD

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


/**
 * Test for {@link UserService}.
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	/**
	 * Test target.
	 */
	@InjectMocks
	private UserService sut;

	/**
	 * {@link User} factory.
	 */
	@Spy
	private UserFactory userFactory;

	/**
	 * {@link UserEntity} factory.
	 */
	@Spy
	private UserEntityFactory userEntityFactory; // NOPMD

	/**
	 * Mock repository for {@User}.
	 */
	@Mock
	private UserRepository userRepository;

	/**
	 * {@link UserEntity} for test.
	 */
	private final UserEntity testUserEntity = new UserEntity("id", "name", "test@example.com", IdpType.GOOGLE);


	/**
	 * Test for findUserByIdpUserName.(Exist entity).
	 */
	@Test
	public void findUserByIdpUserNameExist() {
		// set up
		when(userRepository.findByIdpUserNameAndIdpType(anyString(), any())).thenReturn(Optional.of(testUserEntity));

		// exercise
		Optional<User> actual = sut.findUserByIdpUserName(testUserEntity.getIdpUserName(), testUserEntity.getIdpType());

		// verify
		assertThat(actual.get()).isEqualTo(userFactory.create(Optional.of(testUserEntity)).get());
	}

	/**
	 * Test for findUserByIdpUserName.(Not exist entity).
	 */
	@Test
	public void findUserByIdpUserNameNotExist() {
		// set up
		when(userRepository.findByIdpUserNameAndIdpType(anyString(), any())).thenReturn(Optional.empty());

		// exercise
		Optional<User> actual = sut.findUserByIdpUserName(testUserEntity.getIdpUserName(), testUserEntity.getIdpType());

		// verify
		assertThat(actual).isEmpty();
	}

	/**
	 * Test for upsertUser.(Not exist entity).
	 */
	@Test
	public void upsertUserInsert() {
		// set up
		when(userRepository.save(testUserEntity)).thenReturn(testUserEntity);

		// exercise
		User actual = sut.saveUser(userFactory.create(testUserEntity));

		// verify
		verify(userRepository, times(1)).save(testUserEntity);
		assertThat(actual).isEqualTo(userFactory.create(testUserEntity));
	}

	/**
	 * Test for findLoginUser.
	 */
	@Test
	public void findLoginUser() {
		// setup
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		OAuth2AuthenticationToken authentication = Mockito.mock(OAuth2AuthenticationToken.class);
		when(authentication.getAuthorizedClientRegistrationId()).thenReturn("github");
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("name");
		SecurityContextHolder.setContext(securityContext);
		UserEntity expected = UserEntityFixture.create();
		when(userRepository.findByIdpUserNameAndIdpType(anyString(), any())).thenReturn(Optional.of(expected));

		// exercise
		User actual = sut.findLoginUser();

		// verify
		assertThat(actual).isEqualTo(userFactory.create(expected));
	}
}
