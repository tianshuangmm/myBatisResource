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
        TUser user = mapper.selectByPrimaryKey(1);
        System.out.println(user.toString());

    }
}