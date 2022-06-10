package com.example.iot_java_mobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.iot_java_mobile.Domain.Ad;
import com.example.iot_java_mobile.Domain.AdCampaign;
import com.example.iot_java_mobile.R;
import com.squareup.picasso.Picasso;

public class AdPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_page);

        String EXTRA_MESSAGE = "AdAttached";
        Intent intent = getIntent();
        AdCampaign adCampaign = (AdCampaign) intent.getSerializableExtra(EXTRA_MESSAGE);
        Ad ad = adCampaign.getAd();

//        TextView productName = findViewById(R.id.product_name);
        ImageView adImage = findViewById(R.id.ad_image);
        Picasso.get().load(ad.getAd_image()).into(adImage);
    }
}