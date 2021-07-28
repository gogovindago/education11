package education.hry.pkl.cricket11.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.ui.splash.SplashActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.Config;


public class MyFirebaseMessagingService extends FirebaseMessagingService {


    String sendername = "";
    String title = "";
    CSPreferences app_preference;
    //Set the channel’s ID//
    public static final String CHANNEL_ID = "com.richestsoft.giftcardtrade.messages";
    NotificationUtils notificationUtils;
    RemoteMessage remoteMessage;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        Log.e("From: ", remoteMessage.getData().get("sendername"));

        //       Log.e("From: ", remoteMessage.getFrom());

        this.remoteMessage = remoteMessage;
        app_preference = new CSPreferences();
        notificationUtils = new NotificationUtils(getApplicationContext());
        if (remoteMessage == null)
            return;


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e("Notification Body: ", remoteMessage.getNotification().getBody());
            Map<String, String> data = remoteMessage.getData();
            sendername = data.get("moredata");
            title = data.get("name");

            Log.e("Notification Body: ", remoteMessage.getNotification().getBody());
            Log.e("Bodysender: ", sendername);
            Log.e("name: ", title);



            if(!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                handleNotification(sendername, remoteMessage);
            } else
                { sendNotification(remoteMessage);

                }



//            if (app_preference.getCHATSTATUS().equals("1")) {
//
//            } else {
//                sendNotification(remoteMessage);
//            }

           // handleNotification(sendername, remoteMessage);
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e("Data Payload: ", remoteMessage.getData().toString());

            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(json);
            } catch (Exception e) {
                //  Log.e("Exception: " , e.getMessage());
            }
        }

    }

    private void handleNotification(String message, RemoteMessage remoteMessage) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {

            sendername=remoteMessage.getData().get("moredata");
            title =remoteMessage.getData().get("name");
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("moredata", sendername);
            pushNotification.putExtra("name", title);
            pushNotification.setAction("com.ebookfrenzy.sendbroadcast");
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            //  NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            //  notificationUtils.playNotificationSound();

        } else {


            // If the app is in background, firebase itself handles the notification
        }
    }

    private void handleDataMessage(JSONObject json) {
        Log.e("push json: ", json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            sendername = data.getString("moredata");
            title = data.getString("name");
            Log.e("title: ", sendername);

            String senderid = data.getString("desiredid");
            String itemid = data.getString("itemid");

            //    boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            //  JSONObject payload = data.getJSONObject("payload");

            //        Log.e("title: " ,title);


            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {

                // app is in foreground, broadcast the push message
                Intent resultIntent = new Intent(getApplicationContext(), SplashActivity.class);
                resultIntent.putExtra("moredata", sendername);
                resultIntent.putExtra("name", title);
                startActivity(resultIntent);
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("moredata", sendername);

                title = data.getString("name");
                Log.e("title: ", sendername);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), SplashActivity.class);
                resultIntent.putExtra("moredata", sendername);

                title = data.getString("name");
                Log.e("title: ", sendername);
                Toast.makeText(this, sendername, Toast.LENGTH_SHORT).show();

                //      Log.e("slkjksldkj","sfggfgfg");

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, sendername, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, sendername, timestamp, resultIntent, imageUrl);
                }
            }


        } catch (JSONException e) {
            //   Log.e("Json Exception: ", e.getMessage());
        } catch (Exception e) {
            //   Log.e( "Exception: ", e.getMessage());
        }
    }

    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }

    public void sendNotification(RemoteMessage remoteMessage) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext());

        //Create the intent that’ll fire when the user taps the notification//
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);


        intent.putExtra("moredata", remoteMessage.getData().get("moredata"));
        intent.putExtra("name", remoteMessage.getData().get("name"));
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        //  intent.putExtra("",remoteMessage.getNotification())
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle(remoteMessage.getNotification().getTitle());
        mBuilder.setContentText(remoteMessage.getNotification().getBody());

        NotificationManager mNotificationManager =

                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            //  notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        mNotificationManager.cancel(10);
        mNotificationManager.notify(100, mBuilder.build());

    }
}
