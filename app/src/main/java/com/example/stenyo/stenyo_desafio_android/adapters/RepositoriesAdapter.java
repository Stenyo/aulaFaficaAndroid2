package com.example.stenyo.stenyo_desafio_android.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stenyo.stenyo_desafio_android.R;
import com.example.stenyo.stenyo_desafio_android.activitys.RequestActivity;
import com.example.stenyo.stenyo_desafio_android.models.Items;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.ViewHolder> {

    private List<Items> data;

    public RepositoriesAdapter(List<Items> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_repositories, parent, false);
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

        @BindView(R.id.fork_textview)
        IconTextView forkTextview;

        @BindView(R.id.stars_textview)
        IconTextView starsTextview;

        @BindView(R.id.owner_name_textview)
        TextView ownerNameTextview;

        @BindView(R.id.i_imageView)
        ImageView iImage;

        @BindView(R.id.cardview_repositories)
        CardView cardviewRepositories;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(final Items item) {

            nameText.setText(item.name);
            descriptionTextview.setText(item.description);
            forkTextview.setText("{fa-code-fork} " + String.valueOf(item.forksCount));
            starsTextview.setText("{fa-star} " + String.valueOf(item.stargazersCount));

            Glide.with(itemView.getContext()).load(item.owner.avatarUrl).into(iImage);
            ownerNameTextview.setText(item.owner.login);

            cardviewRepositories.setOnClickListener(v -> {
                Intent i = new Intent(v.getContext(), RequestActivity.class);
                i.putExtra(RequestActivity.EXTRA_NAME, item.name);
                i.putExtra(RequestActivity.EXTRA_OWNER, item.owner.login);
                ((Activity) v.getContext()).startActivity(i);
            });
        }
    }

}
