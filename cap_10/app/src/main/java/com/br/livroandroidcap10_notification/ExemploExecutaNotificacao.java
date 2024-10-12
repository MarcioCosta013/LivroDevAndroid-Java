package com.br.livroandroidcap10_notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class ExemploExecutaNotificacao extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Cancela a notificação
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Para cancelar precisa utilizar o mesmo id que foi utilizado para criar.
        nm.cancel(1);
        TextView text = new TextView(this);
        text.setText("Usuário selecionou a notificação. É possivel executar algo agora.");
        setContentView(text);
    }
}
