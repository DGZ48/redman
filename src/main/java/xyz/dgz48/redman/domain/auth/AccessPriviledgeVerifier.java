package xyz.dgz48.redman.domain.auth;

import java.util.Optional;

import org.springframework.stereotype.Component;
import xyz.dgz48.redman.domain.user.Ownable;

/**
 * あるユーザにひもづくインスタンスがそのユーザのものかどうか判定するクラス.
 */
@Component
public class AccessPriviledgeVerifier {

	/**
	 * 入力されたユーザとオブジェクトの保持者が別の場合例外をスローする.
	 * @param userId userId
	 * @param object verify target
	 * @throws AccessDeniedException オブジェクトの保持者とログインしているユーザが異なる場合
	 */
	public void verifyAccessPrivilege(final String userId, final Ownable object) throws AccessDeniedException {
		if (!userId.equals(object.getUserId())) {
			throw new AccessDeniedException(createMessage(object.getUserId(), userId));
		}
	}

	/**
	 * 入力されたユーザとオブジェクトの保持者が別の場合例外をスローする.
	 * @param userId userId
	 * @param object verify target
	 * @throws AccessDeniedException オブジェクトの保持者とログインしているユーザが異なる場合
	 */
	public void verifyAccessPrivilege(final String userId, final Optional<? extends Ownable> object) {
		object.ifPresent(o -> verifyAccessPrivilege(userId, o));
	}

	/**
	 * 入力されたユーザとオブジェクトの保持者とログインしているユーザが別の場合例外をスローする.
	 * @param userId userId
	 * @param objects objects
	 * @throws AccessDeniedException オブジェクトの保持者とログインしているユーザが異なる場合
	 */
	public void verifyAccessPrivilege(final String userId, final Iterable<? extends Ownable> objects) {
		objects.forEach(o -> verifyAccessPrivilege(userId, o));
	}

	/**
	 * ユーザが対象のオブジェクトを参照できない場合の例外メッセージを返却する.
	 * @param ownerId ownerId
	 * @param objectId objectId
	 * @return message
	 */
	private String createMessage(final String ownerId, final String objectId) {
		return String.format("Object by User:%s can not access by User:%s", ownerId, objectId);
	}
}
