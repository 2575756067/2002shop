package com.example.shopkuang.ui.Details.favorites;

import android.content.Context;

import java.security.SecureRandom;

import io.realm.RealmConfiguration;


public class Realms {

    public static io.realm.Realm getRealm(Context context){
        byte[] bytes = new byte[1024];
        new SecureRandom().nextBytes(bytes);
        io.realm.Realm.init(context);

        //数据库迁移
        //Migration migration = new Migration();
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("db.realm")
                .schemaVersion(2)
                //.migration(migration)
                .deleteRealmIfMigrationNeeded()
                .build();
        return io.realm.Realm.getInstance(config);
    }



}
