package com.james.eggplantfastpass.ui.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.widget.TextView;

import com.james.eggplantfastpass.R;
import com.james.eggplantfastpass.ui.activity.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by 1 on 2017/2/14.
 */

public class SettingActivity extends BaseActivity {
    @BindView(R.id.id_tv_file_storage_location)
    TextView tvFileStorageLocation;
    @Override
    protected int getLayoutId() {
        return R.layout.aty_setting;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        SpannableString spannableString = new SpannableString(getString(R.string.file_storage_location));
        tvFileStorageLocation.setText(spannableString);
    }
}
