package forum.kaoyan.qinghuagong;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//spring 是用容器管理bean的，为什么springboot可以做到呢。springboot有一个默认的方式，如果所有的带有注解的文件
//只要在application的同级或者下一级目录，都会自动加载进来。
@SpringBootApplication
@MapperScan("forum.kaoyan.qinghuagong.mapper")
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

}
