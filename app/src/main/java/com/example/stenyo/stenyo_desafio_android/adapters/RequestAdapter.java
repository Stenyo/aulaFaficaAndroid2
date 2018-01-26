package com.example.stenyo.stenyo_desafio_android.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stenyo.stenyo_desafio_android.R;
import com.example.stenyo.stenyo_desafio_android.activitys.RequestActivity;
import com.example.stenyo.stenyo_desafio_android.models.Items;
import com.example.stenyo.stenyo_desafio_android.models.PullRequest;
import com.joanzapata.iconify.widget.IconTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private List<PullRequest> data;

    public RequestAdapter(List<PullRequest> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_request, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name_textview)
        TextView nameText;

        @BindView(R.id.description_textview)
        TextView descriptionTextview;

        @BindView(R.id.owner_name_textview)
        TextView ownerNameTextview;

        @BindView(R.id.i_imageView)
        ImageView iImage;

        @BindView(R.id.cardview_request)
        CardView cardviewRequest;

        @BindView(R.id.create_textview)
        TextView createTextview;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(PullRequest pullRequest) {

            nameText.setText(pullRequest.title);
            descriptionTextview.setText(pullRequest.body);

            Glide.with(itemView.getContext()).load(pullRequest.user.avatarUrl).into(iImage);
            ownerNameTextview.setText(pullRequest.user.login);

            cardviewRequest.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setData(Uri.parse(pullRequest.htmlUrl));
                v.getContext().startActivity(i);
            });

            SimpleDateFormat oldFormat =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat newFormat =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            try {
                createTextview.setText(newFormat.format(oldFormat.parse(pullRequest.createdAt)));
            } catch (ParseException e) {
                createTextview.setVisibility(View.GONE);
                e.printStackTrace();
            }
        }
    }

}
