package lk.javainstitute.admintranspo;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logoImageView = findViewById(R.id.imgView_logo_rays);

        // Create an ObjectAnimator to animate the logo (rotating it 360 degrees)
        ObjectAnimator rotation = ObjectAnimator.ofFloat(logoImageView, "rotation", 0f, 360f);
        rotation.setDuration(SPLASH_DURATION);
        rotation.setInterpolator(new AccelerateDecelerateInterpolator());

        rotation.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DURATION);
    }
}