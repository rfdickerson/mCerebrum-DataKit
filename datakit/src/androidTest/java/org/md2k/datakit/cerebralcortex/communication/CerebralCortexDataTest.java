package org.md2k.datakit.cerebralcortex.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.md2k.datakitapi.datatype.DataType;
import org.md2k.datakitapi.datatype.DataTypeDouble;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/*
 * Copyright (c) 2016, The University of Memphis, MD2K Center
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class CerebralCortexDataTest {
    Gson gson;

    @org.junit.Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().serializeNulls().create();
    }

    @org.junit.Test
    public void testCerebralCortexData() throws Exception {
        CerebralCortexData cc = new CerebralCortexData(123);
        assertThat(gson.toJson(cc), is("{\"data\":[],\"datastream_id\":123}"));

        cc.data = new ArrayList<DataType>();
        for (int i = 0; i < 5; i++) {
            cc.data.add(new DataTypeDouble(1234567890 + i, 123.456 * i));
        }
        assertThat(gson.toJson(cc), is("{\"data\":[{\"sample\":0.0,\"dateTime\":1234567890,\"offset\":-21600000},{\"sample\":123.456,\"dateTime\":1234567891,\"offset\":-21600000},{\"sample\":246.912,\"dateTime\":1234567892,\"offset\":-21600000},{\"sample\":370.368,\"dateTime\":1234567893,\"offset\":-21600000},{\"sample\":493.824,\"dateTime\":1234567894,\"offset\":-21600000}],\"datastream_id\":123}"));
    }

    @org.junit.Test
    public void testCerebralCortexDataObjects() throws Exception {
        CerebralCortexData cc = new CerebralCortexData(123);
        assertThat(cc.datastream_id, is(instanceOf(long.class)));
        assertThat(cc.data, is(instanceOf(List.class)));
    }
}