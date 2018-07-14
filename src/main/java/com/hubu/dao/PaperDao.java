package com.hubu.dao;

import com.hubu.pojo.Paper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaperDao {
    String TABLE_NAME = " paper ";
    String INSERT_FIELDS = " title,lessonId,questionIds,keys,creater ";
    String SELECT_FIELDS = " title,lessonId,questionIds,keys,creater ";

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,") values title = #{title},lessonId = #{lessonId},questionIds = #{questionIds},keys = #{keys},creater = #{creater}"})
    Integer insertPaperByMan(Paper paper);

    @Delete({"delete from",TABLE_NAME,"where paperId = #{paperId}"})
    Integer deletePaper(Integer paperId);

    @Update({"update",TABLE_NAME,"set title=#{title},lessonId=#{lessonId},questionIds=#{questionIds},keys=#{keys},creater=#{creater} where paperId=#{paperId}"})
    Integer updatePaper(Paper paper);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME," order by paperId desc limit #{currentPage},#{pageCount}"})
    @Results({
            @Result(id = true,column = "paperId",property = "paperID"),
            @Result(column = "title",property = "title"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "questionIds",property = "questionIds"),
            @Result(column = "keys",property = "keys"),
            @Result(column = "creater",property = "creater")
    })
    List<Paper> selectPagePaper(int currentPage, int pageCount);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME," where title like %${keyword}% order by paperId desc limit #{currentPage},#{pageCount}"})
    @Results({
            @Result(id = true,column = "paperId",property = "paperID"),
            @Result(column = "title",property = "title"),
            @Result(column = "lessonId",property = "lesson",
                    one = @One(select = "com.hubu.dao.LessonDAO.selectLessonIdByName")
            ),
            @Result(column = "questionIds",property = "questionIds"),
            @Result(column = "keys",property = "keys"),
            @Result(column = "creater",property = "creater")
    })
    List<Paper> selectPagePaperByKeyWord(int currentPage, int pageCount, String keyword);
}

