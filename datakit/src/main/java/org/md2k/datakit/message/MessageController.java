package org.md2k.datakit.message;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import org.md2k.datakit.privacy.PrivacyManager;
import org.md2k.datakitapi.datatype.DataType;
import org.md2k.datakitapi.messagehandler.MessageType;
import org.md2k.datakitapi.source.datasource.DataSource;
import org.md2k.datakitapi.source.datasource.DataSourceClient;
import org.md2k.datakitapi.status.Status;
import org.md2k.utilities.Report.Log;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Copyright (c) 2015, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
 * - Timothy W. Hnat <twhnat@memphis.edu>
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
public class MessageController {
    private static final String TAG = MessageController.class.getSimpleName();
    private static MessageController instance;
    Context context;
    PrivacyManager privacyManager;

    public static MessageController getInstance(Context context) throws IOException {
        if (instance == null)
            instance = new MessageController(context);
        return instance;
    }

    MessageController(Context context) throws IOException {
        this.context = context;
        privacyManager=PrivacyManager.getInstance(context);
    }
    public void close(){
        Log.d(TAG, "messageController ... close()...instance=" + instance);
        if(instance!=null) {
            privacyManager.close();
            instance = null;
        }
    }

    public Message execute(Message incomingMessage) {
        Bundle bundle;
        Status status;
        switch (incomingMessage.what) {
            case MessageType.REGISTER:
                DataSourceClient dataSourceClient = privacyManager.register((DataSource) incomingMessage.getData().getSerializable(DataSource.class.getSimpleName()));
                bundle = new Bundle();
                bundle.putSerializable(DataSourceClient.class.getSimpleName(), dataSourceClient);
                bundle.putSerializable(Status.class.getSimpleName(),dataSourceClient.getStatus());
                return prepareMessage(bundle, MessageType.REGISTER);
            case MessageType.UNREGISTER:
                status = privacyManager.unregister(incomingMessage.getData().getInt("ds_id"));
                bundle = new Bundle();
                bundle.putSerializable(Status.class.getSimpleName(), status);
                return prepareMessage(bundle, MessageType.UNREGISTER);
            case MessageType.FIND:
                ArrayList<DataSourceClient> dataSourceClients = privacyManager.find((DataSource) incomingMessage.getData().getSerializable(DataSource.class.getSimpleName()));
                bundle = new Bundle();
                bundle.putSerializable(DataSourceClient.class.getSimpleName(), dataSourceClients);
                bundle.putSerializable(Status.class.getSimpleName(), new Status(Status.SUCCESS));
                return prepareMessage(bundle, MessageType.FIND);
            case MessageType.INSERT:
//                status = privacyManager.insert(incomingMessage.getData().getInt("ds_id"), (DataType) incomingMessage.getData().getSerializable(DataType.class.getSimpleName()));
                privacyManager.insert(incomingMessage.getData().getInt("ds_id"), (DataType) incomingMessage.getData().getSerializable(DataType.class.getSimpleName()));
                //                bundle = new Bundle();
//                bundle.putSerializable(Status.class.getSimpleName(), status);
                return null;
//                return prepareMessage(bundle,MessageType.INSERT);
            case MessageType.QUERY:
                ArrayList<DataType> dataTypes;
                if (incomingMessage.getData().containsKey("starttimestamp"))
                    dataTypes = privacyManager.query(incomingMessage.getData().getInt("ds_id"), incomingMessage.getData().getLong("starttimestamp"), incomingMessage.getData().getLong("endtimestamp"));
                else if (incomingMessage.getData().containsKey("last_n_sample"))
                    dataTypes = privacyManager.query(incomingMessage.getData().getInt("ds_id"), incomingMessage.getData().getInt("last_n_sample"));
                else if (incomingMessage.getData().containsKey("last_key"))
                    dataTypes = privacyManager.query(incomingMessage.getData().getInt("ds_id"), incomingMessage.getData().getLong("last_key"));
                bundle = new Bundle();
                bundle.putSerializable(DataType.class.getSimpleName(), dataTypes);
                bundle.putSerializable(Status.class.getSimpleName(), new Status(Status.SUCCESS));
                return prepareMessage(bundle, MessageType.QUERY);
            case MessageType.SUBSCRIBE:
                Status statusSubscribe = privacyManager.subscribe(incomingMessage.getData().getInt("ds_id"), incomingMessage.replyTo);
                bundle = new Bundle();
                bundle.putSerializable(Status.class.getSimpleName(), statusSubscribe);
                return prepareMessage(bundle, MessageType.SUBSCRIBE);
            case MessageType.UNSUBSCRIBE:
                Status statusUnsubscribe = privacyManager.unsubscribe(incomingMessage.getData().getInt("ds_id"), incomingMessage.replyTo);
                bundle = new Bundle();
                bundle.putSerializable(Status.class.getSimpleName(), statusUnsubscribe);
                return prepareMessage(bundle, MessageType.UNSUBSCRIBE);
        }
        return null;
    }

    public Message prepareMessage(Bundle bundle, int messageType) {
        Message message = Message.obtain(null, 0, 0, 0);
        message.what = messageType;
        message.setData(bundle);
        return message;
    }
}
