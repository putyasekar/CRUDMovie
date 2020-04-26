package com.diki.idn.crudmovie.data;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diki.idn.crudmovie.R;
import com.diki.idn.crudmovie.model.ResponseInsertMovie;
import com.diki.idn.crudmovie.network.InterfaceClient;
import com.diki.idn.crudmovie.network.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateDataMovie extends AppCompatActivity {
    EditText etTitle, etDesc;
    Button btnInsert;
    Context context;
    InterfaceClient interfaceClient;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data_movie);

        context = this;
        interfaceClient = RetrofitConfig.creatService(InterfaceClient.class);

        dataInsertMovie();
    }

    private void dataInsertMovie() {
        etTitle = findViewById(R.id.et_title);
        etDesc = findViewById(R.id.et_desc);
        btnInsert = findViewById(R.id.btn_insert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(context, null, "Please Wait", true, false);

                String title = etTitle.getText().toString().trim();
                String description = etDesc.getText().toString().trim();

                Call<ResponseInsertMovie> request = interfaceClient.insertMovie("user", "insert", title, description);

                request.enqueue(new Callback<ResponseInsertMovie>() {
                    @Override
                    public void onResponse(Call<ResponseInsertMovie> call, Response<ResponseInsertMovie> response) {
                        if (response.isSuccessful()) {
                            Log.i("debug", "onResponse: Insert Success");
                            loading.dismiss();
                            Toast.makeText(context, "Moving Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(context, ReadDataMovie.class));
                            finish();
                        } else {
                            Toast.makeText(context, "Moving Fail", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseInsertMovie> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(context, "Error Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
