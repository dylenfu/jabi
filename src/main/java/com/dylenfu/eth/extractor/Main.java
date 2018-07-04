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
package com.dylenfu.eth.extractor;

import com.dylenfu.eth.abi.PersistenceModule;
import com.dylenfu.eth.abi.TransferEvent;
import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.google.inject.Guice;

public class Main {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class);
        PropertyConfigurator.configure("log4j.properties");

        Injector injector = Guice.createInjector(new PersistenceModule(logger));
        TransferEvent transfer = injector.getInstance(TransferEvent.class);

        transfer.unpack();
    }
}
