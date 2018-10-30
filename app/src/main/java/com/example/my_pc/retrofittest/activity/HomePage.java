package com.example.my_pc.retrofittest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_pc.retrofittest.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnViewAll)
    public void onBtnViewAllClicked() {

        if (!isInternetConnected()){
            showSnackBar();
            return;
        }

        startActivity(new Intent(this, ViewAllActivity.class));
    }

    @OnClick(R.id.btnInsert)
    public void onBtnInsertClicked() {

        if (!isInternetConnected()){
            showSnackBar();
            return;
        }

        startActivity(new Intent(this, InsertActivity.class));

    }

    @OnClick(R.id.btnUpdate)
    public void onBtnUpdateClicked() {

        if (!isInternetConnected()){
            showSnackBar();
            return;
        }

        startActivity(new Intent(this, UpdateActivity.class));
    }

    @OnClick(R.id.btnDelete)
    public void onBtnDeleteClicked() {

        if (!isInternetConnected()){
            showSnackBar();
            return;
        }

        startActivity(new Intent(this, DeleteActivity.class));

    }


    // --- For Internet Connectivity Check
    public boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else
            return false;
    }

    // -- For Show Snackbar
    public void showSnackBar() {

        View view1 = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view1, "Your Internet is not connected!", Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.RED);

        View view2 = snackbar.getView();
        TextView tv = view2.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.GREEN);

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ---- Just hide,,,when user clicked ok
            }
        });

        snackbar.show();

    }
}
