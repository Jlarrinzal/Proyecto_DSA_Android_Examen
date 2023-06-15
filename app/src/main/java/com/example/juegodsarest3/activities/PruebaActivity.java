package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.juegodsarest3.models.Prueba;
import com.example.juegodsarest3.models.Swagger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.juegodsarest3.R;

public class PruebaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdaptadorPrueba adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar theProgressBar;
    private final String TAG = InicialActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        recyclerView = (RecyclerView) findViewById(R.id.my_new_recycler_view_prueba);
        theProgressBar = (ProgressBar) findViewById(R.id.progressBarPrueba);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdaptadorPrueba();
        recyclerView.setAdapter(adapter);
        doApiCall();


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                            target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        adapter.remove(viewHolder.getAdapterPosition());
                    }
                };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void doApiCall() {
        Swagger swagger = Swagger.retrofit.create(Swagger.class);
        Call<List<Prueba>> call = swagger.getPrueba();

        theProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<Prueba>>() {
            @Override
            public void onResponse(Call<List<Prueba>> call, Response<List<Prueba>> response) {
                theProgressBar.setVisibility(View.INVISIBLE);

                adapter.setData(response.body());


            }
            @Override
            public void onFailure(Call<List<Prueba>> call, Throwable t) {


                String msg = "Error con el retrofit: "+t.toString();
                Log.d(TAG,msg);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            }
        });
    }
}