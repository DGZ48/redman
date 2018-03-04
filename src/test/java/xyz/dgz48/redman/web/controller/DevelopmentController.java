package xyz.dgz48.redman.web.controller;

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
public class DevelopmentController {

	/**
	 * path:/starter.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/starter.html")
	public String viewStarter(final Model model) {
		return "/starter";
	}

	/**
	 * path:/login.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/login.html")
	public String viewLoginForm(final Model model) {
		return "/login";
	}
}
