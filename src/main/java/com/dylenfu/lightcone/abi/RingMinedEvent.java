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

import com.dylenfu.lightcone.solidity.Abi;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.commons.collections4.Predicate;
import org.spongycastle.util.encoders.Hex;

/**
 * event RingMined(
 *         uint            _ringIndex,
 *         bytes32 indexed _ringHash,
 *         address         _miner,
 *         address         _feeRecipient,
 *         bytes32[]       _orderInfoList
 *     );
 * */

public class RingMinedEvent {

    @Inject
    Logger logger;

    @Inject
    @Named("implAbi")
    Abi abi;

    private Abi.Event event;

    private byte[][] topics;
    private byte[] data;

    public void setTopics(byte[][] topics) {
        this.topics = topics;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private BigInteger ringIndex;
    private byte[]  ringhash;
    private byte[] miner;
    private byte[] feeRecipient;
    private List<byte[]> orderInfoList;

    public BigInteger getRingIndex() {
        return ringIndex;
    }

    public byte[] getRinghash() {
        return ringhash;
    }

    public byte[] getMiner() {
        return miner;
    }

    public byte[] getFeeRecipient() {
        return feeRecipient;
    }

    public List<byte[]> getOrderInfoList() {
        return orderInfoList;
    }

    Predicate<Abi.Event> eventName = x -> x.name.equals("RingMined");

    private void beforeUnpack() throws Exception {
        if (this.event == null) {
            this.event = abi.findEvent(eventName);
        }

        orderInfoList = new ArrayList<byte[]>();
    }

    private void afterUnpack() throws Exception {

    }

    public void unpack() throws Exception {
        beforeUnpack();

        List list = event.decode(data, topics);

        ringIndex = (BigInteger) list.get(0);
        ringhash = (byte[]) list.get(1);
        miner = (byte[]) list.get(2);
        feeRecipient = (byte[]) list. get(3);

        logger.debug("ringMined ringIndex " + ringIndex.toString());
        logger.debug("ringMined ringhash " + Hex.toHexString(ringhash));
        logger.debug("ringMined miner " + Hex.toHexString(miner));
        logger.debug("ringMined feeRecipient "  + Hex.toHexString(feeRecipient));

        Object[] sublist = (Object[])list.get(4);
        for (int i = 0; i < sublist.length; i++) {
            byte[] orderInfo = (byte[]) sublist[i];
            orderInfoList.add(orderInfo);
            logger.debug("ringMined orderInfo " + Hex.toHexString(orderInfo));
        }
    }
}
