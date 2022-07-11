package com.example.myapplication.activities.leave;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;

import java.util.ArrayList;

public class InviteFragment extends Fragment {
    View view;
    private ArrayList<String> itemList = new ArrayList<>();
    private InviteAdapter inviteAdapter;

    public static Fragment fragment;
    private RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    public InviteFragment() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_invite, container, false);
        fragment = this;
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler2);
        inviteAdapter = new InviteAdapter(getActivity(), itemList);
        recyclerView.setAdapter(inviteAdapter);

        inviteAdapter.notifyDataSetChanged();

        swipeRefreshLayout = view.findViewById(R.id.swipe_invite);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                inviteAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }
}
