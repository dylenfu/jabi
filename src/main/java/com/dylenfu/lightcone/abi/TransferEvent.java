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

/* transfer event in erc20.sol
event Transfer(
    address indexed from,
    address indexed to,
    uint256 value
);
*/

public class TransferEvent {
    @Inject
    Logger logger;

    @Inject
    @Named("erc20Abi")
    Abi abi;

    private Abi.Event event;

    private byte[][] topics;
    private byte[] data;

    private byte[] from;
    private byte[] to;
    private BigInteger value;

    public void setTopics(byte[][] topics) {
        this.topics = topics;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getFrom() {
        return from;
    }

    public byte[] getTo() {
        return to;
    }

    public BigInteger getValue() {
        return value;
    }

    public void unpack() throws Exception {
        beforeUnpack();
        List list = event.decode(data, topics);

        this.from = (byte[])list.get(0);
        this.to = (byte[])list.get(1);
        this.value = (BigInteger) list.get(2);

        logger.debug("transfer event, from:" + Hex.toHexString((byte[])(this.from)) +
                " to:" + Hex.toHexString((byte[])(this.to)) +
                " value:" + this.value.toString());
    }

    Predicate<Abi.Event> eventName = x -> x.name.equals("Transfer");

    private void beforeUnpack() throws Exception {
        if (this.event == null) {
            this.event = abi.findEvent(eventName);
        }
    }

    private void afterUnpack() throws Exception {

    }
}
