package com.dylenfu.eth.abi;/*

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

import java.lang.reflect.AnnotatedElement;

enum TypEnum {
    IntTy, UintTy, BoolTy, StringTy, SliceTy, ArrayTy, AddressTy, FixedBytesTy, BytesTy, HashTy, FixedPointTy, FunctionTy
}

public class Typ {

    //static final byte IntTy = 0;


    private AnnotatedElement typ;
    private byte kind;

    public AnnotatedElement getTyp() {
        return typ;
    }

    public void setTyp(AnnotatedElement typ) {
        this.typ = typ;
    }

    public byte getKind() {
        return kind;
    }

    public void setKind(byte kind) {
        this.kind = kind;
    }


}
