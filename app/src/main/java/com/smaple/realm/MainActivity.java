package com.smaple.realm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.smaple.realm.View.RecyclerViewAdapter;
import com.smaple.realm.ViewModel.ListItemViewModel;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity  {
    RecyclerView mRecyclerView;

    private RecyclerViewAdapter mRecyclerViewAdapter;

    private Realm realm;
    private RealmResults<ListItemViewModel> pets;
    private RealmChangeListener<RealmResults<ListItemViewModel>> realmChangeListener = pets -> {
        mRecyclerViewAdapter.setData(pets);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        realm = Realm.getDefaultInstance();

        pets = realm.where(ListItemViewModel.class).findAllAsync();
        pets.addChangeListener(realmChangeListener);

        mRecyclerViewAdapter.notifyDataSetChanged();

        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ListItemViewModel modifiedPet = mRecyclerViewAdapter.getItem(position);
                final String name = modifiedPet.getmName();
                realm.executeTransactionAsync(bgRealm -> {
                    ListItemViewModel pet = bgRealm.where(ListItemViewModel.class).equalTo("mName", name).findFirst();
                    if (pet != null) {
                        pet.setmAge(pet.getmAge() + 1);
                    }
                });
                mRecyclerViewAdapter.notifyDataSetChanged();
            }
        }) ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pets.removeAllChangeListeners(); // Remove change listeners to prevent updating views not yet GCed.
        realm.close(); // Remember to close Realm when done.
    }

}
