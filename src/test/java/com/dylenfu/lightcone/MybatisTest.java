/*

  Copyright 2017 Loopring Project Ltd (Loopring Foundation).

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

*/

package com.dylenfu.lightcone;

import com.dylenfu.lightcone.persistence.User;
import com.dylenfu.lightcone.persistence.UserMapper;
import com.google.inject.Injector;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MybatisTest {

    @Test
    public void mapperTest() {
        Injector injector = Common.getInjector();
        SqlSessionFactory sqlSessionFactory = injector.getInstance(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // test case 2
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user1 = userMapper.getUserById(1);
            injector.getInstance(Logger.class).debug(user1.toString());

            User user2 = userMapper.getUserByName("tom");
            injector.getInstance(Logger.class).debug(user2.toString());
        } finally {
            sqlSession.close();
        }

    }
}
