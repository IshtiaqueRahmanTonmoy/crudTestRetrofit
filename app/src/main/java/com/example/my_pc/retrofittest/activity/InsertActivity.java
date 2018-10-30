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

public class InsertActivity extends AppCompatActivity {

    @BindView(R.id.personId)
    EditText personId;
    @BindView(R.id.personName)
    EditText personName;
    @BindView(R.id.personAddress)
    EditText personAddress;

    private RetrofitService myService;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        myService = MyRetrofit.getMyRetrofit().create(RetrofitService.class);
        person = new Person();
    }

    @OnClick(R.id.insertId)
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

        try {
            person.setId(Integer.valueOf(id));
        } catch (Exception e) {

            Toast.makeText(this, "Person id must be integer", Toast.LENGTH_SHORT).show();
            return;
        }

        person.setName(name);
        person.setAddress(address);
        // Now save the person's data
        saveData();

    }

    private void saveData() {
        Call<Person> call = myService.addPerson(person);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(InsertActivity.this, "Your Data successfully saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InsertActivity.this, "Something wrong, Please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Toast.makeText(InsertActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
