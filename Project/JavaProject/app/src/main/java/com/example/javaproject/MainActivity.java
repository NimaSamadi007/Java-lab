package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.InetAddresses;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    Button confirm_server_ip;
    Button signup_but;
    Button login_but;
    EditText ip_addr_input;
    EditText port_input;
    TextView msg_show;

    public static int port_num;
    public static String ip_addr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup_but = findViewById(R.id.signup_button);
        login_but = findViewById(R.id.login_button);
        confirm_server_ip = findViewById(R.id.confirm_server_ip);

        // by default it is not clickable
        signup_but.setEnabled(false);
        login_but.setEnabled(false);

        // confirm ip address
        confirm_server_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ip_addr_input = (EditText) findViewById(R.id.server_ip_input);
                ip_addr = ip_addr_input.getText().toString();
                port_input = (EditText) findViewById(R.id.server_port_input);
                port_num = Integer.parseInt(port_input.getText().toString());
                msg_show = (TextView) findViewById(R.id.msg_show);
                msg_show.setText("Server ip and port saved");
                signup_but.setEnabled(true);
                login_but.setEnabled(true);
            }
        });

        // navigate to signup page
        signup_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, signup.class);
                startActivity(i);
            }
        });

        // navigate to login page
        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });
    }

}