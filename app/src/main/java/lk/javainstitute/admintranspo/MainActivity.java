package lk.javainstitute.admintranspo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button galleColombo;
    Button matharaColombo;
    Button colomboKatunayake;
    Button meerigamaKurunagala;
    Button colomboMaththala;
    Button kandyColombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galleColombo = findViewById(R.id.galleColombo);
        matharaColombo = findViewById(R.id.matharaColombo);
        colomboKatunayake = findViewById(R.id.colomboKatunayake);
        meerigamaKurunagala = findViewById(R.id.meerigamaKurunagala);
        colomboMaththala = findViewById(R.id.colomboMaththala);
        kandyColombo = findViewById(R.id.colomboKandy);

        galleColombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GalleColomboAddBuses.class);
                startActivity(intent);
            }
        });

        matharaColombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MatharaColomboAddBuses.class);
                startActivity(intent);
            }
        });

        colomboKatunayake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColomboKatunayakeAddBuses.class);
                startActivity(intent);
            }
        });

        meerigamaKurunagala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MeerigamaKurunagalaAddBuses.class);
                startActivity(intent);
            }
        });

        colomboMaththala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColomboMaththalaAddBuses.class);
                startActivity(intent);
            }
        });

        kandyColombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KandyColomboAddBuses.class);
                startActivity(intent);
            }
        });


    }
}