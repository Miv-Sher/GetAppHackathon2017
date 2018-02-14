package com.existentialponytomas.getappprospero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Sergey on 02.04.2017.
 */

public class SmsReceiver extends BroadcastReceiver {
    public SmsReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            //we recevied the broadcast of no concern
            return;
        }
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            //the data is empty,so return
            return;
        }
        SmsMessage[] msgs;
        try {
            Object[] pdus = (Object[]) bundle.get("pdus");
            if (pdus == null) {
                //the data is empty,so return
                return;
            }
            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String msg = msgs[i].getMessageBody();
                if (msg.contains("SOME DETECTION TEXT HERE")) {
                    //do the job
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


