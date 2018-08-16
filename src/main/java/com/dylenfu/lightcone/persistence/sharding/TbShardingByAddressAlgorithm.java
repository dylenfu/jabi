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

package com.dylenfu.lightcone.persistence.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public final class TbShardingByAddressAlgorithm implements SingleKeyTableShardingAlgorithm<String> {

    private int tableCount = 2;

    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<String> shardingValue) {
        String t = "1";
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue().substring(0, 1) + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<String> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
//        Collection<String> values = shardingValue.getValues();
//        for (String value : values) {
//            for (String tableNames : availableTargetNames) {
//                if (tableNames.endsWith(value + "")) {
//                    result.add(tableNames);
//                }
//            }
//        }
//        return result;
        return null;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<String> shardingValue) {
//        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
//        Range<Integer> range = shardingValue.getValueRange();
//        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : availableTargetNames) {
//                if (each.endsWith(i % tableCount + "")) {
//                    result.add(each);
//                }
//            }
//        }
//        return result;
        return null;
    }

    /**
     * 设置分表的个数
     *
     * @param tableCount
     */
    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }

    public int getTableCount() {
        return tableCount;
    }
}
