package xyz.dgz48.redman.domain.user;

import javax.persistence.*;

/**
 * User.
 */
@Entity
public class User {

	/**
	 * このアプリケーション内に置けるUserの識別子.別のリソースとの外部参照のキーはこちらを使う.
	 */
	@Id
	@Column(name = "user_id")
	private String userId;

	/**
	 * Spring securityにとってのUserの識別子.
	 */
	@Column(unique = true, name = "idp_user_name")
	private String idpUserName;

	/**
	 * Idpの種別.
	 */
	@Column(name = "idp_type")
	@Enumerated(EnumType.STRING)
	private IdpType idpType;

	/**
	 * コンストラクタ.
	 * @param userId userId
	 * @param idpUserName idpUserName
	 * @param idpType idpType
	 *
	 */
	public User(final String userId, final String idpUserName, final IdpType idpType) {
		this.userId = userId;
		this.idpUserName = idpUserName;
		this.idpType = idpType;
	}

	/**
	 * Hibernateのためのデフォルトコンストラクタ.
	 */
	public User() {

	}
}