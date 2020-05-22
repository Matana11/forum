package forum.kaoyan.qinghuagong.controller;

import forum.kaoyan.qinghuagong.mapper.AdminMapper;
import forum.kaoyan.qinghuagong.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminLoginController {


    @GetMapping("/adminlogin")
    public String adminLogin(){
        return "adminlogin";
    }

}
