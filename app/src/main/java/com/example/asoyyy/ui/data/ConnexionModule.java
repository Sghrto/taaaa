package com.example.asoyyy.ui.data;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aflak.arduino.Arduino;
import com.example.asoyyy.ui.presenter.ConnexionPresenter;
import com.example.asoyyy.ui.presenter.ConnexionPresenterImpl;
import com.example.asoyyy.ui.view.ChatFragment;
import com.example.asoyyy.ui.view.ConnexionView;


@Module
public class ConnexionModule {
    private ConnexionView connexionView;

    public ConnexionModule(ConnexionView connexionView) {
        this.connexionView = connexionView;
    }

    @Singleton @Provides
    public ConnexionView provideView(){
        return connexionView;
    }

    @Singleton @Provides
    public Arduino provideArduino(Context context){
        return new Arduino (context);
    }

    @Singleton @Provides
    public ConnexionPresenter providePresenter(ConnexionView view, Arduino arduino){
        return new ConnexionPresenterImpl(view, arduino);
    }

    @Singleton @Provides
    public FragmentManager provideFragmentManager(Context context){
        return ((Activity)context).getFragmentManager();
    }

    @Singleton @Provides
    public ChatFragment provideChatFragment(){
        return new ChatFragment();
    }


    @Singleton @Provides
    public AlertDialog.Builder provideDialog(Context context){
        return new AlertDialog.Builder(context);
    }
}
