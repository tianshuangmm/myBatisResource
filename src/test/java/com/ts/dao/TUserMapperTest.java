package com.ts.dao;

import com.ts.bean.TUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;

public class TUserMapperTest {
    @Test
// 快速入门
    public void quickStart() throws IOException {
        //-------------第一阶段-------------
        // 1.读取mybatis配置文件创SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
        //-------------第二阶段-------------
        // 2.获取sqlSession
        SqlSession sqlSession = build.openSession();
        // 3.获取对应mapper
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

        //-------------第三阶段-------------
        // 4.执行查询语句并返回结果

        /*
        * mybatis 的两种编程模式
        * 1、使用mapper接口编程，就可以访问数据库
        * 2、封装了iBatis编程模型，使用SQLSessionFactory对外提供数据库的访问
        * 比如  使用sqlSession直接访问数据库，代码如：
        *    User user1 = sqlSession.selectOne("com.ts.dao.TUserMapper.selectByPrimaryKey",2);
        * */
        TUser user = mapper.selectByPrimaryKey(1);
        System.out.println(user.toString());


        TUser user1 = sqlSession.selectOne("com.ts.dao.TUserMapper.selectByPrimaryKey",2);
        System.out.println(user.toString());
    }

    /*
    * Mybatis核心流程三大阶段
    *    初始化阶段     读取XML配置文件和注解中的配置信息，创建配置对象，并完成各个模块的初始化的工作
    *    代理阶段       封装iBatis的编程模型，使用mapper接口开发的初始化工作
    *    数据库读写阶段  通过sqlSession完成sql的解析，参数的映射、sql的执行、结果的解析过程
    *  细流程
    *    1、创建sqlSessionFactory实例
    *    2、实例化过程中、加载配置文件创建configuration对象
    *    3、通过factory穿件sqlSession对象,把configuration传入sqlSession
    *    4、通过SqlSession获取mapper接口动态代理
    *    5、通过代理对调sqlSession中查询方法
    *    6、sqlSession将查询方法转发给executor
    *    7、executor基于JDBC访问数据库获取数据
    *    8、executor通过反射将数据转换成POJO并返回给sqlSession
    *    9、数据返回给调用者
    * */
}