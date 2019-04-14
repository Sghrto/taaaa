package com.example.asoyyy.ui.view;



import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.felhr.serialportexample.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import serialportexample.MainActivity;

import com.example.asoyyy.R;
import com.example.asoyyy.data.AppModule;
import com.example.asoyyy.ui.data.ConnexionModule;
import com.example.asoyyy.ui.data.DaggerConnexionComponent;
import com.example.asoyyy.ui.presenter.ConnexionPresenter;


public class ConnexionActivity extends AppCompatActivity implements ConnexionView, ChatFragment.OnChatListener {
    @BindView(R.id.activity_connexion_connect) Button connectButton;
    @BindView(R.id.activity_connexion_status) TextView statusText;
    @BindView(R.id.activity_connexion_usb) ImageView usbImage;


    @Inject ConnexionPresenter presenter;
    @Inject FragmentManager fragmentManager;
    @Inject ChatFragment chatFragment;

    public static final int NOTIFICATION_ID = 1;
    //@Inject AlertDialog.Builder aboutDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


        ButterKnife.bind(this);
        DaggerConnexionComponent.builder()
                .appModule(new AppModule(this))
                .connexionModule(new ConnexionModule(this))
                .build().inject(this);

        fragmentManager.beginTransaction().add(R.id.activity_connexion_fragment, chatFragment).hide(chatFragment).commit();
        chatFragment.setListener(this);


    }

    public void sendNotification (View view){
        Intent resultIntent = new Intent (this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity (this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat
                .Builder(this)
                .setSmallIcon(R.drawable.ic_notification_white_48px)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification_white_48px))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true)
                .setContentIntent (resultPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, notification.build());
    }

    @Override
    public void setConnectButtonVisibility(int visibility) {
        connectButton.setVisibility(visibility);
    }


    @Override
    public void showChatFragment() {
        fragmentManager.beginTransaction()
                .show(chatFragment)
                .commit();
    }

    @Override
    public void hideChatFragment() {
        fragmentManager.beginTransaction()
                .hide(chatFragment)
                .commit();
    }

    @Override
    public void setStatusText(int id) {
        statusText.setText(id);
    }

    @Override
    public void setUsbColor(int id) {
        usbImage.setColorFilter(ContextCompat.getColor(this, id));
    }

    @Override
    public void showMessage(String message) {
        chatFragment.appendMessage2(message, ContextCompat.getColor(this, R.color.fragment_chat_arduino));
    }

    @OnClick(R.id.activity_connexion_connect)
    public void onConnect(){
        presenter.connect();
    }

    @Override
    public void onSendMessage(String message) {
        presenter.send(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.connexion, menu);
        return true;
    }
}

