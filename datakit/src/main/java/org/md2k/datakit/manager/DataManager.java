package org.md2k.datakit.manager;

import android.os.Bundle;
import android.os.Message;

import org.md2k.datakitapi.datatype.DataType;
import org.md2k.datakitapi.messagehandler.MessageType;
import org.md2k.datakitapi.status.Status;
import org.md2k.datakitapi.status.StatusCodes;

import java.util.ArrayList;

/**
 * Copyright (c) 2015, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
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
public class DataManager extends Manager{
    private static final String TAG = DataManager.class.getSimpleName();
    public DataManager(){
        super();
    }

    public Message insert(int ds_id, DataType dataType){
        if(databaseLogger==null) return prepareErrorMessage(MessageType.INSERT);

        Bundle bundle=new Bundle();
        databaseLogger.insert(ds_id,dataType);
        bundle.putSerializable(Status.class.getSimpleName(), new Status(StatusCodes.SUCCESS));
        return prepareMessage(bundle,MessageType.INSERT);
    }
    public Message query(int ds_id,long starttimestamp, long endtimestamp){
        if(databaseLogger==null) return prepareErrorMessage(MessageType.QUERY);
        ArrayList<DataType> dataTypes = databaseLogger.query(ds_id, starttimestamp,endtimestamp);
        Bundle bundle=new Bundle();
        bundle.putSerializable(DataType.class.getSimpleName(),dataTypes);
        return prepareMessage(bundle, MessageType.QUERY);
    }
}