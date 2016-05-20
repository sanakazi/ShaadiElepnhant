package com.shaadielephant.shaadielephant;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.shaadielephant.shaadielepnhant.R;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ram on 2/3/2016.
 */
public class ShhadiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig =
                new TwitterAuthConfig(getResources().getString(R.string.twitter_consumer_key),
                        getResources().getString(R.string.twitter_consumer_secret));
        Fabric.with(this, new TwitterCore(authConfig));

    }

}
