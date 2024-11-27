import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FallbackController {

    // 用正则匹配不包含 '.' 的路径，通常是 vue-router 中的路由
    @RequestMapping("/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/index.html";  // 转发到 index.html 页面
    }
}
