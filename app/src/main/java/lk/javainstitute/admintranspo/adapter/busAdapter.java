package lk.javainstitute.admintranspo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lk.javainstitute.admintranspo.R;
import lk.javainstitute.admintranspo.model.bus;

public class busAdapter extends RecyclerView.Adapter<busAdapter.MyViewHolder> {

    Context context;
    TextView tvBookingDate;
    ArrayList<bus> busArrayList;
    Intent intent;

    public busAdapter(Context context, ArrayList<bus> busArrayList) {
        this.context = context;
        this.busArrayList = busArrayList;
        this.tvBookingDate = tvBookingDate;

    }


    @NonNull
    @Override
    public busAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull busAdapter.MyViewHolder holder, int position) {

        bus bus = busArrayList.get(position);
        holder.tv_rTime.setText(bus.getrTime());
        holder.tv_tTime.setText(bus.gettTime());
        holder.tv_Pick.setText(bus.getPick());
        holder.tv_Drop.setText(bus.getDrop());
        holder.tv_Price.setText(bus.getPrice());
        intent = ((Activity) context).getIntent();


        holder.editicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strDate = tvBookingDate.getText().toString();
//                intent = new Intent(v.getContext(), ViewBusesActivity.class);
//                intent.putExtra("pick", bus.getPick());
//                intent.putExtra("drop", bus.getDrop());
//                intent.putExtra("rTime", bus.getrTime());
//                intent.putExtra("price", bus.getPrice());
//                intent.putExtra("date", strDate);
//                context.startActivity(intent);




//                String strDate = tvBookingDate.getText().toString();
////                intent = new Intent(v.getContext(), ViewBusesActivity.class);
//                Intent intent = new Intent(context, ViewBusesActivity.class);
//                intent.putExtra("pick", bus.getPick().toString());
//                intent.putExtra("drop", bus.getDrop().toString());
//                intent.putExtra("time", bus.getrTime().toString());
//                intent.putExtra("price", bus.getPrice().toString());
//                intent.putExtra("date", strDate);
//                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return busArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rTime, tv_tTime, tv_Pick, tv_Drop, tv_Price;
        Button editicon, deleteicon;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tv_rTime = itemView.findViewById(R.id.rvTrip_rTimeID);
            tv_tTime = itemView.findViewById(R.id.rvTrip_tTimeID);
            tv_Pick = itemView.findViewById(R.id.rvTrip_PickID);
            tv_Drop = itemView.findViewById(R.id.rvTrip_tDropID);
            tv_Price = itemView.findViewById(R.id.rvTrip_PriceID);
            editicon = itemView.findViewById(R.id.editicon);
            deleteicon = itemView.findViewById(R.id.deleteicon);
        }
    }
}


