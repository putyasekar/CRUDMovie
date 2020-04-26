package com.diki.idn.crudmovie.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diki.idn.crudmovie.R;
import com.diki.idn.crudmovie.model.ResponseUpdateMovie;
import com.diki.idn.crudmovie.network.InterfaceClient;
import com.diki.idn.crudmovie.network.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDataMovie extends AppCompatActivity {
    Button btnUpdate;
    EditText etUpdateTitle, etUpdateDesc;
    InterfaceClient interfaceClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_movie);

        interfaceClient = RetrofitConfig.creatService(InterfaceClient.class);
        etUpdateTitle = findViewById(R.id.et_update_title);
        etUpdateDesc = findViewById(R.id.et_update_desc);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        etUpdateTitle.setText(title);
        etUpdateDesc.setText(description);

        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etUpdateTitle.getText().toString();
                String description = etUpdateDesc.getText().toString();

                Call<ResponseUpdateMovie> request = interfaceClient.updateMovie("user", "update", title, description);
                request.enqueue(new Callback<ResponseUpdateMovie>() {
                    @Override
                    public void onResponse(Call<ResponseUpdateMovie> call, Response<ResponseUpdateMovie> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(UpdateDataMovie.this, "Success to Update", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateDataMovie.this, ReadDataMovie.class));
                        } else {
                            Toast.makeText(UpdateDataMovie.this, "Fail to Update Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUpdateMovie> call, Throwable t) {
                        Toast.makeText(UpdateDataMovie.this, "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
