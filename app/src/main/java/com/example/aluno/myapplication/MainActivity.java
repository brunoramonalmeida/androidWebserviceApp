package com.example.aluno.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Produto>> {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost/webservice/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ProdutoService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = retrofit.create(ProdutoService.class);

        Call<List<Produto>> call = service.listProdutos();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
        if (response.isSuccessful()) {
            List<Produto> produtos = response.body();

        } else {
            Toast.makeText(getApplicationContext(),"Erro na requisição",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onFailure(Call<List<Produto>> call, Throwable t) {
        t.printStackTrace();
    }
}
