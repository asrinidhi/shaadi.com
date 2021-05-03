package com.sri.shaadi.requests;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sri.shaadi.R;

import java.util.ArrayList;

/**
 * @author Srinidhi on 02,May,2021
 */
class RecyclerMatchesAdapter extends RecyclerView.Adapter<RecyclerMatchesAdapter.ViewHolder> {

    private ArrayList<MatchRequest> matchArrayList;
    Context context;
    CallbackListener callback;

    public RecyclerMatchesAdapter(Context context, ArrayList<MatchRequest> matchArrayList, CallbackListener callback) {
        this.matchArrayList = matchArrayList;
        this.context = context;
        this.callback = callback;
    }

    public void addData(ArrayList<MatchRequest> matchArrayList) {
        this.matchArrayList = matchArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_matches_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textName.setText(matchArrayList.get(position).getName());
        holder.textProfileDetails.setText(matchArrayList.get(position).getAge() + " yrs, " + matchArrayList.get(position).getHeight() + " " + matchArrayList.get(position).getWork() + "\n" + matchArrayList.get(position).getAddress());
        switch (matchArrayList.get(position).getStatus()) {
            case 1:
                holder.acceptDeclineLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.acceptDeclineLayout.setVisibility(View.GONE);
                holder.textStatusMessage.setVisibility(View.VISIBLE);
                holder.textStatusMessage.setTextColor(context.getResources().getColor(R.color.color_green));
                holder.textStatusMessage.setText(matchArrayList.get(position).getName() + " " + context.getResources().getString(R.string.accepted_request));
                break;
            case 3:
                holder.acceptDeclineLayout.setVisibility(View.GONE);
                holder.textStatusMessage.setVisibility(View.VISIBLE);
                holder.textStatusMessage.setTextColor(context.getResources().getColor(R.color.color_red));
                holder.textStatusMessage.setText(matchArrayList.get(position).getName() + " " + context.getResources().getString(R.string.declined_request));
                break;
        }

        holder.imgAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onAcceptClick(matchArrayList.get(position), position);
            }
        });

        holder.imgDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onDeclineClick(matchArrayList.get(position), position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return matchArrayList.size();
    }

    public interface CallbackListener {
        public void onAcceptClick(MatchRequest matchRequest, int position);

        public void onDeclineClick(MatchRequest matchRequest, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textProfileDetails, textStatusMessage;
        public ImageView imgAccept, imgDecline;
        public LinearLayout acceptDeclineLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textName = (TextView) itemView.findViewById(R.id.text_profile_name);
            this.textProfileDetails = (TextView) itemView.findViewById(R.id.text_profile_details);
            this.textStatusMessage = (TextView) itemView.findViewById(R.id.text_status_message);
            acceptDeclineLayout = (LinearLayout) itemView.findViewById(R.id.ll_accept_decline);
            imgAccept = (ImageView) itemView.findViewById(R.id.img_accept);
            imgDecline = (ImageView) itemView.findViewById(R.id.img_decline);
        }
    }
}