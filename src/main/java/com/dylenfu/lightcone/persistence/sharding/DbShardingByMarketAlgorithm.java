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
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public final class DbShardingByMarketAlgorithm implements SingleKeyDatabaseShardingAlgorithm<String> {

    private int dbCount = 2;

    @Override
    public String doEqualSharding(final Collection<String> availableTargetNames, final ShardingValue<String> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(final Collection<String> availableTargetNames, final ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Collection<String> values = shardingValue.getValues();
        for (String value : values) {
            for (String dataSourceName : availableTargetNames) {
                if (dataSourceName.endsWith(value + "")) {
                    result.add(dataSourceName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(final Collection<String> availableTargetNames, final ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
//        Range<String> range = shardingValue.getValueRange();
//        for (String i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
//            for (String each : availableTargetNames) {
//                if (each.endsWith(i % dbCount + "")) {
//                    result.add(each);
//                }
//            }
//        }
        return result;
    }

    /**
     * 设置database分库的个数
     * @param dbCount
     */
    public void setDbCount(int dbCount) {
        this.dbCount = dbCount;
    }


    public int getDbCount() {
        return dbCount;
    }
}

