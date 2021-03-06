package com.bquiz.raipur.ecellapp2k17.splash_screen.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bquiz.raipur.ecellapp2k17.BuildConfig;
import com.bquiz.raipur.ecellapp2k17.helper.MyApplication;
import com.bquiz.raipur.ecellapp2k17.helper.SharedPrefs;
import com.bquiz.raipur.ecellapp2k17.home.Home;
import com.bquiz.raipur.ecellapp2k17.R;
import com.bquiz.raipur.ecellapp2k17.login.view.LoginActivity;
import com.bquiz.raipur.ecellapp2k17.splash_screen.model.RetrofitSplashScreenProvider;
import com.bquiz.raipur.ecellapp2k17.splash_screen.model.data.SplashScreenData;
import com.bquiz.raipur.ecellapp2k17.splash_screen.presenter.SplashScreenPresenter;
import com.bquiz.raipur.ecellapp2k17.splash_screen.presenter.SplashScreenPresenterImpl;

public class SplashScreen extends Activity implements  SplashScreenView{

    SharedPrefs sharedPrefs;
    MyApplication myApplication;
    ProgressBar progressBar,splashProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPrefs = new SharedPrefs(this);
        splashProgressBar=(ProgressBar)findViewById(R.id.splash_progress_bar);
        Log.d("Splash sceen",""+MyApplication.fcm_token);
        SplashScreenPresenter splashScreenPresenter = new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());
        splashScreenPresenter.insertFcm(MyApplication.fcm_token,sharedPrefs.getAccessToken());
    //    splashScreenPresenter.insertFcm(MyApplication.fcm_token,sharedPrefs.getAccessToken());

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(SplashScreen.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fcmInsertStatus(final SplashScreenData splashScreenData) {

        float i = splashScreenData.getVersion();
        Log.d("log",""+BuildConfig.VERSION_CODE);
        if (i > BuildConfig.VERSION_CODE) {
            final Dialog dialog = new Dialog(SplashScreen.this);
            dialog.setContentView(R.layout.activity_rules__dialog_box);
            Button btn = (Button) dialog.findViewById(R.id.dialog_button);
            progressBar = (ProgressBar) dialog.findViewById(R.id.progress_bar_dialog);
            TextView rules = (TextView) dialog.findViewById(R.id.rules5);

            rules.setText("Please Update the app for Better experience");
            dialog.setCancelable(false);
            dialog.setTitle("App Update");
            btn.setText("Update");
            dialog.show();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(splashScreenData.getUrl())));

                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    }
                }
            });
        }
        else if (splashScreenData.isSuccess()) {
            sharedPrefs.setFCM(MyApplication.fcm_token);

            if (sharedPrefs.isLoggedIn()) {
                Intent in = new Intent(SplashScreen.this, Home.class);
                startActivity(in);
                finish();
            } else {
                Intent signIn = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(signIn);
                finish();
            }

        }
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            splashProgressBar.setVisibility(View.VISIBLE);
        } else {
            splashProgressBar.setVisibility(View.GONE);
        }
    }
}


