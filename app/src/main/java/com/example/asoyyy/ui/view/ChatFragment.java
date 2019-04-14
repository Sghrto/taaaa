package com.example.asoyyy.ui.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.asoyyy.R;


public class ChatFragment extends Fragment {
    @BindView(R.id.sip1) TextView chat1;
    @BindView (R.id.aktif) Switch Swit;
    @BindView (R.id.do_not_distrub) Switch Do_not;


    public OnChatListener listen;
    private OnChatListener listener;
    public OnChatListener listener2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        chat1.setMovementMethod(new ScrollingMovementMethod ());
        return view;
    }

    @OnClick(R.id.aktif)
    public void onSend2(){

        String message;
         if (Swit.isChecked ())
            message = Swit.getTextOn().toString();
         else
            message = Swit.getTextOff().toString();


        appendMessage2(message, ContextCompat.getColor(getActivity(), R.color.fragment_chat_android));

        if(listener!=null){
            listener.onSendMessage(message);
        }
    }

    public void appendMessage2(final String message, final int color){
       getActivity().runOnUiThread(new Runnable () {
           @Override
            public void run() {
               int start = chat1.getText().length();
                chat1.append(message+"\n");
                int end = chat1.getText().length();

                Spannable spannableText = (Spannable) chat1.getText();
                spannableText.setSpan(new ForegroundColorSpan (color), start, end, 0);
            }
       });
    }


    @OnClick(R.id.do_not_distrub)
    public void onSend5(){

        String message;
        if (Do_not.isChecked ())
            message = Do_not.getTextOn().toString();
        else
            message = Do_not.getTextOff().toString();

        if(listener!=null){
            listener.onSendMessage(message);
        }
    }



    @OnClick(R.id.busy)
    public void onSend3(){
        String message = "3";


        if(listener!=null){
            listener.onSendMessage(message);
        }
    }






    @OnClick(R.id.not_busy)
    public void onSend4(){
        String message = "4";

        if(listener!=null){
            listener.onSendMessage(message);
        }
    }


    

    public void setListener(OnChatListener listener) {
        this.listen = listener;
        this.listener = listener;
        this.listener2 = listener;
    }

    public interface OnChatListener{
        void onSendMessage(String message);
    }
}
