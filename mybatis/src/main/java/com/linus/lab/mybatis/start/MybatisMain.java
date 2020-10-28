package com.linus.lab.mybatis.start;

import com.linus.lab.mybatis.mapper.BlogMapper;
import com.linus.lab.mybatis.pojo.Blog;
import com.linus.lab.mybatis.pojo.BlogTypeEnum;
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

        /**
         * mybatis配置解析
         * 1 user properties
         * 2 framework properties
         * 3 type alias
         * 4 plugin
         * 5 environment(transactionFactory & datasource
         * 6 type handler
         * 7 mapper
         *      package & class ----> mapper interface(configuration.addMapper)
         *      resource & url  ----> xml(XMLMapperBuilder)
         *          cache (二级缓存 装饰器模式) ->type,eviction(LRU),flushInterval,blocking
         *          @see CacheBuilder.setStandardDecorators()
         *          mapper
         *                   mapperedStatement->insert|update|delete|select
         */
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);//解析所有xml的配置文件

        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            // 你的应用逻辑代码
            Blog blog = mapper.selectBlog(1);

            System.out.println(blog);
            blog.setContent("content changed!");
            blog.setType(BlogTypeEnum.DIARY);
            mapper.updateBlog(blog);
            session.commit();

            blog = mapper.selectBlog(1);
            System.out.println(blog);
        }
    }
}
