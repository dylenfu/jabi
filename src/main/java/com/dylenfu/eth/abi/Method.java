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

public class Method {

    private String name;
    private Boolean Const;

    // private list<Argument> Inputs;
    // private list<Argument> Outputs;

    public Method(String name, Boolean aConst) {
        this.name = name;
        Const = aConst;
    }

    public String getName() {
        return name;
    }

    public Boolean getConst() {
        return Const;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConst(Boolean aConst) {
        Const = aConst;
    }
}
