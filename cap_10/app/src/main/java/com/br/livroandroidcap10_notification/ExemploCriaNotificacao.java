package com.br.livroandroidcap10_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExemploCriaNotificacao extends Activity {

    private static final String CHANNEL_ID = "channel_id_exemplo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text = new TextView(this);
        text.setText("Uma notificação foi disparada.");
        setContentView(text);

        //Texto com a chamada para a notificação (barra de status)
        String tickerText = "Você recebeu uma menssagem";

        //Detalhes da mensagem, quem enviou e o texto
        CharSequence titulo = "Marcio";
        CharSequence mensagem = "Exemplo de notificação";

        //Exibe a notificação para abrir a RecebeuMensagemActivity
        criarNotificacao(this, tickerText, titulo, mensagem, ExemploExecutaNotificacao.class);
    }

    //Exibe a notificação
    protected void criarNotificacao(Context context, CharSequence mensagemBarraStatus, CharSequence titulo, CharSequence mensagem, Class<?> activity) {

//        //Recupera o serviço do NotificationManager
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        Notification n = new Notification(R.drawable.ic_launcher_foreground, mensagemBarraStatus, System.currentTimeMillis());
        // Cria o canal de notificação, necessário para Android 8.0 ou superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Canal de exemplo", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Descrição do canal");
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //PendingIntent para executar a Activity se o usuário selecionar a notificação.
        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, activity), PendingIntent.FLAG_IMMUTABLE);

//        //Informações
//        n.setLatestEventInfo(this,titulo,mensagem,p);
//        //Permissão:<uses-permission android:name="android.permission.VIBRATE"/>
//        //Espera 100ms e vivra por 250ms, depois espera por 100ms e vibra por 500ms
//        n.vibrate = new long[]{100, 250, 100, 500};
//
//        //id (numero único) que identifica esta notificação
//        nm.notify(R.string.app_name, n);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Ícone da notificação
                .setContentTitle(titulo)
                .setContentText(mensagem)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(p)
                .setTicker(mensagemBarraStatus)
                .setVibrate(new long[]{100, 250, 100, 500}); // Vibração

        // Exibe a notificação
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(1, builder.build());
    }
}