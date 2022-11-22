package com.g7tech.endeavorvirginia.Helper_Classes;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.g7tech.endeavorvirginia.Model.RentalHistory;
import com.g7tech.endeavorvirginia.R;

import java.util.List;

public class RentalHistoryAdapter extends RecyclerView.Adapter<RentalHistoryAdapter.RentalViewHolder> {
    private Context mCtx;
    private List<RentalHistory> RentalHistoryList;

    public RentalHistoryAdapter(Context mCtx, List<RentalHistory> RentalHistoryList) {
        this.mCtx = mCtx;
        this.RentalHistoryList = RentalHistoryList;
    }

    @Override
    public RentalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.rental_history_list, null);
        return new RentalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RentalViewHolder holder, int position) {
        RentalHistory RentalHistory = RentalHistoryList.get(position);

        holder.id.setText(Integer.valueOf(RentalHistory.getId()).toString());
        holder.title.setText(String.valueOf(RentalHistory.getTitle()));
        holder.message.setText(String.valueOf(RentalHistory.getMessage()));
        holder.status.setText(Integer.valueOf(RentalHistory.getStatus()).toString());
        holder.created_at.setText(String.valueOf(RentalHistory.getCreated_at()));
    }

    @Override
    public int getItemCount() {
        return RentalHistoryList.size();
    }


    class RentalViewHolder extends RecyclerView.ViewHolder {

        TextView id,title,message,status,created_at;
        ImageView info;

        public RentalViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);
            status = itemView.findViewById(R.id.status);
            created_at = itemView.findViewById(R.id.created_at);
            info = itemView.findViewById(R.id.info);


            info.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        RentalHistory clickedDataItem = RentalHistoryList.get(pos);

                        final Dialog dialog = new Dialog(v.getContext());
                        dialog.setContentView(R.layout.custom_dialog);
                        dialog.setTitle("Rental History");
                        TextView dialog_title = dialog.findViewById(R.id.dialog_title);
                        TextView dialog_message = dialog.findViewById(R.id.dialog_message);
                        TextView dialog_status = dialog.findViewById(R.id.dialog_status);
                        TextView dialog_created_at = dialog.findViewById(R.id.dialog_created_at);
                        Button buttonOk = dialog.findViewById(R.id.buttonOk);
                        dialog_title.setText("Address: "+((RentalHistory)clickedDataItem).getTitle());
                        dialog_message.setText("Transaction Id:\n"+((RentalHistory)clickedDataItem).getMessage());
                        dialog_status.setText("Amount: "+((RentalHistory)clickedDataItem).getStatus());
                        dialog_created_at.setText("Paid On: "+((RentalHistory)clickedDataItem).getCreated_at());
                        dialog.show();
                        buttonOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                }
            });
        }
    }
}