package forum.kaoyan.qinghuagong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//注意首字母大写
//加注解后，就会自动识别扫描当前类，把它当做spring的一个并局管理，同时也识别它为controller，意思就是允许这个类
//接收前端的请求。
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name="name")String name, Model model){
        model.addAttribute("name",name);
        return "hello";//写完这句再去templates下写hello.html
    }
}
