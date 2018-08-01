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

package com.dylenfu.lightcone.persistence;

//import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
//import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

// 根据market分库
public class DbAlgorithmByMarket implements PreciseShardingAlgorithm<String> {

    @Override
    String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String market = shardingValue.getValue();

        int index = id % 2;

        for (String each : collection) {
            if (each.endsWith(index + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
        return market;
    }

//    @Override
//    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<String> shardingValue) {
//        return null;
//    }
//
//    @Override
//    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<String> shardingValue) {
//        return null;
//    }
}
