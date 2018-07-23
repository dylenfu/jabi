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
import org.ethereum.solidity.Abi;
import org.spongycastle.util.encoders.Hex;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.List;

/*
type SubmitRingMethodInputs struct {
	AddressList        [][4]common.Address `fieldName:"addressList" fieldId:"0"`   // owner,tokenS, wallet, authAddress
	UintArgsList       [][6]*big.Int       `fieldName:"uintArgsList" fieldId:"1"`  // amountS, amountB, validSince (second),validUntil (second), lrcFee, rateAmountS.
	Uint8ArgsList      [][1]uint8          `fieldName:"uint8ArgsList" fieldId:"2"` // marginSplitPercentageList
	BuyNoMoreThanBList []bool              `fieldName:"buyNoMoreThanAmountBList" fieldId:"3"`
	VList              []uint8             `fieldName:"vList" fieldId:"4"`
	RList              [][32]byte          `fieldName:"rList" fieldId:"5"`
	SList              [][32]byte          `fieldName:"sList" fieldId:"6"`
	FeeRecipient       common.Address      `fieldName:"feeRecipient" fieldId:"7"`
	FeeSelections      uint16              `fieldName:"feeSelections" fieldId:"8"`
	Protocol           common.Address
	FeeReceipt         common.Address
}
* */

public class SubmitRingMethod {

    @Inject
    Logger logger;

    @Inject
    @Named("implAbi")
    Abi abi;

    private Abi.Function method;

    private byte[] input;

    public void setInput(byte[] input) {
        this.input = input;
    }

    private byte[][] addressList;
    private BigInteger[][] uintArgsList;
    private BigInteger[][] uint8ArgsList;
    private boolean[] buyNoMoreThanBList;
    private int[] vList;
    private byte[][] rList;
    private byte[][] sList;
    private byte[] feeRecipient;
    private BigInteger feeSelections;

    public byte[][] getAddressList() {
        return addressList;
    }

    public BigInteger[][] getUintArgsList() {
        return uintArgsList;
    }

    public BigInteger[][] getUint8ArgsList() {
        return uint8ArgsList;
    }

    public boolean[] getBuyNoMoreThanBList() {
        return buyNoMoreThanBList;
    }

    public int[] getvList() {
        return vList;
    }

    public byte[][] getrList() {
        return rList;
    }

    public byte[][] getsList() {
        return sList;
    }

    public byte[] getFeeRecipient() {
        return feeRecipient;
    }

    public BigInteger getFeeSelections() {
        return feeSelections;
    }

    Predicate<Abi.Function> methodName = x -> x.name.equals("submitRing");

    private void beforeUnpack() throws Exception {
        if (this.method == null) {
            this.method = abi.findFunction(methodName);
        }
    }

    private void afterUnpack() throws Exception {

    }

    public void unpack() throws Exception {
        logger.debug("-------submit ring before unpack");
        beforeUnpack();
        logger.debug("-------submit ring after unpack");
        List list = method.decode(input);

        Object[] objects = (Object[])list.get(0);
        logger.debug("objects length:" + objects.length);
    }
}
