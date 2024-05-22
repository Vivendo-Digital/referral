package com.vivendo.plugin.referral;

import android.os.RemoteException;
import android.util.Log;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Referral")
public class ReferralPlugin extends Plugin {

    private Referral implementation = new Referral();

    private InstallReferrerClient referrerClient;

    @Override
    public void load() {
        referrerClient = InstallReferrerClient.newBuilder(getContext()).build();
    }

    @PluginMethod
    public void getInstallReferrer(PluginCall call) {
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                if (responseCode == InstallReferrerClient.InstallReferrerResponse.OK) {
                    try {
                        ReferrerDetails response = referrerClient.getInstallReferrer();
                        String referrerUrl = response.getInstallReferrer();
                        long clickTimestamp = response.getReferrerClickTimestampSeconds();
                        long installTimestamp = response.getInstallBeginTimestampSeconds();

                        JSObject ret = new JSObject();
                        ret.put("referrerUrl", referrerUrl);
                        ret.put("clickTimestamp", clickTimestamp);
                        ret.put("installTimestamp", installTimestamp);

                        call.resolve(ret);
                    } catch (RemoteException e) {
                        call.reject("Failed to get install referrer", e);
                    }
                } else {
                    call.reject("Install Referrer setup failed, response code: " + responseCode);
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                // Try to reconnect on next request
                Log.i("ReferrerDisconnected", "onInstallReferrerServiceDisconnected...");
            }
        });
    }
}
