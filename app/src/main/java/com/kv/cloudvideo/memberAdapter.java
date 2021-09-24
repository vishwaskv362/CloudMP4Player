package com.kv.cloudvideo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class memberAdapter extends FirebaseRecyclerAdapter<members, memberAdapter.memberViewholder> {
    static String id;
    public memberAdapter(
            @NonNull FirebaseRecyclerOptions<members> options)
    {
        super(options);

    }
    @Override
    protected void
    onBindViewHolder(@NonNull memberViewholder holder, int position, @NonNull members model)
    {
        holder.title.setText(model.getTitle());
        Picasso.get().load(model.getThumbnail()).into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=getRef(position).getKey();
                Intent i =new Intent(v.getContext(),video_player.class);
                i.putExtra("id",id);
                v.getContext().startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public memberViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddisplay, parent, false);
        return new memberViewholder(view);

    }
    static class memberViewholder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView pic;
        LinearLayout linearLayout;
        public memberViewholder(@NonNull View itemView)
        {
            super(itemView);
            title= itemView.findViewById(R.id.vname);
            pic=itemView.findViewById(R.id.thumb);
            linearLayout=itemView.findViewById(R.id.LL);

        }

    }



}

