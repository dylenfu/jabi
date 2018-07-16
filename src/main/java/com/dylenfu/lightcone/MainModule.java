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

import com.dylenfu.lightcone.abi.TransferEvent;
import com.dylenfu.lightcone.config.NodeConfig;
import com.dylenfu.lightcone.config.StaticConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.typesafe.config.*;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.ethereum.solidity.Abi;

import javax.sql.DataSource;
import java.io.*;

public class MainModule extends AbstractModule {

    private StaticConfig staticConfig;
    private NodeConfig nodeConfig;

    public MainModule(StaticConfig staticConfig, NodeConfig nodeConfig) {
        this.staticConfig = staticConfig;
        this.nodeConfig = nodeConfig;
    }

    @Override
    protected void configure() {
        // load logger
        Logger logger = Logger.getLogger(MainModule.class);
        PropertyConfigurator.configure("log4j.properties");
        bind(Logger.class).toInstance(logger);

        // load config
        bind(StaticConfig.class).toInstance(staticConfig);
        bind(NodeConfig.class).toInstance(nodeConfig);

        // load erc20 abi
        String erc20AbiStr = staticConfig.config.getString("abi.erc20");
        Abi erc20Abi = Abi.fromJson(erc20AbiStr);
        bind(Abi.class).annotatedWith(Names.named("erc20Abi")).toInstance(erc20Abi);
        bind(TransferEvent.class).toInstance(new TransferEvent());

        // load loopring abi
//        Abi implAbi = Abi.fromJson(implAbiStr);
//        bind(Abi.class).annotatedWith(Names.named("implAbi")).toInstance(implAbi);

        // load deployer
        //bind(Deployer.class).toInstance(new Deployer());

        // load mysql
        // DataSource dataSource = new PooledDataSource(staticConfig.config.getString("db.driver"), staticConfig.config.getString("db.url"), staticConfig.config.getString("db.username"), staticConfig.config.getString("db.password"));
    }
}
