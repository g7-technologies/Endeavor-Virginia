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

import com.g7tech.endeavorvirginia.Model.ReportHistory;
import com.g7tech.endeavorvirginia.R;

import java.util.List;

public class ReportHistoryAdapter extends RecyclerView.Adapter<ReportHistoryAdapter.ReportViewHolder> {
    private Context mCtx;
    private List<ReportHistory> ReportHistoryList;

    public ReportHistoryAdapter(Context mCtx, List<ReportHistory> ReportHistoryList) {
        this.mCtx = mCtx;
        this.ReportHistoryList = ReportHistoryList;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.report_history_list, null);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        ReportHistory ReportHistory = ReportHistoryList.get(position);

        holder.id.setText(Integer.valueOf(ReportHistory.getId()).toString());
        holder.title.setText(String.valueOf(ReportHistory.getTitle()));
        holder.message.setText(String.valueOf(ReportHistory.getMessage()));
        holder.status.setText(String.valueOf(ReportHistory.getStatus()));
        holder.created_at.setText(String.valueOf(ReportHistory.getCreated_at()));
    }

    @Override
    public int getItemCount() {
        return ReportHistoryList.size();
    }


    class ReportViewHolder extends RecyclerView.ViewHolder {

        TextView id,title,message,status,created_at;
        ImageView info;

        public ReportViewHolder(View itemView) {
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
                        ReportHistory clickedDataItem = ReportHistoryList.get(pos);

                        final Dialog dialog = new Dialog(v.getContext());
                        dialog.setContentView(R.layout.custom_dialog);
                        dialog.setTitle("Report History");
                        TextView dialog_title = dialog.findViewById(R.id.dialog_title);
                        TextView dialog_message = dialog.findViewById(R.id.dialog_message);
                        TextView dialog_status = dialog.findViewById(R.id.dialog_status);
                        TextView dialog_created_at = dialog.findViewById(R.id.dialog_created_at);
                        Button buttonOk = dialog.findViewById(R.id.buttonOk);
                        dialog_title.setText("Title: "+((ReportHistory)clickedDataItem).getTitle());
                        dialog_message.setText("Message:\n"+((ReportHistory)clickedDataItem).getMessage());
                        dialog_status.setText("Status: "+((ReportHistory)clickedDataItem).getStatus());
                        dialog_created_at.setText("Reported On: "+((ReportHistory)clickedDataItem).getCreated_at());
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