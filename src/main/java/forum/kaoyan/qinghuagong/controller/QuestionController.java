package forum.kaoyan.qinghuagong.controller;

import forum.kaoyan.qinghuagong.dto.QuestionDTO;
import forum.kaoyan.qinghuagong.mapper.QuestionMapper;
import forum.kaoyan.qinghuagong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired(required = true)
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id,
    Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";//希望跳转到question页面
    }

}
