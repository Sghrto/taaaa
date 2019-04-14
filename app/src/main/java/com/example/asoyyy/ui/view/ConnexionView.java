package com.example.asoyyy.ui.view;


public interface ConnexionView {
    void setConnectButtonVisibility(int visibility);
    void showChatFragment();
    void hideChatFragment();
    void setStatusText(int id);
    void setUsbColor(int id);
    void showMessage(String message);
}
