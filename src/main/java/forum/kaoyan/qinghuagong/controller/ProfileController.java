package forum.kaoyan.qinghuagong.controller;

import forum.kaoyan.qinghuagong.dto.PaginationDTO;
import forum.kaoyan.qinghuagong.mapper.UserMapper;
import forum.kaoyan.qinghuagong.model.User;
import forum.kaoyan.qinghuagong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name="action") String action, //pathVariable别的参数什么也没填，也默认跳转一个路径
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "2") Integer size){

        User user=(User) request.getSession().getAttribute("user");

        if(user==null){
            return "redirect/";
        }
        if("questions".equals(action)){//这样可以防止空指针异常
        //好像懂了一点了，个人详情页右边选择我的问题，左面的标题就是我的问题。右边选我关注的..，左边标签就是我关注的。
        //所以这里和index一样，需要引入model
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);

        model.addAttribute("pagination", paginationDTO);

        return "profile";
    }
}
