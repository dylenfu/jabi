package com.dylenfu.lightcone;/*

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

import com.dylenfu.lightcone.persistence.entity.UserEntity;
import com.dylenfu.lightcone.persistence.mapper.UserEntityMapper;
import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.junit.Test;

public class MybatisSpringTest {

    @Test
    public void simpleTest() {
        Injector injector = Common.getInjector();
        Logger logger = injector.getInstance(Logger.class);
        UserEntityMapper mapper = injector.getInstance(UserEntityMapper.class);

        mapper.insertOne(new UserEntity(1, "xx", 11));
        mapper.insertOne(new UserEntity(2, "xxzzz", 11));
        logger.debug(mapper.selectByPk(1));
        logger.debug(mapper.selectByPk(2));
    }
}
