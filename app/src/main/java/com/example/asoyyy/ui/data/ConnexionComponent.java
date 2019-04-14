package com.example.asoyyy.ui.data;

import javax.inject.Singleton;

import dagger.Component;
import com.example.asoyyy.data.AppModule;
import com.example.asoyyy.ui.view.ChatFragment;
import com.example.asoyyy.ui.view.ConnexionActivity;


@Singleton
@Component(modules = {AppModule.class, ConnexionModule.class})
public interface ConnexionComponent {
    void inject(ConnexionActivity activity);
    void inject(ChatFragment fragment);



}
