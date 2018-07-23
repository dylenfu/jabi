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

import com.google.inject.Injector;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.spongycastle.util.encoders.Hex;

import static junit.framework.TestCase.assertEquals;

public class ApproveMethodTest {

    @Test
    public void unpackTest() {

        Injector injector = Common.getInjector();

        //byte[] input = Hex.decode("0x095ea7b300000000000000000000000045aa504eb94077eec4bf95a10095a8e3196fc5910000000000000000000000000000000000000000000000008ac7230489e80000");
        byte[] input = Hex.decode("0000000000000000000000000000000045aa504eb94077eec4bf95a10095a8e3196fc5910000000000000000000000000000000000000000000000008ac7230489e80000");
        ApproveMethod method = injector.getInstance(ApproveMethod.class);

        method.setInput(input);

        try {
            method.unpack();
        } catch (Exception e) {
            injector.getInstance(Logger.class).debug(e.getMessage());
        }

        assertEquals(Hex.toHexString(method.getSpender()), "45aA504EB94077EeC4BF95A10095A8e3196fc591".toLowerCase());
        assertEquals(method.getValue().toString(), "10000000000000000000");
    }
}
