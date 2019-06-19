package com.ts.dao;

import com.ts.bean.TUser;

public interface TUserMapper {

    TUser selectByPrimaryKey(Integer id);
}
