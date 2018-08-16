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

    private List<List<byte[]>> addressList;
    private List<BigInteger[]> uintArgsList;
    private List<BigInteger[]> uint8ArgsList;
    private boolean[] buyNoMoreThanBList;
    private List<BigInteger> vList;
    private List<byte[]> rList;
    private List<byte[]> sList;
    private byte[] feeRecipient;
    private BigInteger feeSelections;

    public List<List<byte[]>> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<List<byte[]>> addressList) {
        this.addressList = addressList;
    }

    public List<BigInteger[]> getUintArgsList() {
        return uintArgsList;
    }

    public void setUintArgsList(List<BigInteger[]> uintArgsList) {
        this.uintArgsList = uintArgsList;
    }

    public List<BigInteger[]> getUint8ArgsList() {
        return uint8ArgsList;
    }

    public void setUint8ArgsList(List<BigInteger[]> uint8ArgsList) {
        this.uint8ArgsList = uint8ArgsList;
    }

    public boolean[] getBuyNoMoreThanBList() {
        return buyNoMoreThanBList;
    }

    public void setBuyNoMoreThanBList(boolean[] buyNoMoreThanBList) {
        this.buyNoMoreThanBList = buyNoMoreThanBList;
    }

    public List<BigInteger> getvList() {
        return vList;
    }

    public void setvList(List<BigInteger> vList) {
        this.vList = vList;
    }

    public List<byte[]> getrList() {
        return rList;
    }

    public void setrList(List<byte[]> rList) {
        this.rList = rList;
    }

    public List<byte[]> getsList() {
        return sList;
    }

    public void setsList(List<byte[]> sList) {
        this.sList = sList;
    }

    public byte[] getFeeRecipient() {
        return feeRecipient;
    }

    public void setFeeRecipient(byte[] feeRecipient) {
        this.feeRecipient = feeRecipient;
    }

    public BigInteger getFeeSelections() {
        return feeSelections;
    }

    public void setFeeSelections(BigInteger feeSelections) {
        this.feeSelections = feeSelections;
    }

    Predicate<Abi.Function> methodName = x -> x.name.equals("submitRing");

    private void beforeUnpack() throws Exception {
        if (this.method == null) {
            this.method = abi.findFunction(methodName);
        }

        addressList = new ArrayList<List<byte[]>>();
        uintArgsList = new ArrayList<BigInteger[]>();
        uint8ArgsList = new ArrayList<BigInteger[]>();
        buyNoMoreThanBList = new boolean[2];
        vList = new ArrayList<BigInteger>();
        rList = new ArrayList<byte[]>();
        sList = new ArrayList<byte[]>();
    }

    private void afterUnpack() throws Exception {

    }

    public void unpack() throws Exception {
        beforeUnpack();
        List list = method.decode(input);

        logger.debug("submit ring method objects length:" + list.toArray().length);

        for (Object arr: (Object[]) list.get(0)) {
            List subAddressList = new ArrayList<byte[]>();
            for (Object object: (Object[]) arr) {
                byte[] address = (byte[])object;
                subAddressList.add(address);
                logger.debug("submitRing address " + Hex.toHexString(address));
            }
            addressList.add(subAddressList);
        }

        for (Object arr: (Object[]) list.get(1)) {
            BigInteger[] numbers = new BigInteger[6];
            Object[] sublist = (Object[]) arr;
            for (int i = 0; i < sublist.length; i++) {
                numbers[i] = (BigInteger) sublist[i];
                logger.debug("submitRing uintArg " + numbers[i].toString());
            }
            uintArgsList.add(numbers);
        }

        for (Object arr: (Object[]) list.get(2)) {
            Object[] sublist = (Object[]) arr;
            BigInteger[] numbers = {(BigInteger) sublist[0]};
            logger.debug("submitRing uint8Arg " + numbers[0]);
            uint8ArgsList.add(numbers);
        }

        Object[] buyNoMoreThanBConvertList = (Object[]) list.get(3);
        for (int i = 0; i < buyNoMoreThanBConvertList.length; i++) {
            buyNoMoreThanBList[i] = (boolean) buyNoMoreThanBConvertList[i];
            logger.debug("submitRing buyNoMoreThanB " + buyNoMoreThanBList[i]);
        }

        Object[] vConvertList = (Object[]) list.get(4);
        for (int i = 0; i < vConvertList.length; i++) {
            vList.add((BigInteger) vConvertList[i]);
            logger.debug("submitRing v " + vList.get(i).toString());
        }

        Object[] rConvertList = (Object[]) list.get(5);
        for (int i = 0; i < rConvertList.length; i++) {
            rList.add((byte[]) rConvertList[i]);
            logger.debug("submitRing r " + Hex.toHexString(rList.get(i)));
        }

        Object[] sConvertList = (Object[]) list.get(6);
        for (int i = 0; i < sConvertList.length; i++) {
            sList.add((byte[]) sConvertList[i]);
            logger.debug("submitRing s " + Hex.toHexString(sList.get(i)));
        }

        feeRecipient = (byte[]) list.get(7);
        logger.debug("submitRing feeRecipient " + Hex.toHexString(feeRecipient));

        feeSelections = (BigInteger) list.get(8);
        logger.debug("submitRing feeSelections " + feeSelections.toString());
    }

    public byte[] pack() {
        return method.encode(addressList, uintArgsList, uint8ArgsList, buyNoMoreThanBList, vList, rList, sList, feeRecipient, feeSelections);
    }
}
