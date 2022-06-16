package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class login extends AppCompatActivity {
    Button login_confirm;
    EditText username_input;
    EditText password_input;

    public static Socket socket;
    public static Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Context context = getApplicationContext();

        username_input = findViewById(R.id.login_username_input);
        password_input = findViewById(R.id.login_password_input);

        // navigate to friends page
        login_confirm = findViewById(R.id.confirm_login_button);
        login_confirm.setOnClickListener(new View.OnClickListener() {
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
                            client = new Client(socket, username, password, "Login");
                            client.listenForMessage();
                            client.sendMessage();
                        } catch (IOException e){
                            CharSequence text = "Unable to connect " + MainActivity.ip_addr + ":" + MainActivity.port_num;
                            Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }
                        // go to the chat page
                        Intent i = new Intent(login.this, searchFriend.class);
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
        return true;
    }
}