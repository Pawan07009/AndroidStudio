package com.example.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;

    public class CallLogDumper {

        public static void dumpCallLogs(Context context) {
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(
                        CallLog.Calls.CONTENT_URI,
                        null, null, null,
                        CallLog.Calls.DATE + " DESC"
                );

                if (cursor != null && cursor.moveToFirst()) {
                    int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                    int typeIndex = cursor.getColumnIndex(CallLog.Calls.TYPE);
                    int dateIndex = cursor.getColumnIndex(CallLog.Calls.DATE);
                    int durationIndex = cursor.getColumnIndex(CallLog.Calls.DURATION);

                    do {
                        String number = cursor.getString(numberIndex);
                        String type = getCallType(cursor.getInt(typeIndex));
                        String date = cursor.getString(dateIndex);
                        String duration = cursor.getString(durationIndex);

                        Log.d("CallLog", "Number: " + number +
                                ", Type: " + type +
                                ", Date: " + date +
                                ", Duration: " + duration + " sec");
                    } while (cursor.moveToNext());
                }
            } catch (SecurityException e) {
                Log.e("CallLogDumper", "Permission denied: " + e.getMessage());
            } finally {
                if (cursor != null) cursor.close();
            }
        }

        private static String getCallType(int typeCode) {
            switch (typeCode) {
                case CallLog.Calls.INCOMING_TYPE: return "Incoming";
                case CallLog.Calls.OUTGOING_TYPE: return "Outgoing";
                case CallLog.Calls.MISSED_TYPE: return "Missed";
                case CallLog.Calls.REJECTED_TYPE: return "Rejected";
                case CallLog.Calls.VOICEMAIL_TYPE: return "Voicemail";
                default: return "Unknown";
            }
        }
    }

