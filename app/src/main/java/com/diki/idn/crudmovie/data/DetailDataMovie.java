package com.diki.idn.crudmovie.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.diki.idn.crudmovie.R;
import com.diki.idn.crudmovie.model.ResponseDeleteMovie;
import com.diki.idn.crudmovie.network.InterfaceClient;
import com.diki.idn.crudmovie.network.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDataMovie extends AppCompatActivity {

    String txtTitle, txtDesc;
    TextView tvDetailTitle, tvDetailDesc;
    Button btnDelete;
    InterfaceClient interfaceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_movie);

        tvDetailTitle = findViewById(R.id.tv_detail_title);
        tvDetailDesc = findViewById(R.id.tv_detail_desc);

        txtTitle = getIntent().getStringExtra("title");
        txtDesc = getIntent().getStringExtra("description");

        tvDetailTitle.setText(txtTitle);
        tvDetailDesc.setText(txtDesc);


        //===============8
        btnDelete = findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceClient = RetrofitConfig.creatService(InterfaceClient.class);
                Call<ResponseDeleteMovie> request = interfaceClient.deleteMovie("title");
                request.enqueue(new Callback<ResponseDeleteMovie>() {
                    @Override
                    public void onResponse(Call<ResponseDeleteMovie> call, Response<ResponseDeleteMovie> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DetailDataMovie.this, "Success to Delete", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailDataMovie.this, ReadDataMovie.class));
                        } else {
                            Toast.makeText(DetailDataMovie.this, "Fail to Delete", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDeleteMovie> call, Throwable t) {
                        Toast.makeText(DetailDataMovie.this, "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
