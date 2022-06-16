package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class signup extends AppCompatActivity {
    Button signup_confirm;

    EditText username_input;
    EditText password_input;
    EditText age_input;
    RadioButton male_selected;
    RadioButton female_selected;
    TextView signup_status_msg;

    public static Socket socket;
    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username_input = findViewById(R.id.signup_username_input);
        password_input = findViewById(R.id.signup_password_input);
        age_input = findViewById(R.id.signup_age_input);
        male_selected = findViewById(R.id.male_gender);
        female_selected = findViewById(R.id.female_gender);


        Context context = getApplicationContext();

        signup_confirm = findViewById(R.id.confirm_signup_button);
        signup_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // first check if all fields are filled
                if (!isAllFieldsFilled()) {
                    CharSequence text = "Please fill all fields!";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                String username = username_input.getText().toString();
                String password = password_input.getText().toString();
                // create socket and login automatically
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            socket = new Socket(MainActivity.ip_addr, MainActivity.port_num);
                            client = new Client(socket, username, password, "Register");
                            client.listenForMessage();
                            client.sendMessage();
                        } catch (IOException e){
                            CharSequence text = "Unable to connect " + MainActivity.ip_addr + ":" + MainActivity.port_num;
                            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }
                        // go to the chat page
                        Intent i = new Intent(signup.this, searchFriend.class);
                        startActivity(i);
                    }}).start();
            }
        });

    }
    private boolean isAllFieldsFilled(){
        if(username_input.getText().toString().matches(""))
            return false;
        if(password_input.getText().toString().matches(""))
            return false;
        if(age_input.getText().toString().matches(""))
            return false;
        return male_selected.isChecked() || female_selected.isChecked();
    }


}

