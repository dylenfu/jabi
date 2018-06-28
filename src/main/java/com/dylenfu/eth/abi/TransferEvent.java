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

package com.dylenfu.eth.abi;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.log4j.Logger;
import org.ethereum.solidity.Abi;
import org.ethereum.solidity.Abi.Entry;
import java.math.BigInteger;

class TransferEvent {
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
        Entry entry = abi.get(0);
        for (Entry.Param input : entry.inputs) {
            logger.debug(input);
            logger.debug("-----hahahahhah");
        }
    }
}
