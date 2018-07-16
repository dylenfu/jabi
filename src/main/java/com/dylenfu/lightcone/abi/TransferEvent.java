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

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.log4j.Logger;
import org.ethereum.solidity.Abi;

import java.math.BigInteger;
import java.util.List;
import org.apache.commons.collections4.Predicate;
import org.spongycastle.util.encoders.Hex;

public class TransferEvent {
    @Inject
    Logger logger;

    @Inject
    @Named("erc20Abi")
    Abi abi;

    // event in erc20.sol
    /*
    event Transfer(
        address indexed from,
        address indexed to,
        uint256 value
    );
    */

    private byte[][] topics;
    private byte[] data;

    public TransferEvent() {

    }
    
    public TransferEvent(Hex[] topics, Hex data) {
//        Hex.
//        for (Hex topic:topics) {
//            this.topics.
//        }
    }

    private String from;
    private String to;
    private BigInteger value;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigInteger getValue() {
        return value;
    }

    public void unpack() {
        //需要去除0x前缀
        byte[] content = Hex.decode("000000000000000000000000000000000000000000000000016345785d8a0000");
        byte[] topic1 = Hex.decode("ddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef");
        byte[] topic2 = Hex.decode("0000000000000000000000001b978a1d302335a6f2ebe4b8823b5e17c3c84135");
        byte[] topic3 = Hex.decode("000000000000000000000000b1018949b241d76a1ab2094f473e9befeabb5ead");

        byte[][] topics = {topic1, topic2, topic3};
        for (Abi.Entry entry:abi) {
            System.out.println("---------" + entry.name);
            System.out.println("---------" + entry.type.toString() + ","+ entry.formatSignature());
        }

        Abi.Event event  = abi.findEvent(eventName);

        logger.debug("----name-----" + event.name);
        logger.debug("----type-----" + event.type.toString());

        List list = event.decode(content, topics);

        this.from = Hex.toHexString((byte[])(list.get(0)));
        this.to = Hex.toHexString((byte[])(list.get(1)));
        this.value = (BigInteger) list.get(2);

        for(Object item:list) {
            if (item instanceof byte[]) {
                logger.debug(Hex.toHexString((byte[])(item)));
            } else if (item instanceof BigInteger) {
                logger.debug(item);
            } else {
                logger.debug("-------3");
            }
        }
        //this.from = list.get(0);

        //logger.debug(list.toArray().length);

//        logger.debug(list.get(0));
//        logger.debug(list.get(1));
//        logger.debug(list.get(2));

//        Entry entry = abi.get(0);
//        for (Entry.Param input : entry.inputs) {
//            logger.debug(input);
//            logger.debug("-----hahahahhah");
//        }
    }

    Predicate<Abi.Event> eventName = x -> x.name.equals("Transfer");
}
