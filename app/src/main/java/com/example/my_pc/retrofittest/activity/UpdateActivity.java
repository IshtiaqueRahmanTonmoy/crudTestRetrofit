package com.example.my_pc.retrofittest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.personId)
    EditText personId;
    @BindView(R.id.personName)
    EditText personName;
    @BindView(R.id.personAddress)
    EditText personAddress;

    private Person person;
    private RetrofitService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        person = new Person();
        myService = MyRetrofit.getMyRetrofit().create(RetrofitService.class);
    }

    @OnClick(R.id.updateId)
    public void onViewClicked() {


        String id, name, address;
        id = personId.getText().toString().trim();
        name = personName.getText().toString().trim();
        address = personAddress.getText().toString().trim();

        // -- check id
        if (id.isEmpty()) {
            personId.setError("Person Id is required");
            personId.requestFocus();
            return;
        }

        // --- check name field
        if (name.isEmpty()) {
            personName.setError("Person Id is required");
            personName.requestFocus();
            return;
        }

        // -- check the person address fiels
        if (address.isEmpty()) {
            personAddress.setError("Person Id is required");
            personAddress.requestFocus();
            return;
        }
        int i;
        try {
            i = Integer.valueOf(id);
            person.setId(i);
        } catch (Exception e) {

            Toast.makeText(this, "Person id must be integer", Toast.LENGTH_SHORT).show();
            return;
        }

        person.setName(name);
        person.setAddress(address);

        // --- Now update the person's data
        update(i);
    }

    private void update(int id) {
        Call<Person> call = myService.updatePerson(id, person);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Data update successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

                Toast.makeText(UpdateActivity.this, "Network error", Toast.LENGTH_SHORT).show();
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
