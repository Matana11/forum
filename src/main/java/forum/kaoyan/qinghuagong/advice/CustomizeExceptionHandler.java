package forum.kaoyan.qinghuagong.advice;

import forum.kaoyan.qinghuagong.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)//把YourException改成Exception,因为目前还没写Exception
    //把@ResponseBody去掉，因为不希望返回json，希望返回ModelAndView
    //什么是ModelAndView呢？就是index.html返回"index" ，就是一个modelAndView，手动写也是返回一个渲染过的页面
    ModelAndView handle(Throwable e, Model model) {//希望出现错误之后，能跳转到我们写好的页面
        if(e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else{
            model.addAttribute("message","服务冒烟了，要不你稍后再试试！！");
        }
        return new ModelAndView("error");//希望能回到我们定义的error界面
    }

}
