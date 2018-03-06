package xyz.dgz48.redman.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
