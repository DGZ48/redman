package xyz.dgz48.redman.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

// TODO ログインコントローラーという名前のコントローラーはいらないような気がする
// TODO 初めてのログインの場合はユーザ登録して云々はaspectであらゆるエンドポイントに対して適用できないかなという感覚
@RestController
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public Map<?, ?> login(Principal principal) {
        logger.debug("access user:{}", principal.getName());
        if (principal instanceof OAuth2AuthenticationToken) {
            final OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) principal;
            logger.debug("login user:{}", auth.getPrincipal().getAttributes());

            // TODO この値でDB参照。なければUserとして新規登録/あったら何もしない
            String sub = String.valueOf(auth.getPrincipal().getAttributes().get("sub"));
            // TODO ログイン後最初のページを開く（今はGoogleから取ってきたやつ出してるだけ）
            return auth.getPrincipal().getAttributes();
        }

        logger.debug("login fail user{}", principal.getName());
        return null;
    }
}
