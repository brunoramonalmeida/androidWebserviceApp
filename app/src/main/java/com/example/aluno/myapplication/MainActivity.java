package com.example.aluno.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Post>> {

    ListView mListView;

    ArrayList<String> dados = new ArrayList<>();

    ArrayAdapter<String> adapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    PostService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.lista);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,dados);

        mListView.setAdapter(adapter);

        service = retrofit.create(PostService.class);

        Call<List<Post>> call = service.listar();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (response.isSuccessful()) {
            List<Post> resposta = response.body();
            atualizarLista(resposta);
        } else {
            Toast.makeText(getApplicationContext(),"Erro na requisição",Toast.LENGTH_LONG);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        t.printStackTrace();
    }

    void atualizarLista(List<Post> lista) {
        for (Post p: lista) {
            dados.add(p.title);
        }
    }
}
