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
import org.apache.commons.collections4.Predicate;
import org.apache.log4j.Logger;
import com.dylenfu.lightcone.solidity.Abi;
import org.spongycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
* function cancelOrder(
        address[5] addresses,
        uint[6]    orderValues,
        bool       buyNoMoreThanAmountB,
        uint8      marginSplitPercentage,
        uint8      v,
        bytes32    r,
        bytes32    s
        )
* */

public class CancelOrderMethod {

    @Inject
    Logger logger;

    @Inject
    @Named("implAbi")
    Abi abi;

    private byte[] input;
    private Abi.Function method;

    public void setInput(byte[] input) {
        this.input = input;
    }

    private List<byte[]> addresses;
    private List<BigInteger> orderValues;
    private boolean buyNoMoreThanAmountB;
    private BigInteger marginSplitPercentage;
    private BigInteger v;
    private byte[] r;
    private byte[] s;

    public List<byte[]> getAddresses() {
        return addresses;
    }

    public List<BigInteger> getOrderValues() {
        return orderValues;
    }

    public boolean isBuyNoMoreThanAmountB() {
        return buyNoMoreThanAmountB;
    }

    public BigInteger getMarginSplitPercentage() {
        return marginSplitPercentage;
    }

    public BigInteger getV() {
        return v;
    }

    public byte[] getR() {
        return r;
    }

    public byte[] getS() {
        return s;
    }

    Predicate<Abi.Function> methodName = x -> x.name.equals("cancelOrder");

    private void beforeUnpack() throws Exception {
        if (this.method == null) {
            this.method = abi.findFunction(methodName);
        }
        addresses = new ArrayList<byte[]>();
        orderValues = new ArrayList<BigInteger>();
    }

    private void afterUnpack() throws Exception {

    }

    public void unpack() throws Exception {
        beforeUnpack();
        List list = method.decode(input);

        for (Object object: (Object[]) list.get(0)) {
            this.addresses.add((byte[])object);
            logger.debug("cancel order address " + Hex.toHexString(addresses.get(this.addresses.toArray().length - 1)));
        }

        for (Object object: (Object[]) list.get(1)) {
            orderValues.add((BigInteger)object);
            logger.debug("cancel order value" + orderValues.get(this.orderValues.toArray().length - 1).toString());
        }

        buyNoMoreThanAmountB = (boolean) list.get(2);
        logger.debug("cancel order buyNoMore " + buyNoMoreThanAmountB);

        marginSplitPercentage = (BigInteger) list.get(3);
        logger.debug("cancel order marginSplitPercentage " + marginSplitPercentage.toString());

        v = (BigInteger) list.get(4);
        logger.debug("cancel order v " + v.toString());

        r = (byte[]) list.get(5);
        logger.debug("cancel order r " + Hex.toHexString(r));

        s = (byte[]) list.get(6);
        logger.debug("cancel order s " + Hex.toHexString(s));
    }
}
