package forum.kaoyan.qinghuagong.controller;

import forum.kaoyan.qinghuagong.mapper.UserMapper;
import forum.kaoyan.qinghuagong.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Handler;

//注意首字母大写
//加注解后，就会自动识别扫描当前类，把它当做spring的一个并局管理，同时也识别它为controller，意思就是允许这个类
//接收前端的请求。
//一个反斜杠代表根目录，代表任何东西都不输入的时候，默认访问index
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user=userMapper.findByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }


        return "index";

    }
}
