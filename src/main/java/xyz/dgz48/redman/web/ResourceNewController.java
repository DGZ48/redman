package xyz.dgz48.redman.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * {@link Controller} for Resource create flow.
 *
 * @author win2cot
 *
 */
@Controller
@RequestMapping("/resource/new")
public class ResourceNewController {

	/**
	 * リソース登録入力画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/input.html")
	public String viewInput(final Model model) {
		return "/resource/new/input";
	}

	/**
	 * リソース登録入力画面の入力値を検証する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@RequestMapping(path = "/input.html", method = RequestMethod.POST)
	public String validate(final Model model) {
		return "redirect:/resource/new/confirm.html";
	}

	/**
	 * リソース登録確認画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@RequestMapping(path = "/confirm.html", method = RequestMethod.GET)
	public String confirm(final Model model) {
		return "/resource/new/confirm";
	}

	/**
	 * リソースを登録する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@RequestMapping(path = "/confirm.html", method = RequestMethod.POST)
	public String register(final Model model) {
		return "redirect:/resource/new/complete.html";
	}

	/**
	 * リソース登録完了画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@RequestMapping(path = "/complete.html", method = RequestMethod.GET)
	public String complete(final Model model) {
		return "/resource/new/complete";
	}
}
