package org.md2k.datakit.cerebralcortex.communication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
public class CerebralCortexDataResponseTest {
    Gson gson;
    CerebralCortexDataResponse ccdr;

    @org.junit.Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().serializeNulls().create();
        ccdr = new CerebralCortexDataResponse();
        ccdr.count = 0;
        ccdr.message = "";
        ccdr.status = "";
    }

    @org.junit.Test
    public void testCerebralCortexDataResponseParameters() throws Exception {
        assertThat(ccdr.count, is(instanceOf(long.class)));
        assertThat(ccdr.message, instanceOf(String.class));
        assertThat(ccdr.status, instanceOf(String.class));
    }

    @org.junit.Test
    public void testCerebralCortexDataResponseDecode() throws Exception {
        String base = "{\n" +
                "    \"count\": 12,\n" +
                "    \"message\": \"upload\",\n" +
                "    \"status\": \"SUCCESS\"\n" +
                "}";
        ccdr = gson.fromJson(base, CerebralCortexDataResponse.class);

        assertThat(ccdr.count, is(12L));
        assertThat(ccdr.message, is("upload"));
        assertThat(ccdr.status, is("SUCCESS"));

    }
}