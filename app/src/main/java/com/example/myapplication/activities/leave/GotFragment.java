package com.example.myapplication.activities.leave;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class GotFragment extends Fragment {

    View view;
    private ArrayList<String> itemList = new ArrayList<>();
    private GotAdapter gotAdapter;

    public static Fragment fragment;
    private RecyclerView recyclerView;

    public GotFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_got, container, false);
        fragment = this;
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler1);
        gotAdapter = new GotAdapter(getActivity(), itemList);
        recyclerView.setAdapter(gotAdapter);

        gotAdapter.notifyDataSetChanged();

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("alarm")
//                .setContentText("newly added")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        return view;
    }

}
