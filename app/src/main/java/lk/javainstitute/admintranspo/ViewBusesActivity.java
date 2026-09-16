package lk.javainstitute.admintranspo;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import lk.javainstitute.admintranspo.adapter.busAdapter;
import lk.javainstitute.admintranspo.model.bus;

public class ViewBusesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView tvBookFrom, tvBookTo, tvBookDate;
    ArrayList<bus> arrayList;
    busAdapter adapter;
    FirebaseFirestore db;
    String strGetFrom, strGetTo, strGetDate;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_buses);

        intent = getIntent();
        strGetFrom = intent.getStringExtra("pick");
        strGetTo = intent.getStringExtra("drop");
        strGetDate = intent.getStringExtra("date");


        recyclerView = findViewById(R.id.recy_tripID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvBookFrom = findViewById(R.id.tv_bookingFromID);
        tvBookTo = findViewById(R.id.tv_bookingToID);
        tvBookDate = findViewById(R.id.tv_bookingDateID);

        tvBookFrom.setText(strGetFrom);
        tvBookTo.setText(strGetTo);
        tvBookDate.setText(strGetDate);

        db = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<bus>();

        adapter = new busAdapter(ViewBusesActivity.this, arrayList);
        recyclerView.setAdapter(adapter);

        if (strGetFrom.matches("Galle") && strGetTo.matches("Colombo")) {
            GalleColombo();
        } else if (strGetFrom.matches("Kandy") && strGetTo.matches("Colombo")) {
            KandyColombo();
        }
        GalleColombo();

    }


    private void KandyColombo() {

        db.collection("KandyColombo").orderBy("rTime", Query.Direction.ASCENDING).addSnapshotListener(new com.google.firebase.firestore.EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange dc: value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        bus currentBus = dc.getDocument().toObject(bus.class);

                        String date = currentBus.getDate();

                        if (strGetDate.equals(date)) {
                            arrayList.add(currentBus);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }



    private void GalleColombo() {
        db.collection("GalleColombo").orderBy("rTime", Query.Direction.ASCENDING).addSnapshotListener(new com.google.firebase.firestore.EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange dc: value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {
                        bus currentBus = dc.getDocument().toObject(bus.class);

                        String date = currentBus.getDate();

                        if (strGetDate.equals(date)) {
                            arrayList.add(currentBus);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }


    }















