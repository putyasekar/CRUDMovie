package com.diki.idn.crudmovie.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.diki.idn.crudmovie.R;
import com.diki.idn.crudmovie.adapter.MainAdapter;
import com.diki.idn.crudmovie.model.ResponseReadMovie;
import com.diki.idn.crudmovie.model.UserItem;
import com.diki.idn.crudmovie.network.InterfaceClient;
import com.diki.idn.crudmovie.network.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadDataMovie extends AppCompatActivity {
    private MainAdapter mainAdapter;
    private InterfaceClient interfaceClient;
    private RecyclerView rvMovie;
    private List<UserItem> results = new ArrayList<>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data_movie);

        rvMovie = findViewById(R.id.rv_movie);
        interfaceClient = RetrofitConfig.creatService(InterfaceClient.class);

        mainAdapter = new MainAdapter(this, results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvMovie.setLayoutManager(layoutManager);
        context = this;

        loadMovie();
    }

    private void loadMovie() {
        Call<ResponseReadMovie> request = interfaceClient.getMovie("user", "read", "title", "description");
        request.enqueue(new Callback<ResponseReadMovie>() {
            @Override
            public void onResponse(Call<ResponseReadMovie> call, Response<ResponseReadMovie> response) {
                results = response.body().getUser();
                rvMovie.setAdapter(new MainAdapter(context,results));
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseReadMovie> call, Throwable t) {
                Log.e("Connection Failed", t.toString());

            }
        });
    }
}
