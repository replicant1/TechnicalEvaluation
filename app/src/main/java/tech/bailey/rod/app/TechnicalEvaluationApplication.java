package tech.bailey.rod.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * The application representing all of the Technical Evaluation app. This class exists solely for
 * the purposes of providing global access to the contex without having to continually pass it
 * around.
 */
public class TechnicalEvaluationApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        context = base;
    }
}
