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

import java.util.List;

public class MybatisSpringTest {

    @Test
    public void simpleInsertTest() {
        Injector injector = Common.getInjector();
        Logger logger = injector.getInstance(Logger.class);
        UserMapper mapper = injector.getInstance(UserMapper.class);

        UserEntity entity1 = new UserEntity("aaa6", 21);
        UserEntity entity2 = new UserEntity("bbb6", 22);

        mapper.insertOne(entity1);
        mapper.insertOne(entity2);

        logger.debug(entity1.toString());
        logger.debug(entity2.toString());
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
}
