package com.linus.lab.redis.flash;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/8
 */
public class OrderDao {

    private DataSource dataSource;

    private Random random;

    public OrderDao () {
        //数据源配置
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.0.101:3306/test");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //这个可以缺省的，会根据url自动识别
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //下面都是可选的配置
        dataSource.setInitialSize(100);  //初始连接数，默认0
        dataSource.setMaxActive(100);  //最大连接数，默认8
        dataSource.setMinIdle(100);  //最小闲置数
        dataSource.setMaxWait(2000);  //获取连接的最大等待时间，单位毫秒
        dataSource.setPoolPreparedStatements(true); //缓存PreparedStatement，默认false
        dataSource.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码

        this.dataSource = dataSource;
        this.random = new Random();
    }


    public int insert(Integer productId, Integer userId) throws SQLException {

        //获取连接
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            randomError(userId);

            //Statement接口
            PreparedStatement statement = connection.prepareStatement("insert into flash_order (product_id, user_id) values (?,?)");
            statement.setInt(1, productId);
            statement.setInt(2, userId);
            int res = statement.executeUpdate();

            connection.commit();
            return res;
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void randomError(Integer userId) {
        if (random.nextInt(100) == 0) {
            System.out.println("random error:" + userId);
            System.out.println(1/0);
        }
    }




}
