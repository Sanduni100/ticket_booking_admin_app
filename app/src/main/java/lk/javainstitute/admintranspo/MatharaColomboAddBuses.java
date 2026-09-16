package lk.javainstitute.admintranspo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MatharaColomboAddBuses extends AppCompatActivity {

    EditText fromEditText;
    EditText toEditText;
    EditText priceEditText;
    EditText tTimeEditText;
    EditText rTimeEditText;
    EditText dateEditText;
    String strFrom;
    String strTo;
    String strPrice;
    String strtTime;
    String strrTime;
    String strDate;
    Button saveButton;
    Button btnBack;
    ImageButton calendarButton;

    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathara_colombo_add_buses);

        firestore = FirebaseFirestore.getInstance();

        fromEditText = findViewById(R.id.fromEditText);
        toEditText = findViewById(R.id.toEditText);
        priceEditText = findViewById(R.id.priceEditText);
        tTimeEditText = findViewById(R.id.tTimeEditText);
        rTimeEditText = findViewById(R.id.rTimeEditText);
        dateEditText = findViewById(R.id.dateEditText);
        saveButton = findViewById(R.id.saveGCButton);
        calendarButton = findViewById(R.id.calendarButton);
        btnBack = findViewById(R.id.btnBack);


        calendarButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select Travel Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override

                    public void onPositiveButtonClick(Long selection) {

                        strDate =new SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(new Date(selection));
                        dateEditText.setText(MessageFormat.format("{0}",strDate));
                    }
                });
                materialDatePicker.show(getSupportFragmentManager(), "tag");
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strFrom = fromEditText.getText().toString().trim();
                strTo = toEditText.getText().toString().trim();
                strPrice = priceEditText.getText().toString().trim();
                strrTime = rTimeEditText.getText().toString().trim();
                strtTime = tTimeEditText.getText().toString().trim();
                strDate = dateEditText.getText().toString().trim();

                Map<String, String> matharaColombo = new HashMap<>();
                matharaColombo.put("pick", strFrom);
                matharaColombo.put("drop", strTo);
                matharaColombo.put("price", strPrice);
                matharaColombo.put("rTime", strrTime);
                matharaColombo.put("tTime", strtTime);
                matharaColombo.put("date", strDate);

                firestore.collection("MatharaColombo").document().set(matharaColombo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(MatharaColomboAddBuses.this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MatharaColomboAddBuses.this, MainActivity.class);
                        startActivity(intent);

                    }
                });

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MatharaColomboAddBuses.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}