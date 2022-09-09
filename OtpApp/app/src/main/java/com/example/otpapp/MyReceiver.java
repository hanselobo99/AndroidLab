package com.example.otpapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

public class MyReceiver extends BroadcastReceiver {
    private static EditText editOTP;
    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for(SmsMessage smsMessage:messages){
            String messageBody = smsMessage.getMessageBody();
            String getOtp = messageBody.split(":")[1];
            editOTP.setText(getOtp);
        }
    }
    public void setEditOtp(EditText editText){
        MyReceiver.editOTP = editText;
    }
}
