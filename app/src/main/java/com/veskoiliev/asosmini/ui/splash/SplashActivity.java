package com.veskoiliev.asosmini.ui.splash;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.veskoiliev.asosmini.ui.main.MainActivity;
import com.veskoiliev.asosmini.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Bind(R.id.splash_progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        SplashPresenter presenter = new SplashPresenterImpl(this);
        presenter.onActivityCreated();
    }

    @Override
    public void showLoadingProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void displayError(String errorMessage) {
        mProgressBar.setVisibility(View.GONE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(errorMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SplashActivity.this.finish();
                    }
                });
        builder.show();
    }
}
