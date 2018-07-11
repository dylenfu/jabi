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

public class MainModule extends AbstractModule {

    private StaticConfig staticConfig;
    private NodeConfig nodeConfig;

    private final String protocolStr = "0x781870080C8C24a2FD6882296c49c837b06A65E6";
    private final String delegateStr = "0xC533531f4f291F036513f7Abb41bfcCc62475486";

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

        // load mysql
        DataSource dataSource = new PooledDataSource();

        // load erc20 abi
//        Abi erc20Abi = Abi.fromJson(erc20AbiStr);
//        bind(Abi.class).annotatedWith(Names.named("erc20Abi")).toInstance(erc20Abi);

        // load loopring abi
//        Abi implAbi = Abi.fromJson(implAbiStr);
//        bind(Abi.class).annotatedWith(Names.named("implAbi")).toInstance(implAbi);

        //bind(TransferEvent.class).toInstance(new TransferEvent());

        // load deployer
        bind(Deployer.class).toInstance(new Deployer());
    }
}
