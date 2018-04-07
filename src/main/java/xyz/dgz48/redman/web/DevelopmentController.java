package xyz.dgz48.redman.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 開発者目的の{@link Controller}.
 *
 * @author win2cot
 *
 */
@Controller
@Profile("debug")
@Slf4j
public class DevelopmentController {

	/**
	 * path:/starter.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/starter.html")
	public String viewStarter(final Model model) {
		log.debug("View /starter.html");
		return "starter";
	}

	/**
	 * path:/example/bootstrap.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/example/bootstrap.html")
	public String viewExampleBootsrap(final Model model) {
		log.debug("View /example/bootstrap.html");
		return "example/bootstrap";
	}

	/**
	 * path:/example/bootstrap-honoka.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/example/bootstrap-honoka.html")
	public String viewExampleBootsrapHonoka(final Model model) {
		log.debug("View /example/bootstrap-honoka.html");
		return "example/bootstrap-honoka";
	}

	/**
	 * path:/example/bootstrap-honoka2.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/example/bootstrap-honoka2.html")
	public String viewExampleBootsrapHonoka2(final Model model) {
		log.debug("View /example/bootstrap-honoka2.html");
		return "example/bootstrap-honoka2";
	}

	/**
	 * path:/example/bootstrap-googlewebfont.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/example/bootstrap-googlewebfont.html")
	public String viewExampleBootsrapGoogleWebFont(final Model model) {
		log.debug("View /example/bootstrap-googlewebfont.html");
		return "example/bootstrap-googlewebfont";
	}

}
