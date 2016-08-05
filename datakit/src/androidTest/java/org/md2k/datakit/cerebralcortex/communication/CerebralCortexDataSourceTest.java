package org.md2k.datakit.cerebralcortex.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.md2k.datakitapi.source.datasource.DataSource;
import org.md2k.datakitapi.source.datasource.DataSourceBuilder;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
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
public class CerebralCortexDataSourceTest {
    Gson gson;
    CerebralCortexDataSource ccds;

    @org.junit.Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().serializeNulls().create();
        ccds = new CerebralCortexDataSource();
        ccds.participant_id = "123-456";
        ccds.datasource = new DataSourceBuilder().setId("123").build();
    }

    @org.junit.Test
    public void testCerebralCortexDataSourceParameters() throws Exception {
        assertThat(ccds.datasource, instanceOf(DataSource.class));
        assertThat(ccds.participant_id, instanceOf(String.class));
    }

    @org.junit.Test
    public void testCerebralCortexDataSourceDecode() throws Exception {
        String base = "{\n" +
                "    \"datasource\": {\n" +
                "        \"application\": null,\n" +
                "        \"dataDescriptors\": null,\n" +
                "        \"persistent\": true,\n" +
                "        \"platform\": null,\n" +
                "        \"platformApp\": null,\n" +
                "        \"id\": \"123\",\n" +
                "        \"metadata\": {},\n" +
                "        \"type\": null\n" +
                "    },\n" +
                "    \"participant_id\": \"123-456\"\n" +
                "}";

        ccds = gson.fromJson(base, CerebralCortexDataSource.class);


        assertThat(ccds.participant_id, is("123-456"));
        assertThat(ccds.datasource.getApplication(), is(nullValue()));
        assertThat(ccds.datasource.getDataDescriptors(), is(nullValue()));
        assertThat(ccds.datasource.getPlatform(), is(nullValue()));
        assertThat(ccds.datasource.getPlatformApp(), is(nullValue()));
        assertThat(ccds.datasource.getId(), is("123"));

    }
}