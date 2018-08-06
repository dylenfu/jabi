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

import com.dylenfu.lightcone.persistence.entity.UserEntity;
import com.dylenfu.lightcone.persistence.mapper.UserMapper;
import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class MybatisSpringTest {

    @Test
    public void simpleInsertTest() {
        Injector injector = Common.getInjector();
        Logger logger = injector.getInstance(Logger.class);
        UserMapper mapper = injector.getInstance(UserMapper.class);

        UserEntity entity1 = new UserEntity("tom", 21, "eth", "a5");
        UserEntity entity2 = new UserEntity("tom", 21, "eth", "b5");
        UserEntity entity3 = new UserEntity("red", 22, "lrc", "a2");
        UserEntity entity4 = new UserEntity("red", 22, "lrc", "b2");

        mapper.insertOne(entity1);
        mapper.insertOne(entity2);
        mapper.insertOne(entity3);
        mapper.insertOne(entity4);

        logger.debug(entity1.toString());
        logger.debug(entity2.toString());
        logger.debug(entity3.toString());
        logger.debug(entity4.toString());
    }

    @Test
    public void simpleSelectTest() {
        Injector injector = Common.getInjector();
        Logger logger = injector.getInstance(Logger.class);
        UserMapper mapper = injector.getInstance(UserMapper.class);

        List<UserEntity> list = mapper.selectByAge(3);
        for (UserEntity user: list) {
            logger.debug(user.toString());
        }
    }

    @Test
    public void selectByMarket() {
        Injector injector = Common.getInjector();
        Logger logger = injector.getInstance(Logger.class);
        UserMapper mapper = injector.getInstance(UserMapper.class);

        List<UserEntity> list = mapper.selectByMarket("lrc");
        assertEquals(list.size(), 2);
        for (UserEntity user: list) {
            logger.debug(user.toString());
        }
    }
}
