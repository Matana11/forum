package forum.kaoyan.qinghuagong.controller;

import forum.kaoyan.qinghuagong.dto.AccessTokenDTO;
import forum.kaoyan.qinghuagong.dto.GithubUser;
import forum.kaoyan.qinghuagong.mapper.UserMapper;
import forum.kaoyan.qinghuagong.model.User;
import forum.kaoyan.qinghuagong.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

//写一个方法，返回string因为要返回到一个页面去
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;//直接在这里面定义，然后写一个注解，就会自动把刚才实例化好的东西放里面。

    @Autowired(required = false)
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        //怎么样接收参数呢，就是requestParameter
        //requestparameter不知道什么参数就摁ctrl+p,看提示
        //state需要接收吗，文档中下文调access_token时，还有一个state，这个state陈述说是random string，第一步提供的。所以要再填一个参数
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);//光标选中new AccessTokenDTO()，快捷键ctrl+alt+v，把变量快速提取到外面
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);//token用UUID设置
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));//id是long类型的，accountId是string类型，强制转换String.valueof
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
           //
            return "redirect:/";
        }else{
            //登录失败，重新登录
            return "redirect:/";
        }


    }
}
