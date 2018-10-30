package com.example.my_pc.retrofittest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_pc.retrofittest.R;
import com.example.my_pc.retrofittest.model.Person;
import com.example.my_pc.retrofittest.retrofit.MyRetrofit;
import com.example.my_pc.retrofittest.retrofit.RetrofitService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    @BindView(R.id.personId)
    EditText personId;

    private RetrofitService myService;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ButterKnife.bind(this);


        initUI();
    }

    private void initUI() {

        myService = MyRetrofit.getMyRetrofit().create(RetrofitService.class);
        person = new Person();
    }

    @OnClick(R.id.deleteId)
    public void onViewClicked() {

        String id = personId.getText().toString().trim();

        if (id.isEmpty()) {
            personId.setError("Enter Person Id");
            personId.requestFocus();
            return;
        }

        try {
            int i = Integer.valueOf(id);
            person.setId(i);
            person.setName("ABC");
            person.setAddress("ABC");

        } catch (Exception e) {
            Toast.makeText(this, "Id must be integer", Toast.LENGTH_SHORT).show();
            return;
        }

        deleteData(id);

    }

    private void deleteData(String id) {

        Call<Person> call = myService.delete(person);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(DeleteActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteActivity.this, "Not delete", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

                Toast.makeText(DeleteActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
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
