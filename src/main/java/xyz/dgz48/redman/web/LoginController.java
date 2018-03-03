package xyz.dgz48.redman.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controller for login
 */
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 認証後に表示するページ。最終的にリソースの一覧か何かになると思う
     *
     * @param authentication
     * @return user info
     */
    @GetMapping("/")
    public Map<String, Object> index(OAuth2AuthenticationToken authentication) throws JsonProcessingException {
        return authentication.getPrincipal().getAttributes();
    }

    /**
     * ログイン画面表示
     *
     * @return login page
     */
    @GetMapping("/login")
    public String viewLogin() {
        return "forward:/index.html";
    }
}
