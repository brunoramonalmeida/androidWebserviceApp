package com.example.aluno.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {
    @GET("produtos/lista")
    Call<List<Produto>> listProdutos();
}
