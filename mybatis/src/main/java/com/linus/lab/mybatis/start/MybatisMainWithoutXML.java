package com.linus.lab.mybatis.start;

import com.linus.lab.mybatis.mapper.BlogMapper;
import com.linus.lab.mybatis.pojo.Blog;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/28
 */
public class MybatisMainWithoutXML {

    public static void main(String[] args) {
        DataSource dataSource = new UnpooledDataSource("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.0.101:3306/test", "root", "root");
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(BlogMapper.class);


        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            // 你的应用逻辑代码
            Blog blog = mapper.selectBlog(1);
            System.out.println(blog);
        }
    }
}
