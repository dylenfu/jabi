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
package com.dylenfu.lightcone.abi;

import com.dylenfu.lightcone.config.NodeConfig;
import com.dylenfu.lightcone.config.StaticConfig;
import com.google.inject.*;
import com.google.inject.name.Names;
import org.apache.log4j.*;
import org.ethereum.solidity.Abi;
import org.junit.Test;
import org.spongycastle.util.encoders.Hex;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class TransferEventTest {

    @Test
    public void unpackTest() {

        Injector injector =  Guice.createInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                StaticConfig staticConfig = new StaticConfig("/Users/fukun/projects/javahome/github.com/dylenfu/lightcone/src/main/resources/local.conf");
                staticConfig.parse();
                NodeConfig nodeConfig = new NodeConfig();

                // load logger
                Logger logger = Logger.getLogger(TransferEventTest.class);
                PropertyConfigurator.configure("log4j.properties");
                binder.bind(Logger.class).toInstance(logger);

                // load config
                binder.bind(StaticConfig.class).toInstance(staticConfig);
                binder.bind(NodeConfig.class).toInstance(nodeConfig);

                // load erc20 abi
                String erc20AbiStr = staticConfig.config.getString("abi.erc20");
                Abi erc20Abi = Abi.fromJson(erc20AbiStr);
                binder.bind(Abi.class).annotatedWith(Names.named("erc20Abi")).toInstance(erc20Abi);
                binder.bind(TransferEvent.class).toInstance(new TransferEvent());
            }
        });

        byte[] data = Hex.decode("000000000000000000000000000000000000000000000000016345785d8a0000");
        byte[][] topics = {
                Hex.decode("ddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef"),
                Hex.decode("0000000000000000000000001b978a1d302335a6f2ebe4b8823b5e17c3c84135"),
                Hex.decode("000000000000000000000000b1018949b241d76a1ab2094f473e9befeabb5ead")};

        TransferEvent transfer = injector.getInstance(TransferEvent.class);
        transfer.setData(data);
        transfer.setTopics(topics);
        try {
            transfer.unpack();
        } catch (Exception e) {
            injector.getInstance(Logger.class).debug(e.getMessage());
        }

        assertEquals(Hex.toHexString((byte[])(transfer.getFrom())), "1b978a1d302335a6f2ebe4b8823b5e17c3c84135");
        assertEquals(Hex.toHexString((byte[])(transfer.getTo())), "b1018949b241d76a1ab2094f473e9befeabb5ead");
        assertEquals(transfer.getValue().toString(), "100000000000000000");
    }

}
