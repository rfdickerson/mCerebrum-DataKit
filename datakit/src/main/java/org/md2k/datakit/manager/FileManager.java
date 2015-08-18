package org.md2k.datakit.manager;

import android.content.Context;
import android.os.Environment;

import org.md2k.utilities.Report.Log;

import java.io.File;

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
public class FileManager {
    public static final int INTERNAL_SDCARD = 1;
    public static final int EXTERNAL_SDCARD = 2;
    public static final int INTERNAL_SDCARD_FOLLOWED_BY_EXTERNAL_SDCARD = 3;
    public static final int EXTERNAL_SDCARD_FOLLOWED_BY_INTERNAL_SDCARD = 4;
    public static final int NONE = 5;
    public static int STORAGE_OPTION = EXTERNAL_SDCARD_FOLLOWED_BY_INTERNAL_SDCARD;
    private static final String TAG = FileManager.class.getSimpleName();

    public static String getFileName() {
        return "database.db";
    }

    public static String getStorageOption() {
        switch (STORAGE_OPTION) {
            case INTERNAL_SDCARD:
                return "Internal SD Card";
            case EXTERNAL_SDCARD:
                return "External SD Card";
            case INTERNAL_SDCARD_FOLLOWED_BY_EXTERNAL_SDCARD:
                return "both Internal & External SD Card";
            case EXTERNAL_SDCARD_FOLLOWED_BY_INTERNAL_SDCARD:
                return "both External & Internal SD Card";
            default:
                return "SD Card";
        }
    }

    public static final int VERSION = 1;

    public static String getInternalSDCardDirectory(Context context) {
        String directory = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            directory = Environment.getExternalStorageDirectory().toString() + "/Android/data/" + context.getPackageName() + "/files/";
            File dir = new File(directory);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    directory = null;
                }
            }
        }
        return directory;
    }

    public static String getExternalSDCardDirectory(Context context) {
        /**
         * TODO: There is no straight forward way to detect the presence of removable SD card.
         * This functions works for Samsung Galaxy S4. But for different phone, it needs to check
         **/
        String strSDCardPath = System.getenv("SECONDARY_STORAGE");
        String directory = null;
        Log.d(TAG, "External SD Card=" + strSDCardPath + " context=" + context);

        if (strSDCardPath != null && strSDCardPath.length() != 0) {
            directory = strSDCardPath + "/Android/data/" + context.getPackageName() + "/files/";
            File dir = new File(directory);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    directory = null;
                }
            }
        }
        return directory;
    }

    public static String getCurrentSDCardOptionString() {
        switch (STORAGE_OPTION) {
            case INTERNAL_SDCARD:
                return "Internal SDcard";
            case EXTERNAL_SDCARD:
                return "External SDcard";
            case INTERNAL_SDCARD_FOLLOWED_BY_EXTERNAL_SDCARD:
                return "Internal SDcard followed by External SDcard";
            case EXTERNAL_SDCARD_FOLLOWED_BY_INTERNAL_SDCARD:
                return "External SDcard followed by Internal SDcard";
            case NONE:
                return "None";
            default:
                return "(null)";
        }

    }

    public static String getFilePath(Context context) {
        String filepath = getDirectory(context);
        if (filepath != null)
            filepath = filepath + getFileName();
        return filepath;
    }

    public static String getValidSDcard(Context context) {
        switch (STORAGE_OPTION) {
            case INTERNAL_SDCARD:
                if (getInternalSDCardDirectory(context) == null) return "Not found";
                else return "Internal SDCard";
            case EXTERNAL_SDCARD:
                if (getExternalSDCardDirectory(context) == null) return "Not found";
                else return "External SDCard";
            case INTERNAL_SDCARD_FOLLOWED_BY_EXTERNAL_SDCARD:
                if (getInternalSDCardDirectory(context) != null) return "Internal SDCard";
                else if (getExternalSDCardDirectory(context) != null) return "External SDCard";
                else return "Not found";
            case EXTERNAL_SDCARD_FOLLOWED_BY_INTERNAL_SDCARD:
                if (getExternalSDCardDirectory(context) != null) return "External SDCard";
                else if (getInternalSDCardDirectory(context) != null) return "Internal SDCard";
                else return "Not found";
            case NONE:
                return "none";
            default:
                return "(null)";
        }

    }

    public static String getDirectory(Context context) {
        if (context == null) return null;
        Log.d(TAG, "getDirectory.. STORAGE_OPTION=" + STORAGE_OPTION + " Context=" + context);
        String directory = null;
        switch (STORAGE_OPTION) {
            case INTERNAL_SDCARD:
                directory = getInternalSDCardDirectory(context);
                break;
            case EXTERNAL_SDCARD:
                directory = getExternalSDCardDirectory(context);
                break;
            case INTERNAL_SDCARD_FOLLOWED_BY_EXTERNAL_SDCARD:
                directory = getInternalSDCardDirectory(context);
                if (directory == null)
                    directory = getExternalSDCardDirectory(context);
                break;
            case EXTERNAL_SDCARD_FOLLOWED_BY_INTERNAL_SDCARD:
                directory = getExternalSDCardDirectory(context);
                if (directory == null)
                    directory = getInternalSDCardDirectory(context);
                break;
            case NONE:
                directory = null;
                break;
            default:
                directory = null;
        }
        return directory;
    }
}