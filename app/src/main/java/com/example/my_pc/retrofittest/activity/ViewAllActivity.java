package com.example.my_pc.retrofittest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.my_pc.retrofittest.R;
import com.example.my_pc.retrofittest.model.Person;
import com.example.my_pc.retrofittest.model.PersonAdapter;
import com.example.my_pc.retrofittest.model.PopUpItem;
import com.example.my_pc.retrofittest.retrofit.MyRetrofit;
import com.example.my_pc.retrofittest.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);

        setTitle("Retrofit");

        initUI();
    }

    private void initUI() {


        personList = new ArrayList<Person>();
        recyclerView = findViewById(R.id.myRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        RetrofitService myService = MyRetrofit.getMyRetrofit().create(RetrofitService.class);


        Call<List<PopUpItem>> call = myService.getList(1);

        call.enqueue(new Callback<List<PopUpItem>>() {
            @Override
            public void onResponse(Call<List<PopUpItem>> call, Response<List<PopUpItem>> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(ViewAllActivity.this, ""+response, Toast.LENGTH_SHORT).show();

                    //personList = response.body();
                    PersonAdapter adapter = new PersonAdapter(getBaseContext(), personList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<PopUpItem>> call, Throwable t) {
                Toast.makeText(ViewAllActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        Call<List<Person>> call = myService.getAllPerson();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(ViewAllActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    personList = response.body();
                    PersonAdapter adapter = new PersonAdapter(getBaseContext(), personList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Toast.makeText(ViewAllActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
        });
        */

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
