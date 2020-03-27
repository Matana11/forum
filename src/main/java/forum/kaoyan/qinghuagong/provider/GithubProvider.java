package forum.kaoyan.qinghuagong.provider;

import com.alibaba.fastjson.JSON;
import forum.kaoyan.qinghuagong.dto.AccessTokenDTO;
import forum.kaoyan.qinghuagong.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

//controller是把当前类作为路由api的一个承载者
//component是仅仅把当前类初始化到spring容器的上下文，加了这个注解之后就不用了new..，再抽出一个变量。而是在调用时，不需要实例化一个对象，用另一个注解，直接就能把变量拿到，叫Ioc。
//ioc能把放了component注解，对象能自动实例化，放在一个池子里面，当我们用时，很轻松拿出名字来用。
@Component
public class GithubProvider {
    //先定义返回类型是string型，因为想要accesstoken。带的参数是accesstoken dto
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        //下面为从okhttp复制来的post请求，不要忘记引入jar包
        //因为当前项目是maven项目，导入maven包，在maven仓库找到okhttp包，ctrl+shift+n查找pom.xml，把dependency复制到dependencies里。
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string= response.body().string();
            String[] split = string.split("&");
            String token=split[0].split("=")[1];
            return token;
         } catch (Exception e) {
            e.printStackTrace();
        }return null;


    }
    public GithubUser getUser(String accessToken){
        //把hellohttp get a url的代码复制过来
        //创建一个client
        OkHttpClient client = new OkHttpClient();
        //构建一个request

        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            //拿到response，参照上文response.body().string()，就能拿到string对象
            //通过浏览器请求，知道了它是一个json的格式，直接用下载好的fastjson的包解析
            String string= response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);//可以把string自动转换成java的类对象
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();

        }

        return null;
    }
}
