package com.james.eggplantfastpass.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.adapter.HelpAndFeedBackAdapter;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import org.james.material.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.james.eggplantfastpass.ui.activity.ProblemListActivity.FILES_NAME_KEY;

/**
 * Created by 1 on 2017/2/13.
 */

public class HelpAndFeedBackActivity extends BaseActivity {
    @BindView(R.id.id_rv_content)
    RecyclerView rvContent;
    @BindView(R.id.id_tv_title)
    TextView tvTitle;
    private String[] hotspotIssues;
    private HelpAndFeedBackAdapter helpAndFeedBackAdapter;
    private List<String> problemList;
    private List<String> titleList;
    private List<String> fileNameList;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_help_and_feedback;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);//不显示默认的title
        toolbar.setLogo(ContextCompat.getDrawable(this,R.drawable.arrow_selector));

        tvTitle.setText(getString(R.string.help_feed_back));
        hotspotIssues = getResources().getStringArray(R.array.hotspot_issues_array);
        helpAndFeedBackAdapter = new HelpAndFeedBackAdapter(hotspotIssues);
        rvContent.setAdapter(helpAndFeedBackAdapter);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        dividerItemDecoration.setIndex(2);
        rvContent.addItemDecoration(dividerItemDecoration);
        problemList = new ArrayList<String>();
        titleList = new ArrayList<String>();
        fileNameList = new ArrayList<>();
        setData4();
        helpAndFeedBackAdapter.setOnRecyclerViewClickListener(new HelpAndFeedBackAdapter.OnRecyclerViewClickListener() {
            @Override
            public void onRecyclerViewClick(int position) {
                if(fileNameList != null && fileNameList.size()>0) {
                    Intent intent = new Intent(HelpAndFeedBackActivity.this, WebActivity.class);
                    Bundle bundle = new Bundle();
                    int newPosition = position - 2;
                    String fileName = fileNameList.get(newPosition);
                    bundle.putString(WebActivity.FILE_NAME_KEY, fileName);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        helpAndFeedBackAdapter.setOnViewHolderOneClickListener(new HelpAndFeedBackAdapter.OnViewHolderOneClickListener() {
            @Override
            public void onViewHolderOneClick(int id) {
                Intent intent = new Intent(HelpAndFeedBackActivity.this,ProblemListActivity.class);
                Bundle bundle = new Bundle();
                String title = "";
                problemList.clear();
                titleList.clear();
                if(id == R.id.id_ll_transmission_problem){
                    title = getString(R.string.transmission_problem);
                    setData0();
                }else if(id == R.id.id_ll_connection_problem){
                    title = getString(R.string.connection_problem);
                    setData1();
                }else if(id == R.id.id_ll_storage_problem){
                    title = getString(R.string.storage_problem);
                    setData2();
                }else if(id == R.id.id_ll_specific_type_problem){
                    title = getString(R.string.specific_type_problem);
                    setData3();
                }

                bundle.putStringArrayList(ProblemListActivity.PROBLEM_KEY, (ArrayList<String>) problemList);
                bundle.putStringArrayList(FILES_NAME_KEY, (ArrayList<String>) titleList);
                bundle.putString(ProblemListActivity.TITLE_KEY,title);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


    private void setData0() {
        problemList.add("升级到新版本后无法正常收发？");
        problemList.add("接收到的文件不能保存？");
        problemList.add("接收到的文件无法打开？应用程序无法安装？");
        problemList.add("找不到要发送的文件？");
        problemList.add("传输速度慢？");
        problemList.add("传输过程中断？");
        problemList.add("传输中将茄子切换后台传输停止？");
        problemList.add("Android 手机如何与苹果手机互传文件？");
        problemList.add("Android 手机如何与电脑传输文件？");
        problemList.add("小技巧实现群收发");
        problemList.add("如何通过局域网传输文件？");
        titleList.add("ht_update");
        titleList.add("ht_save");
        titleList.add("ht_open");
        titleList.add("ht_find");
        titleList.add("ht_slow");
        titleList.add("ht_interrupt");
        titleList.add("ht_backstage");
        titleList.add("ht_ios");
        titleList.add("ht_pc");
        titleList.add("ht_group");
        titleList.add("ht_wlan");
    }
    private void setData1() {
        problemList.add("发送方选择接收方头像后无法连接？");
        problemList.add("找不到周围的接收者？");
        problemList.add("启动vpn后无法进行发送接收？");
        problemList.add("是否可以同时给多个设备发送文件？");
        problemList.add("热点启动失败？");
        titleList.add("hc_unconnect");
        titleList.add("hc_find");
        titleList.add("hc_vpn");
        titleList.add("hc_multi");
        titleList.add("hc_startap");
    }
    private void setData2() {
        problemList.add("如何将文件保存到SD卡？");
        problemList.add("无法将文件保存到SD卡？");
        problemList.add("找不到我接收的文件？");
        problemList.add("系统为安卓4.4，选择储存路径时，提示外置储存卡（SD卡）没有权限");
        problemList.add("储存路径太长并无法更改");
        titleList.add("hs_movesd");
        titleList.add("hs_savesd");
        titleList.add("hs_find");
        titleList.add("hs_android4.4");
        titleList.add("hs_location");
    }
    private void setData3() {
        problemList.add("启动茄子后多次闪退？");
        problemList.add("小米手机安全中心设置方法");
        problemList.add("Yuphoria手机无法完整显示界面？");
        problemList.add("Mi pad热点启动失败？");
        problemList.add("Nexus7热点启动失败？");
        problemList.add("为什么我的小米（或红米）手机不能接收或发送？");
        problemList.add("索尼手机出现Tethering error 共享错误提示？");
        problemList.add("出现“安装包解析错误”提示？");
        titleList.add("hd_crash");
        titleList.add("hd_misafe");
        titleList.add("hd_yuphoria");
        titleList.add("hd_mipad");
        titleList.add("hd_nexus7");
        titleList.add("hd_xiaomi");
        titleList.add("hd_sony");
        titleList.add("hd_package");
    }

    private void setData4() {
        fileNameList.add("ht_update");
        fileNameList.add("ht_slow");
        fileNameList.add("ht_interrupt");
        fileNameList.add("hc_unconnect");
        fileNameList.add("hc_find");
        fileNameList.add("hs_movesd");
        fileNameList.add("hd_crash");
        fileNameList.add("hd_misafe");
    }

}
