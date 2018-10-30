package com.example.my_pc.retrofittest.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my_pc.retrofittest.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by my_pc on 3/8/2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    // we use this context to inflate the layout
    private Context context;

    // we will storing all person into a list
    private List<Person> personList;


    public PersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // --- inflating and returing our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sample_layout, null);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {

        // --- getting the specific person
        Person person = personList.get(position);

        holder.id.setText(String.valueOf(person.getId()) + ")");
        holder.name.setText(person.getName());
        holder.address.setText(person.getAddress());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView name;
        private TextView address;

        public PersonViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.personId);
            name = itemView.findViewById(R.id.personName);
            address = itemView.findViewById(R.id.personAddress);
        }
    }
}
