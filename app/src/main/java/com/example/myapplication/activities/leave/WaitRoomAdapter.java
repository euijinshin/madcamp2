package com.example.myapplication.activities.leave;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;


public class WaitRoomAdapter extends RecyclerView.Adapter<WaitRoomAdapter.ViewHolder> {

    private ArrayList<String> mData = null ;
    private final Context context;

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1 ;

        ViewHolder(View itemView) {
            super(itemView) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, LeaveActivity.class);

                        intent.putExtra("name", mData.get(pos));

                        context.startActivity(intent);
                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            textView1 = itemView.findViewById(R.id.waitroom_text) ;
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    WaitRoomAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.mData = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public WaitRoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.waitroom_item, parent, false) ;
        WaitRoomAdapter.ViewHolder vh = new WaitRoomAdapter.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(WaitRoomAdapter.ViewHolder holder, int position) {
        String text = mData.get(position) ;
        holder.textView1.setText(text) ;
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}