package com.shaan.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DDzvPe7Uo9cisIFlRGbyBSzOJOE1026MO10AlIqh")
                // if defined
                .clientKey("on9xprviABfR9vrKn2ZcNgbBU88IG0GXmPBrTWHo")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
