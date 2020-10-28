package com.linus.lab.mybatis.mapper;

import com.linus.lab.mybatis.pojo.Blog;
import org.apache.ibatis.annotations.Select;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/23
 */
public interface BlogMapper {

//    @Select("SELECT * FROM blog WHERE id = #{id}")
    Blog selectBlog(int id);


    void updateBlog(Blog blog);
}

