package education.hry.pkl.cricket11.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

//  Created by Govind Kumar on 6/5/2020.
//          govind224556@gmail.com
public class NetworkUtil {

    public static String getConnectivityStatusString(Context context) {
        String status = null;
        Boolean internetHaiYaNahi;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enabled";
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data enabled";
                return status;
            }

        } else {
            internetHaiYaNahi=true;
            status = "No internet is available";
            return status;
        }
        return status;
    }


    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        /* NetworkInfo is deprecated in API 29 so we have to check separately for higher API Levels */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Network network = cm.getActiveNetwork();
            if (network == null) {
                return false;
            }
            NetworkCapabilities networkCapabilities = cm.getNetworkCapabilities(network);
            if (networkCapabilities == null) {
                return false;
            }
            boolean isInternetSuspended = !networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED);
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                    && !isInternetSuspended;
        } else {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
}



