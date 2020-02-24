package forum.kaoyan.qinghuagong.controller;

import forum.kaoyan.qinghuagong.dto.PaginationDTO;
import forum.kaoyan.qinghuagong.dto.QuestionDTO;
import forum.kaoyan.qinghuagong.mapper.QuestionMapper;
import forum.kaoyan.qinghuagong.mapper.UserMapper;
import forum.kaoyan.qinghuagong.model.Question;
import forum.kaoyan.qinghuagong.model.User;
import forum.kaoyan.qinghuagong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Handler;

//注意首字母大写
//加注解后，就会自动识别扫描当前类，把它当做spring的一个并局管理，同时也识别它为controller，意思就是允许这个类
//接收前端的请求。
//一个反斜杠代表根目录，代表任何东西都不输入的时候，默认访问index
@Controller
public class IndexController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        PaginationDTO pagination = questionService.list(page,size);

        model.addAttribute("pagination", pagination);

        return "index";

    }
}
