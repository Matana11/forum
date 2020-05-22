package forum.kaoyan.qinghuagong.mapper;

import forum.kaoyan.qinghuagong.model.Question;
import forum.kaoyan.qinghuagong.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
}