package xyz.dgz48.redman.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * {@link Controller} for Resource list page.
 *
 * @author win2cot
 *
 */
@Controller
public class ResourceListController {

	/**
	 * リソース一覧画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/resource/list.html")
	public String viewList(final Model model) {
		return "/resource/list";
	}
}
