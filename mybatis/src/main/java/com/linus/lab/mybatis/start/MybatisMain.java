package com.linus.lab.mybatis.start;

import com.linus.lab.mybatis.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/21
 */
public class MybatisMain {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.selectOne("");
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            // 你的应用逻辑代码
            mapper.selectBlog(1);
        }
    }
}
