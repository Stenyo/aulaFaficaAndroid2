package com.example.stenyo.stenyo_desafio_android.activitys;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.stenyo.stenyo_desafio_android.App;
import com.example.stenyo.stenyo_desafio_android.R;
import com.example.stenyo.stenyo_desafio_android.adapters.RepositoriesAdapter;
import com.example.stenyo.stenyo_desafio_android.api.service.RepositoriesService;
import com.example.stenyo.stenyo_desafio_android.models.Items;
import com.example.stenyo.stenyo_desafio_android.models.Repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    int PAGE_SIZE = 0;
    int PAGE_CURRENT = 1;
    int INDEX = 0;
    boolean LOADING = true;

    List<Items> data = new ArrayList<>();

    Unbinder unbinder;

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);


    @BindView(R.id.repositories_recyclerview)
    RecyclerView repositoriesRecyclerView;

    @BindView(R.id.constraint_loading)
    ConstraintLayout constraintLoading;

    @Inject
    RepositoriesService repositoriesService;

    private RepositoriesAdapter repositoriesAdapter;



    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if ((PAGE_SIZE > PAGE_CURRENT) && !LOADING) {

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();

                if (firstVisibleItemPosition + visibleItemCount >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    INDEX = (firstVisibleItemPosition + visibleItemCount) - 1;
                    loadMoreItems(mLayoutManager);

                }
            }
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        ((App) this.getApplication()).getAPIComponent().inject(this);

        if (savedInstanceState != null) {
            INDEX = savedInstanceState.getInt("INDEX");
            PAGE_SIZE = savedInstanceState.getInt("PAGE_SIZE");
            PAGE_CURRENT = savedInstanceState.getInt("PAGE_CURRENT");
            data = savedInstanceState.getParcelableArrayList("data");
            setView();
        } else {
            loadMoreItems(mLayoutManager);

        }
    }

    private void loadMoreItems(final RecyclerView.LayoutManager mLayoutManager) {
        LOADING = true;
        constraintLoading.setVisibility(View.VISIBLE);
        repositoriesService
                .list(PAGE_CURRENT)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<Repositories>>() {
                    @Override
                    public void accept(@NonNull Response<Repositories> response) throws Exception {
                        Repositories resp = response.body();
                        data.addAll(resp.items);
                        if (response.isSuccessful() && data != null) {
                            setView();
                            ++PAGE_CURRENT;
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.v("Error servidor", throwable.getMessage());
                    }
                });
    }

    public void setView() {
        repositoriesAdapter = new RepositoriesAdapter(data);
        repositoriesRecyclerView.setAdapter(repositoriesAdapter);
        PAGE_SIZE = data.size();
        LOADING = false;
        constraintLoading.setVisibility(View.GONE);
        mLayoutManager.scrollToPosition(INDEX);

        repositoriesRecyclerView.setLayoutManager(this.mLayoutManager);
        repositoriesRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        int visibleItemCount = mLayoutManager.getChildCount();
        int firstVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        INDEX = (firstVisibleItemPosition + visibleItemCount) - 1;
        outState.putInt("INDEX", INDEX);
        outState.putInt("PAGE_SIZE", PAGE_SIZE);
        outState.putInt("PAGE_CURRENT", PAGE_CURRENT);
        outState.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) data);
        super.onSaveInstanceState(outState);
    }

}
