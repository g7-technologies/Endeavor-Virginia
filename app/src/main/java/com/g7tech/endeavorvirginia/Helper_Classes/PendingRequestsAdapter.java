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

import com.g7tech.endeavorvirginia.Model.PendingRequests;
import com.g7tech.endeavorvirginia.R;

import java.util.List;


public class PendingRequestsAdapter extends RecyclerView.Adapter<PendingRequestsAdapter.PendingRequestsViewHolder> {
    private Context mCtx;
    private List<PendingRequests> PendingRequestsList;

    public PendingRequestsAdapter(Context mCtx, List<PendingRequests> PendingRequestsList) {
        this.mCtx = mCtx;
        this.PendingRequestsList = PendingRequestsList;
    }

    @Override
    public PendingRequestsAdapter.PendingRequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.pending_request_list, null);
        return new PendingRequestsAdapter.PendingRequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PendingRequestsAdapter.PendingRequestsViewHolder holder, int position) {
        PendingRequests PendingRequests = PendingRequestsList.get(position);

        holder.id.setText(Integer.valueOf(PendingRequests.getId()).toString());
        holder.title.setText(String.valueOf(PendingRequests.getTitle()));
        holder.message.setText(String.valueOf(PendingRequests.getMessage()));
        holder.status.setText(String.valueOf(PendingRequests.getStatus()));
        holder.created_at.setText(String.valueOf(PendingRequests.getCreated_at()));
    }

    @Override
    public int getItemCount() {
        return PendingRequestsList.size();
    }


    class PendingRequestsViewHolder extends RecyclerView.ViewHolder {

        TextView id,title,message,status,created_at;
        ImageView info;

        public PendingRequestsViewHolder(View itemView) {
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
                        PendingRequests clickedDataItem = PendingRequestsList.get(pos);

                        final Dialog dialog = new Dialog(v.getContext());
                        dialog.setContentView(R.layout.custom_dialog);
                        dialog.setTitle("Pending Requests");
                        TextView dialog_title = dialog.findViewById(R.id.dialog_title);
                        TextView dialog_message = dialog.findViewById(R.id.dialog_message);
                        TextView dialog_status = dialog.findViewById(R.id.dialog_status);
                        TextView dialog_created_at = dialog.findViewById(R.id.dialog_created_at);
                        Button buttonOk = dialog.findViewById(R.id.buttonOk);
                        dialog_title.setText("Title: "+((PendingRequests)clickedDataItem).getTitle());
                        dialog_message.setText("Message:\n"+((PendingRequests)clickedDataItem).getMessage());
                        dialog_status.setText("Status: "+((PendingRequests)clickedDataItem).getStatus());
                        dialog_created_at.setText("Reported On: "+((PendingRequests)clickedDataItem).getCreated_at());
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