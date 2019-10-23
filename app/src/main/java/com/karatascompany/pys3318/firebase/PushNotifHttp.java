package com.karatascompany.pys3318.firebase;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 * Created by azizmahmud on 3.4.2018.
 */

public class PushNotifHttp extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... strings) {


        try {
            String tokenId= strings[0];
            String authKey = "AAAAQj4kfdA:APA91bFafkX6Ad1YKw9jZBnf_xPKZllXcNk7H7LSCOfO8S6pEXXbdq1smFdIZOU9mQfDBOCIDsKASGsRoay76O3joIpKljeFV1L2skLRaTP5tuuTaB5g3MgfCeqdKveVml09M21ggY0T";//AUTH_KEY_FCM; // You FCM AUTH key
            //  String FMCurl = API_URL_FCM;
           // String tokenId = "fvmmcfXAx-o:APA91bGT8oFUve6K0sOTJMMqm3-YS2M5lOxfIhMYlpe7mPmPI1armrUqfegB6sruoay0WzdYbkeIIgP2Iqr-BQvHjz7Hxd8ItxyRhU0F0q1kWTwepegNccO_V7quOB4Wnhv9jnZHQSQZ";

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
            post.setHeader("Content-type", "application/json");
            post.setHeader("Authorization", "key=" + authKey);

            JSONObject message = new JSONObject();
            message.put("to",  tokenId);  // FirebaseInstanceId.getInstance().getToken() --> kendi token bilgisi telefonun
            //message.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", strings[1]);
            notification.put("body", strings[2]); //"notifidsad do Java"

            message.put("notification", notification);

            post.setEntity(new StringEntity(message.toString(), "UTF-8"));
            HttpResponse response = client.execute(post);
            System.out.println(response);
            System.out.println(message);
        }
        catch (Exception e){
            //Toast.makeText(, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return null;
    }

}
