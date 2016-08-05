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
public class UserInfoCCResponseTest {
    Gson gson;
    UserInfoCCResponse uiResp;

    @org.junit.Before
    public void setUp() throws Exception {
        gson = new GsonBuilder().serializeNulls().create();
        uiResp = new UserInfoCCResponse();
        uiResp.created_at = "1";
        uiResp.id = "2";
        uiResp.identifier = "345";
        uiResp.updated_at = "";
    }

    @org.junit.Test
    public void testUserInfoCCResponseParameters() throws Exception {
        assertThat(uiResp, is(instanceOf(UserInfoCCResponse.class)));
        assertThat(uiResp.created_at, is(instanceOf(String.class)));
        assertThat(uiResp.id, is(instanceOf(String.class)));
        assertThat(uiResp.identifier, is(instanceOf(String.class)));
        assertThat(uiResp.updated_at, is(instanceOf(String.class)));
    }

    @org.junit.Test
    public void testUserInfoCCResponseDecode() throws Exception {
        String base = "{\n" +
                "  \"id\": \"a37fbcab-0f90-4dd3-907d-f0cb3f247302\",\n" +
                "  \"identifier\": \"Participant Name/Identifier\",\n" +
                "  \"created_at\": \"2016-04-28T01:56:38.217Z\",\n" +
                "  \"updated_at\": \"2016-04-28T01:56:38.217Z\"\n" +
                "}\n";

        uiResp = gson.fromJson(base, UserInfoCCResponse.class);

        assertThat(uiResp.created_at, is("2016-04-28T01:56:38.217Z"));
        assertThat(uiResp.id, is("a37fbcab-0f90-4dd3-907d-f0cb3f247302"));
        assertThat(uiResp.identifier, is("Participant Name/Identifier"));
        assertThat(uiResp.updated_at, is("2016-04-28T01:56:38.217Z"));

    }
}