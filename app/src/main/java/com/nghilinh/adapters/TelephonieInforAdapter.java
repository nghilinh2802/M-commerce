package com.nghilinh.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nghilinh.k22411csampleproject.R;
import com.nghilinh.k22411csampleproject.SendSMSActivity;
import com.nghilinh.k22411csampleproject.TelephonieActivity;
import com.nghilinh.models.TelephonieInfor;

public class TelephonieInforAdapter extends ArrayAdapter<TelephonieInfor> {
    Activity context;
    int resource;
    public TelephonieInforAdapter(@NonNull Activity context, int resource) {
        super(context,resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);
        TextView txtTelephonieName=item.findViewById(R.id.txtTelephonieName);
        TextView txtTelephonieNumber=item.findViewById(R.id.txtTelephonieNumber);
        ImageView imgDirectCall=item.findViewById(R.id.imgDirectCall);
        ImageView imgDialUp=item.findViewById(R.id.imgDialUp);
        ImageView imgSendSMS=item.findViewById(R.id.imgSendSMS);

        TelephonieInfor ti=getItem(position);
        txtTelephonieName.setText(ti.getName());
        txtTelephonieNumber.setText(ti.getPhone());

        imgDirectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TelephonieActivity)context).directCall(ti);
            }
        });

        imgDialUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TelephonieActivity)context).dialupCall(ti);
            }
        });

        imgSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI",ti);
                context.startActivity(intent);
            }
        });

        return item;
    }
}
