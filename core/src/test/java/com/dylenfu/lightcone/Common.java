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

import com.dylenfu.lightcone.config.NodeConfig;
import com.dylenfu.lightcone.config.StaticConfig;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Common {

    public static Injector getInjector() {
        StaticConfig staticConfig = new StaticConfig("/Users/fukun/projects/javahome/github.com/dylenfu/lightcone/core/src/main/resources/local.conf");
        staticConfig.parse();
        NodeConfig nodeConfig = new NodeConfig();

        Injector injector = Guice.createInjector(new MainModule(staticConfig, nodeConfig));

        injector.getInstance(StaticConfig.class).parse();

        return injector;
    }
}
