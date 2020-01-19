package forum.kaoyan.qinghuagong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//注意首字母大写
//加注解后，就会自动识别扫描当前类，把它当做spring的一个并局管理，同时也识别它为controller，意思就是允许这个类
//接收前端的请求。
//一个反斜杠代表根目录，代表任何东西都不输入的时候，默认访问index
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){return "index";}
}
