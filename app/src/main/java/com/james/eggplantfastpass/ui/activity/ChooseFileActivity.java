package com.james.eggplantfastpass.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.SampleFragmentPagerAdapter;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;
import com.james.eggplantfastpass.ui.fragments.AppFragment;
import com.james.eggplantfastpass.ui.fragments.FileFragment;
import com.james.eggplantfastpass.ui.fragments.MusicFragment;
import com.james.eggplantfastpass.ui.fragments.PicFragment;
import com.james.eggplantfastpass.ui.fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/19 0019.
 */

public class ChooseFileActivity extends BaseActivity {
//    @BindView(R.id.id_btn_open_hot)
//    Button btnOpenHot;
//
//    private WifiManager mWifiManager;
//    private List<ScanResult> wifiList;
//    private boolean flag = false;
//    private List<String> passableHotsPot;
//    private WifiReceiver wifiReceiver;
//    private boolean isConnected = false;
    private List<Fragment> fragmentList;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_send;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
//        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//        wifiReceiver = new WifiReceiver();
        //设置支持toolbar
        setSupportActionBar(toolbar);
        //设置返回箭头
        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.arrow_selector));
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示默认的title
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //设置搜索

        fragmentList = new ArrayList<>();
        AppFragment appFragment = new AppFragment();
        FileFragment fileFragment = new FileFragment();
        MusicFragment musicFragment = new MusicFragment();
        PicFragment picFragment = new PicFragment();
        VideoFragment videoFragment = new VideoFragment();

        fragmentList.add(fileFragment);//文件
        fragmentList.add(videoFragment);//视频
        fragmentList.add(appFragment);//应用
        fragmentList.add(picFragment);//图片
        fragmentList.add(musicFragment);//音乐

        SampleFragmentPagerAdapter sampleFragmentPagerAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                ChooseFileActivity.this);
        sampleFragmentPagerAdapter.addFragmentToList(fragmentList);
        viewPager.setAdapter(sampleFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(sampleFragmentPagerAdapter.getCount());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    //    @OnClick(R.id.id_btn_open_hot)
//    public void openHot(){
//        flag=!flag;
//        setWifiApEnabled(flag);
//
//    }
//    @OnClick(R.id.id_btn_connect_hot)
//    public void connectHot(){
//        //搜索热点
//        mWifiManager.startScan();
//
//        //连接热点
//    }
//
//    //打开热点
//    public boolean setWifiApEnabled(boolean enabled){
//        if(enabled){
//            mWifiManager.setWifiEnabled(false);
//        }
//        try {
//            //热点配置
//            WifiConfiguration apConfig = new WifiConfiguration();
//            //配置热点的名称(可以在名字后面加点随机数什么的)
//            apConfig.SSID = "YRCCONNECTION";
//            //配置热点的密码
//            apConfig.preSharedKey="12122112";
//            Method method = mWifiManager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
//            return (Boolean) method.invoke(mWifiManager, apConfig, enabled);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    /* 监听热点变化 */
//    private final class WifiReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            wifiList = mWifiManager.getScanResults();
//            if(wifiList == null || wifiList.size()<=0 || isConnected){
//                return;
//            }
//            onReceiveNewNetworks(wifiList);
//
//        }
//    }
//
//    /**
//     *
//     * @param wifiList
//     */
//    public void onReceiveNewNetworks(List<ScanResult> wifiList){
//        passableHotsPot = new ArrayList<>();
//        for(ScanResult scanResult : wifiList){
//            if(scanResult.SSID.equals("YRCCONNECTION")){
//                passableHotsPot.add(scanResult.SSID);
//            }
//        }
//        synchronized (this){
//            connectToHotpot();
//        }
//    }
//
//    /**
//     * 连接热点
//     */
//    private void connectToHotpot() {
//        if(passableHotsPot==null || passableHotsPot.size()==0)
//            return;
//        WifiConfiguration wifiConfig = this.setWifiParams(passableHotsPot.get(0));
//        int wcgId = mWifiManager.addNetwork(wifiConfig);
//        boolean flag = mWifiManager.enableNetwork(wcgId,true);
//        isConnected=flag;
//    }
//
//    private WifiConfiguration setWifiParams(String ssid) {
//        WifiConfiguration apConfig=new WifiConfiguration();
//        apConfig.SSID="\""+ssid+"\"";
//        apConfig.preSharedKey="\"12122112\"";
//        apConfig.hiddenSSID = true;
//        apConfig.status = WifiConfiguration.Status.ENABLED;
//        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
//        apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
//        apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
//        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//        apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//        apConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
//        return apConfig;
//    }
//    //socket传输文件
//
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(wifiReceiver);
//    }
}
