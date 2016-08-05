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
public class StudyInfoCCResponseTest {
    Gson gson;
    StudyInfoCCResponse siResp;

    @org.junit.Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().serializeNulls().create();
        siResp = new StudyInfoCCResponse();
        siResp.created_at = "1";
        siResp.id = "2";
        siResp.identifier = "345";
        siResp.name = "FooBar";
        siResp.updated_at = "";
    }

    @org.junit.Test
    public void testStudyInfoCCResponseParameters() throws Exception {
        assertThat(siResp, is(instanceOf(StudyInfoCCResponse.class)));
        assertThat(siResp.created_at, is(instanceOf(String.class)));
        assertThat(siResp.id, is(instanceOf(String.class)));
        assertThat(siResp.identifier, is(instanceOf(String.class)));
        assertThat(siResp.name, is(instanceOf(String.class)));
        assertThat(siResp.updated_at, is(instanceOf(String.class)));
    }

    @org.junit.Test
    public void testStudyInfoCCResponseDecode() throws Exception {
        String base = "{\n" +
                "  \"id\": 4,\n" +
                "  \"identifier\": \"Study XYZ\",\n" +
                "  \"name\": \"Study Name\",\n" +
                "  \"created_at\": \"2016-04-18T20:09:00.286Z\",\n" +
                "  \"updated_at\": \"2016-04-18T20:09:00.286Z\"\n" +
                "}\n";

        siResp = gson.fromJson(base, StudyInfoCCResponse.class);

        assertThat(siResp.created_at, is("2016-04-18T20:09:00.286Z"));
        assertThat(siResp.id, is("4"));
        assertThat(siResp.identifier, is("Study XYZ"));
        assertThat(siResp.name, is("Study Name"));
        assertThat(siResp.updated_at, is("2016-04-18T20:09:00.286Z"));

    }
}