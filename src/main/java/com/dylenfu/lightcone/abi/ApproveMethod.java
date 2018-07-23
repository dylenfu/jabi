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

/**
** function approve(
 *         address spender,
 *         uint256 value
 *         )
 *         public
 *         returns (bool);
 *
 */
public class ApproveMethod {

    @Inject
    Logger logger;

    @Inject
    @Named("erc20Abi")
    Abi abi;

    private Abi.Function method;

    private byte[] input;

    public void setInput(byte[] input) {
        this.input = input;
    }

    private byte[] spender;
    private BigInteger value;

    public byte[] getSpender() {
        return spender;
    }

    public BigInteger getValue() {
        return value;
    }

    Predicate<Abi.Function> methodName = x -> x.name.equals("approve");

    private void beforeUnpack() throws Exception {
        if (this.method == null) {
            this.method = abi.findFunction(methodName);
        }
    }

    private void afterUnpack() throws Exception {

    }

    public void unpack() throws Exception {
        beforeUnpack();
        List list = method.decode(input);
        logger.debug("approve unpack list lenght:" + list.toArray().length);

        this.spender = (byte[])list.get(0);
        this.value = (BigInteger) list.get(1);

        logger.debug("approve spender:" + Hex.toHexString(spender));
        logger.debug("approve value:" + value.toString());
    }
}
