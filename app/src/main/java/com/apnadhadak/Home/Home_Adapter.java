package com.apnadhadak.Home;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.apnadhadak.Accounts.Login_A;
import com.apnadhadak.Main_Menu.BlankFragment;
import com.apnadhadak.Main_Menu.MainMenuActivity;
import com.apnadhadak.Profile.Profile_F;
import com.apnadhadak.R;
import com.apnadhadak.Services.Upload_Service;
import com.apnadhadak.SimpleClasses.Functions;
import com.apnadhadak.SimpleClasses.Variables;
import com.apnadhadak.Video_Recording.Video_Recoder_A;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import com.apnadhadak.Profile.Profile_Tab_F;

/**
 * Created by AQEEL on 3/20/2018.
 */

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.CustomViewHolder > {

    public Context context;
    private Home_Adapter.OnItemClickListener listener;
    private ArrayList<Home_Get_Set> dataList;
    Activity activity;
//    private LinearLayout create;




    // meker the onitemclick listener interface and this interface is impliment in Chatinbox activity
    // for to do action when user click on item
    public interface OnItemClickListener {
        void onItemClick(int positon,Home_Get_Set item, View view);
    }



    public Home_Adapter(Activity activity,Context context, ArrayList<Home_Get_Set> dataList, Home_Adapter.OnItemClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        this.activity = activity;

    }

    @Override
    public Home_Adapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_layout,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT));
        Home_Adapter.CustomViewHolder viewHolder = new Home_Adapter.CustomViewHolder(view);
        return viewHolder;
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }



    @Override
    public void onBindViewHolder(final Home_Adapter.CustomViewHolder holder, final int i) {
        final Home_Get_Set item= dataList.get(i);
        holder.setIsRecyclable(false);

        try {

            holder.bind(i,item,listener);
            holder.username.setText(item.username);
            if((item.sound_name==null || item.sound_name.equals("") || item.sound_name.equals("null"))){
                holder.sound_name.setText("original sound - "+item.first_name+" "+item.last_name);
            }else {
                holder.sound_name.setText(item.sound_name);
            }
            holder.sound_name.setSelected(true);



//            //rajat
//            holder.create.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
////                    if(Functions.check_permissions(context)) {
//
//                    Functions.make_directry(Variables.app_hided_folder);
//                    Functions.make_directry(Variables.app_showing_folder);
//                    Functions.make_directry(Variables.draft_app_folder);
//
//                    if(!Variables.sharedPreferences.getBoolean(Variables.islogin,false)) {
//                        Toast.makeText(context, "You have to login First", Toast.LENGTH_SHORT).show();
//                    }
//
//                    else if (Functions.isMyServiceRunning(context,new Upload_Service().getClass())) {
//                        Toast.makeText(context, "Please wait video already in uploading progress", Toast.LENGTH_LONG).show();
//                    }
//
//                    else {
//                           Intent intent = new Intent(context, Video_Recoder_A.class);
//                            context.startActivity(intent);
////                            overridePendingTransition(R.anim.in_from_bottom, R.anim.out_to_top);
//                    }
//
//
//                }
//
////            }
//
//
//
//            });
//
//
//
//            holder.profile.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(Variables.sharedPreferences.getBoolean(Variables.islogin,false)){
//
//
////                        ((FragmentActivity) v.getContext()).getFragmentManager().beginTransaction()
////                                .replace(R.id.MainMenuFragment,Profile_F)
////                                .commit();
//
//
//                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                        Fragment myFragment = new Profile_F();
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.MainMenuFragment, myFragment).addToBackStack(null).commit();
//
//                    }else {
//
//                        Intent intent = new Intent(context, Login_A.class);
//                        context.startActivity(intent);
//                    }
//
//
//                }
//            });

            holder.desc_txt.setText(item.video_description);
            Log.d("LIKES_COM>>>",item.like_count+" "+item.video_comment_count+ " "+item.liked);
            holder.like_txt.setText(Functions.GetSuffix(item.like_count));
            holder.comment_txt.setText(Functions.GetSuffix(item.video_comment_count));


            Picasso.get().
                    load(item.profile_pic)
                    .centerCrop()
                    .placeholder(context.getResources().getDrawable(R.drawable.profile_image_placeholder))
                    .resize(100,100).into(holder.user_pic);



            if((item.sound_name==null || item.sound_name.equals(""))
                    || item.sound_name.equals("null")){

                item.sound_pic=item.profile_pic;

            }
            else if(item.sound_pic.equals(""))
                item.sound_pic="Null";
            Picasso.get().
                    load(item.sound_pic).centerCrop()
                    .placeholder(context.getResources().getDrawable(R.drawable.ic_round_music))
                    .resize(100,100).into(holder.sound_image);






//            holder.like_image.setImageDrawable(context.getResources().getDrawable(R.drawable.like));

            if(item.liked.equals("1")){

                Glide
                        .with(holder.itemView)
                        .load(R.drawable.like)
                        .centerCrop()
                        .placeholder(R.drawable.unlike)
                        .into(holder.like_image);

            }
            else {
                Glide
                        .with(holder.itemView)
                        .load(R.drawable.unlike)
                        .centerCrop()
                        .placeholder(R.drawable.unlike)
                        .into(holder.like_image);
            }

//            if(item.liked.equals("1")){
//                holder.like_image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like_fill));
//            }
//            else {
//                holder.like_image.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_like));
//            }

            if(item.allow_comments!=null && item.allow_comments.equalsIgnoreCase("false"))
                holder.comment_layout.setVisibility(View.GONE);
            else
                holder.comment_layout.setVisibility(View.VISIBLE);

            holder.like_txt.setText(Functions.GetSuffix(item.like_count));
            holder.comment_txt.setText(Functions.GetSuffix(item.video_comment_count));


            if(item.verified!=null && item.verified.equalsIgnoreCase("1")){
                holder.varified_btn.setVisibility(View.VISIBLE);
            }else {
                holder.varified_btn.setVisibility(View.GONE);
            }

        }catch (Exception e){

        }
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView username,desc_txt,sound_name;
        ImageView user_pic,sound_image,varified_btn;

        LinearLayout like_layout,comment_layout,shared_layout,sound_image_layout,create;
        ImageView like_image,comment_image;
        TextView like_txt,comment_txt;

        ImageView profile;


        public CustomViewHolder(View view) {
            super(view);


            username=view.findViewById(R.id.username);
            user_pic=view.findViewById(R.id.user_pic);
            sound_name=view.findViewById(R.id.sound_name);
            sound_image=view.findViewById(R.id.sound_image);
            varified_btn=view.findViewById(R.id.varified_btn);


            like_layout=view.findViewById(R.id.like_layout);
            like_image=view.findViewById(R.id.like_image);
            like_txt=view.findViewById(R.id.like_txt);


            desc_txt=view.findViewById(R.id.desc_txt);

            comment_layout=view.findViewById(R.id.comment_layout);
            comment_image=view.findViewById(R.id.comment_image);
            comment_txt=view.findViewById(R.id.comment_txt);


            sound_image_layout=view.findViewById(R.id.sound_image_layout);
            shared_layout=view.findViewById(R.id.shared_layout);

//rajat
            create = view.findViewById(R.id.create);
            profile = view.findViewById(R.id.profile);



        }

        public void bind(final int postion,final Home_Get_Set item, final Home_Adapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(postion,item,v);
                }
            });


            user_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(postion,item,v);
                }
            });

            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(postion,item,v);
                }
            });


            like_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(postion,item,v);
                }
            });


            comment_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(postion,item,v);
                }
            });

            shared_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(postion,item,v);
                }
            });

            sound_image_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(postion,item,v);
                }
            });


        }


    }


}