package tech.bailey.rod.app;

import android.app.Application;
import android.content.Context;

/**
 * The application representing all of SonicDX. This class exists solely for the purposes of providing application-wide
 * access to the application via SonicApplication.context;
 */

public class TechnicalEvaluationApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
