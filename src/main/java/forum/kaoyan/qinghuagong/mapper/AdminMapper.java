package forum.kaoyan.qinghuagong.mapper;

import forum.kaoyan.qinghuagong.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {

    @Select("select id,name from user;")
    List<User> userlist();
}
