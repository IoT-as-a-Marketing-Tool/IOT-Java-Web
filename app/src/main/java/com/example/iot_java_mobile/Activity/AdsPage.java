package com.example.iot_java_mobile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.AutoScrollHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.iot_java_mobile.Adaptor.AdsAdapter;
import com.example.iot_java_mobile.Domain.AdCampaign;
import com.example.iot_java_mobile.Domain.EstProduct;
import com.example.iot_java_mobile.Domain.Establishment;
import com.example.iot_java_mobile.R;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AdsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String EXTRA_MESSAGE = "GivingEstProducts";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_page);

        Intent intent = getIntent();
//        List<EstProduct> estProductList = (List<EstProduct>) intent.getSerializableExtra(EXTRA_MESSAGE);
//        if (estProductList != null){
//            Log.e("Don", "onCreate: estProducts == "+ estProductList );
//            AdsAdapter adsAdapter = new AdsAdapter(estProductList);
//        }
        List<AdCampaign> campaignList = (List<AdCampaign>) intent.getSerializableExtra("GivingCampaignList");
        List<Establishment> establishmentList = (List<Establishment>) intent.getSerializableExtra("GivingEstablishmentNameList");

        Log.e("Don", "onCreate: campaignList == "+ campaignList );
        AdsAdapter adsAdapter = new
                AdsAdapter(campaignList, establishmentList );

        RecyclerView recyclerView = findViewById(R.id.ads_recycler_view);
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(this,RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManagerProduct);
        recyclerView.setAdapter(adsAdapter);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(recyclerView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(layoutManagerProduct.findLastCompletelyVisibleItemPosition() < (adsAdapter.getItemCount() - 1)){
                    layoutManagerProduct.smoothScrollToPosition(recyclerView, new RecyclerView.State(), layoutManagerProduct.findLastVisibleItemPosition()+1);
                }
            }
        },0,6500);
        adsAdapter.setOnItemClickListener(
                new AdsAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Log.e("Don", "onItemClick: " +v );
                        switch (v.getId()){
                            case R.id.ad_establishment:
                                Log.e("Don", "--- establishments clicked" );
                                List<Establishment> establishments = campaignList.get(position).getEstablishments();
                                Intent intent = new Intent(AdsPage.this, AdEstablishmentsPage.class);
                                intent.putExtra("GivingCampaignEstablishments", (Serializable) establishments);
                                Establishment curEstablishment = null;
                                if (establishmentList.size() > position){
                                    curEstablishment = establishmentList.get(position);
                                }
                                intent.putExtra("GivingCurEstablishment", (Serializable) curEstablishment);
                                intent.putExtra("GivingCampaign", campaignList.get(position));

                                startActivity(intent);
                                break;
                        }
                    }
                }
        );
//        adsAdapter.setOnItemClickListener(new AdsAdapter.ClickListener() {
//            @Override
//            public void onItemClick(int position, View v) {
//                v.
//            }
//
//            @Override
//            public void onClickListener() {
//
//            }
//        });

        // setScrollingTouchSlop();










    }
}