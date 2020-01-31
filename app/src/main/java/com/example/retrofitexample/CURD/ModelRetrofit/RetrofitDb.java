package com.example.retrofitexample.CURD.ModelRetrofit;

public interface RetrofitDb {

    void insertInToRetrofit(final String title, final String note, final int color);

    void editInRetrofit(final int id, final String title, final String note, final int color);

    void deleteFromRetrofit(final int id);

    void getAllFromRetrofit();
}
