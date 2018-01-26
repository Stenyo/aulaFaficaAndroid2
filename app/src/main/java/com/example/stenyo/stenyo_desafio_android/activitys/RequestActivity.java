package com.example.stenyo.stenyo_desafio_android.activitys;

import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.stenyo.stenyo_desafio_android.App;
import com.example.stenyo.stenyo_desafio_android.R;
import com.example.stenyo.stenyo_desafio_android.adapters.RepositoriesAdapter;
import com.example.stenyo.stenyo_desafio_android.adapters.RequestAdapter;
import com.example.stenyo.stenyo_desafio_android.api.service.RepositoriesService;
import com.example.stenyo.stenyo_desafio_android.models.PullRequest;

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

public class RequestActivity extends AppCompatActivity {

    public static final String EXTRA_OWNER = "owner";
    public static final String EXTRA_NAME = "name";

    Unbinder unbinder;

    String owner;
    String name;

    int PAGE_SIZE = 0;
    int PAGE_CURRENT = 1;
    int INDEX = 0;
    boolean LOADING = true;

    List<PullRequest> data = new ArrayList<>();


    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);


    @BindView(R.id.request_recyclerview)
    RecyclerView requestRecyclerView;

    @BindView(R.id.constraint_loading)
    ConstraintLayout constraintLoading;

    @Inject
    RepositoriesService repositoriesService;

    private RequestAdapter requestAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        unbinder = ButterKnife.bind(this);

        ((App) this.getApplication()).getAPIComponent().inject(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            owner = bundle.getString(EXTRA_OWNER);
            name = bundle.getString(EXTRA_NAME);
            if (savedInstanceState != null) {
                INDEX = bundle.getInt("INDEX");
                data = savedInstanceState.getParcelableArrayList("data");
                setView();
            }else{
                loadMoreItems(mLayoutManager);
            }
        }

    }

    private void loadMoreItems(final RecyclerView.LayoutManager mLayoutManager) {
        LOADING = true;
        constraintLoading.setVisibility(View.VISIBLE);
        repositoriesService
                .listRep(owner,name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<List<PullRequest>>>() {
                    @Override
                    public void accept(@NonNull Response<List<PullRequest>> response) throws Exception {
                        data.addAll(response.body());
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
        requestAdapter = new RequestAdapter(data);
        requestRecyclerView.setAdapter(requestAdapter);
        PAGE_SIZE = data.size();
        LOADING = false;
        constraintLoading.setVisibility(View.GONE);
        mLayoutManager.scrollToPosition(INDEX);

        requestRecyclerView.setLayoutManager(this.mLayoutManager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        int visibleItemCount = mLayoutManager.getChildCount();
        int firstVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        INDEX = (firstVisibleItemPosition + visibleItemCount) - 1;
        outState.putInt("INDEX", INDEX);
        outState.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) data);
        super.onSaveInstanceState(outState);
    }
}
