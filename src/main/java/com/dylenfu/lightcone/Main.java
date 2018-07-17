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
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.spongycastle.util.encoders.Hex;

public class Main {

    public static void main(String[] args) {
        StaticConfig staticConfig = new StaticConfig("/Users/fukun/projects/javahome/github.com/dylenfu/lightcone/src/main/resources/local.conf");
        staticConfig.parse();
        NodeConfig nodeConfig = new NodeConfig();

        Injector injector = Guice.createInjector(new MainModule(staticConfig, nodeConfig));

        injector.getInstance(StaticConfig.class).parse();
        //injector.getInstance(Deployer.class).deploy();

        byte[] data = Hex.decode("000000000000000000000000000000000000000000000000016345785d8a0000");
        byte[] topic1 = Hex.decode("ddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef");
        byte[] topic2 = Hex.decode("0000000000000000000000001b978a1d302335a6f2ebe4b8823b5e17c3c84135");
        byte[] topic3 = Hex.decode("000000000000000000000000b1018949b241d76a1ab2094f473e9befeabb5ead");
        byte[][] topics = {topic1, topic2, topic3};
        TransferEvent transfer = injector.getInstance(TransferEvent.class);
        transfer.setData(data);
        transfer.setTopics(topics);

        try {
            transfer.unpack();
        } catch (Exception e) {
            injector.getInstance(Logger.class).error(e);
        }
    }
}
