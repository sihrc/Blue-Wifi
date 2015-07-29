package io.sihrc.blue_wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

import io.sihrc.blue_wifi.fragments.WifiFragment;
import io.sihrc.blue_wifi.fragments.WifiListFragment;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WifiFragment fragment = WifiListFragment.getInstance();
        getSupportFragmentManager().beginTransaction()
            .add(R.id.main_fragment_view, fragment)
            .addToBackStack(fragment.getName())
            .commit();
    }

    void read() {
        /*Get the WifiService */
        WifiManager wifi = (WifiManager) getSystemService(WIFI_SERVICE);

        /*Get All WIfi configurations*/
        List<WifiConfiguration> configList = wifi.getConfiguredNetworks();
        /*Now we need to search appropriate configuration i.e. with name SSID_Name*/
        for (int i = 0; i < configList.size(); i++) {
            if (configList.get(i).SSID.contentEquals("\"WesternDigital\"")) {
                /*We found the appropriate config now read all config details*/
                Iterator<WifiConfiguration> iter = configList.iterator();
                WifiConfiguration config = configList.get(i);

                Log.d("WifiPreference", "SSID" + config.SSID);
                Log.d("WifiPreference", "PASSWORD" + config.preSharedKey);
                Log.d("WifiPreference", "ALLOWED ALGORITHMS");
                Log.d("WifiPreference", "LEAP" + config.allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.LEAP));
                Log.d("WifiPreference", "OPEN" + config.allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.OPEN));
                Log.d("WifiPreference", "SHARED" + config.allowedAuthAlgorithms.get(WifiConfiguration.AuthAlgorithm.SHARED));
                Log.d("WifiPreference", "GROUP CIPHERS");
                Log.d("WifiPreference", "CCMP" + config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.CCMP));
                Log.d("WifiPreference", "TKIP" + config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.TKIP));
                Log.d("WifiPreference", "WEP104" + config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.WEP104));
                Log.d("WifiPreference", "WEP40" + config.allowedGroupCiphers.get(WifiConfiguration.GroupCipher.WEP40));
                Log.d("WifiPreference", "KEYMGMT");
                Log.d("WifiPreference", "IEEE8021X" + config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.IEEE8021X));
                Log.d("WifiPreference", "NONE" + config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.NONE));
                Log.d("WifiPreference", "WPA_EAP" + config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_EAP));
                Log.d("WifiPreference", "WPA_PSK" + config.allowedKeyManagement.get(WifiConfiguration.KeyMgmt.WPA_PSK));
                Log.d("WifiPreference", "PairWiseCipher");
                Log.d("WifiPreference", "CCMP" + config.allowedPairwiseCiphers.get(WifiConfiguration.PairwiseCipher.CCMP));
                Log.d("WifiPreference", "NONE" + config.allowedPairwiseCiphers.get(WifiConfiguration.PairwiseCipher.NONE));
                Log.d("WifiPreference", "TKIP" + config.allowedPairwiseCiphers.get(WifiConfiguration.PairwiseCipher.TKIP));
                Log.d("WifiPreference", "Protocols");
                Log.d("WifiPreference", "RSN" + config.allowedProtocols.get(WifiConfiguration.Protocol.RSN));
                Log.d("WifiPreference", "WPA" + config.allowedProtocols.get(WifiConfiguration.Protocol.WPA));
                Log.d("WifiPreference", "WEP Key Strings");
                String[] wepKeys = config.wepKeys;
                Log.d("WifiPreference", "WEP KEY 0" + wepKeys[0]);
                Log.d("WifiPreference", "WEP KEY 1" + wepKeys[1]);
                Log.d("WifiPreference", "WEP KEY 2" + wepKeys[2]);
                Log.d("WifiPreference", "WEP KEY 3" + wepKeys[3]);
            }
        }
    }
}
