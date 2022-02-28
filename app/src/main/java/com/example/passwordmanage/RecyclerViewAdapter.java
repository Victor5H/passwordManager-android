package com.example.passwordmanage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordmanage.basic_activities.Updat;
import com.example.passwordmanage.models.Entries_Model;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    List<Entries_Model> list_em;
    Context context;
    private static final String TAG = "RecyclerViewAdapter";

    public RecyclerViewAdapter(List<Entries_Model> list_em, Context context) {
        this.list_em = list_em;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String etc = "", id = "ID: ", pass = "Password: ", pre = "Previous password: ", preword = null, word = null;
        holder.name.setText(id + list_em.get(position).getName());
        word = list_em.get(position).getWord();
        if (word.length() > 9) {
            holder.word.setText(pass + "\n" + list_em.get(position).getWord());
        } else {
            holder.word.setText(pass + list_em.get(position).getWord());
        }
        preword = list_em.get(position).getPre_Word();
        holder.preword.setText(pre);
        if (preword == null) {
        } else if (preword.length() > 9) {
            holder.preword.setText(pre + "\n" + preword);
        } else {
            holder.preword.setText(pre + preword);
        }
        holder.tag.setText(list_em.get(position).getTag());
        String tag = list_em.get(position).getTag();
        try {
            etc = tag.toLowerCase();
        } catch (NullPointerException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
        holder.tag.setVisibility(View.VISIBLE);
        holder.imageView.setVisibility(View.VISIBLE);
        holder.logo.setVisibility(View.INVISIBLE);
        if (etc.contains("microsoft")) {
            holder.imageView.setImageResource(R.drawable.microsoft);
        } else if (etc.contains("insta")) {
            holder.imageView.setImageResource(R.drawable.insta);
        } else if (etc.contains("datacamp")) {
            holder.imageView.setImageResource(R.drawable.datacamp);
        } else if (etc.contains("a51")) {
            holder.imageView.setImageResource(R.drawable.a51);
        } else if (etc.contains("ycce") && etc.length() == "ycce".length()) {
            holder.imageView.setImageResource(R.drawable.ycce);
        } else if (etc.contains("ycce ") && etc.length() == "ycce ".length()) {
            holder.imageView.setImageResource(R.drawable.ycce);
        } else if (etc.contains("linkedin")) {
            holder.imageView.setImageResource(R.drawable.linkedin);
        } else if (etc.contains("google")) {
            holder.imageView.setImageResource(R.drawable.google);
        } else if (etc.contains("samsung")) {
            holder.imageView.setImageResource(R.drawable.samsung);
        } else if (etc.contains("ndl")) {
            holder.imageView.setImageResource(R.drawable.ndl);
        } else if (etc.contains("edx")) {
            holder.imageView.setImageResource(R.drawable.edx);
        } else if (etc.contains("coursera")) {
            holder.imageView.setImageResource(R.drawable.coursera);
        } else if (etc.contains("hellfire")) {
            holder.imageView.setImageResource(R.drawable.hellfire);
        } else if (etc.contains("flipkart")) {
            holder.imageView.setImageResource(R.drawable.flipkart);
        } else if (etc.contains("amazon")) {
            holder.imageView.setImageResource(R.drawable.amazon);
        } else if (etc.contains("wifi")) {
            holder.imageView.setImageResource(R.drawable.wifi);
        } else if (etc.contains("ycce mis")) {
            holder.imageView.setImageResource(R.drawable.yccemis);
        } else if (etc.contains("nptel")) {
            holder.imageView.setImageResource(R.drawable.nptel);
        } else if (etc.contains("discord")) {
            holder.imageView.setImageResource(R.drawable.discord);
        } else if (etc.contains("email")) {
            holder.imageView.setImageResource(R.drawable.email);
        } else if (etc.contains("facebook")) {
            holder.imageView.setImageResource(R.drawable.facebook);
        } else if (etc.contains("quora")) {
            holder.imageView.setImageResource(R.drawable.quora);
        } else if (etc.contains("apple")) {
            holder.imageView.setImageResource(R.drawable.apple);
        } else if (etc.contains("fb")) {
            holder.imageView.setImageResource(R.drawable.flipkart);
        } else if (etc.contains("udemy")) {
            holder.imageView.setImageResource(R.drawable.udemy);
        } else if (etc.contains("github")) {
            holder.imageView.setImageResource(R.drawable.github);
        } else if (etc.contains("git hub")) {
            holder.imageView.setImageResource(R.drawable.github);
        } else if (etc.contains("router")) {
            holder.imageView.setImageResource(R.drawable.router);
        } else if (etc.contains("stackoverflow")) {
            holder.imageView.setImageResource(R.drawable.stackoverflow);
        } else if (etc.contains("stack overflow")) {
            holder.imageView.setImageResource(R.drawable.stackoverflow);
        } else if (etc.contains("whatsapp")) {
            holder.imageView.setImageResource(R.drawable.whatsapp);
        } else if (etc.contains("amazon prime")) {
            holder.imageView.setImageResource(R.drawable.prime);
        } else if (etc.contains("netflix")) {
            holder.imageView.setImageResource(R.drawable.netflix);
        } else if (etc.contains("prime")) {
            holder.imageView.setImageResource(R.drawable.prime);
        } else if (etc.contains("epic games")) {
            holder.imageView.setImageResource(R.drawable.epic_games);
        } else if (etc.contains("twitch")) {
            holder.imageView.setImageResource(R.drawable.twitch);
        } else if (etc.contains("reddit")) {
            holder.imageView.setImageResource(R.drawable.reddit);
        } else if (etc.contains("minecraft")) {
            holder.imageView.setImageResource(R.drawable.minecraft);
        } else if (etc.contains("twitter")) {
            holder.imageView.setImageResource(R.drawable.twitter);
        } else if (etc.contains("gmail")) {
            holder.imageView.setImageResource(R.drawable.email);
        } else if (etc.contains("hotstar")) {
            holder.imageView.setImageResource(R.drawable.disneyhotstar);
        } else if (etc.contains("disney+hotstar")) {
            holder.imageView.setImageResource(R.drawable.disneyhotstar);
        } else if (etc.contains("sony liv")) {
            holder.imageView.setImageResource(R.drawable.sonyliv);
        } else if (etc.contains("liv")) {
            holder.imageView.setImageResource(R.drawable.sonyliv);
        } else if (etc.contains("zee5")) {
            holder.imageView.setImageResource(R.drawable.zee5);
        } else if (etc.contains("upi")) {
            holder.imageView.setImageResource(R.drawable.upi);
        } else if (etc.contains("bank of baroda")) {
            holder.imageView.setImageResource(R.drawable.bankofbar);
        } else if (etc.contains("xbox")) {
            holder.imageView.setImageResource(R.drawable.xbox);
        } else if (etc.contains("playstation")) {
            holder.imageView.setImageResource(R.drawable.playstation);
        } else if (etc.contains("steam")) {
            holder.imageView.setImageResource(R.drawable.steam);
        } else if (etc.contains("spotify")) {
            holder.imageView.setImageResource(R.drawable.spotify);
        } else if (etc.contains("gaana")) {
            holder.imageView.setImageResource(R.drawable.gaana);
        } else if (etc.contains("udacity")) {
            holder.imageView.setImageResource(R.drawable.udacity);
        } else if (etc.contains("adobe")) {
            holder.imageView.setImageResource(R.drawable.adobe);
        } else if (etc.contains("educative")) {
            holder.imageView.setImageResource(R.drawable.educative);
        } else if (etc.contains("voot")) {
            holder.imageView.setImageResource(R.drawable.voot);
            holder.imageView.setImageResource(R.drawable.voot);
        } else {
            holder.tag.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.logo.setVisibility(View.VISIBLE);
            holder.logo.setText(tag);
        }
        holder.parentLayout.setOnClickListener(v -> {
            Intent i = new Intent(context, Updat.class);
            i.putExtra("id", list_em.get(position).getId());
            context.startActivity(i);
        });
//        holder.parentLayout.setLongClickable(true);
//        holder.parentLayout.setOnLongClickListener(v->{
//            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
//            builder1.setMessage("Delete this entry?");
//            builder1.setCancelable(true);
//            String t = Integer.toString(list_em.get(position).getId());
//            int idi=Integer.parseInt(t);
//            builder1.setPositiveButton(
//                    "Yes",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            Toast.makeText(context, t, Toast.LENGTH_SHORT).show();
//                            CustomHelper ch = new CustomHelper(context);
//                            ch.delete(idi);
//                        }
//                    });
//            builder1.setNegativeButton(
//                    "No",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    });
//
//            AlertDialog alert11 = builder1.create();
//            alert11.show();
//            return false;
//        });
    }


    @Override
    public int getItemCount() {
        return list_em.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageViewCheck;
        TextView name, word, preword, tag, logo;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCheck = itemView.findViewById(R.id.iv_check);
            imageView = itemView.findViewById(R.id.imageView_Picture);
            name = itemView.findViewById(R.id.textView_Name);
            word = itemView.findViewById(R.id.textView_Word);
            preword = itemView.findViewById(R.id.textView_Preword);
            tag = itemView.findViewById(R.id.textView_tag);
            logo = itemView.findViewById(R.id.textView_logo);
            parentLayout = itemView.findViewById(R.id.one_item);
        }
    }
}
