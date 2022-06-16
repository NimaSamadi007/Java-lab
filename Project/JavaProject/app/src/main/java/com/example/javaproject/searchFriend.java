package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class searchFriend extends AppCompatActivity {
    TextView chat_layout;
    EditText input_msg_container;
    Button send_msg_but;
    private CountDownTimer timer_obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        input_msg_container = findViewById(R.id.input_msg);
        send_msg_but = findViewById(R.id.send_msg);

        chat_layout = findViewById(R.id.msg_layout);
        chat_layout.setMovementMethod(new ScrollingMovementMethod());

        timer_obj = new CountDownTimer(1000,10) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (Client.messageFromChat != null && !Client.messageFromChat.equals("!")) {
                    chat_layout.append(Client.messageFromChat + "\r\n");
                    Client.messageFromChat = "!";
                }
            }
            @Override
            public void onFinish() {
                timer_obj.start(); // restart timer
            }
        }.start();

        send_msg_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_layout.append(input_msg_container.getText().toString() + "\r\n");
                Client.messageToSend = input_msg_container.getText().toString();
                Client.sendMsg = true;
                input_msg_container.getText().clear();
            }
        });

    }
}