package io.spenser.ribbit;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by spenser on 1/14/15.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, getProperties("parse", "applicationId"), getProperties("parse", "clientKey"));
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

    }

    private String getProperties(String propertiesFile, String key) {
        Resources resources = this.getResources();
        AssetManager assetManager = resources.getAssets();
        try {
            InputStream inputStream = assetManager.open(propertiesFile + ".properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ERROR", "IO Exception");
            return "";
        }
    }

}
