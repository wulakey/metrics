/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.metrics;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SlidingWindowReservoirTest {
    private final SlidingWindowReservoir reservoir = new SlidingWindowReservoir(3);

    @Test
    public void handlesSmallDataStreams() throws Exception {
        reservoir.update(1);
        reservoir.update(2);

        assertThat(reservoir.getSnapshot().getValues())
                .containsOnly(1, 2);
    }

    @Test
    public void onlyKeepsTheMostRecentFromBigDataStreams() throws Exception {
        reservoir.update(1);
        reservoir.update(2);
        reservoir.update(3);
        reservoir.update(4);

        assertThat(reservoir.getSnapshot().getValues())
                .containsOnly(2, 3, 4);
    }
}
