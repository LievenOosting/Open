package com.example.qiuchunjia.sample;

import android.widget.CompoundButton;

import com.qcj.common.base.AppContext;
import com.qcj.common.base.BaseActivity;
import com.qcj.common.widget.switchbutton.SwitchButton;

/**
 * Created by qiuchunjia on 2016/5/25.
 */
public class ChangeThemeActivity extends BaseActivity {
    private SwitchButton mSwitchButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_theme;
    }

    @Override
    public void initView() {
        mSwitchButton = (SwitchButton) findViewById(R.id.mSwitchButton);
        boolean isOpen = AppContext.getNightModeSwitch();
        mSwitchButton.setChecked(isOpen);
        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppContext.setNightModeSwitch(true);
                } else {
                    AppContext.setNightModeSwitch(false);
                }
            }
        });
    }

    @Override
    public void initData() {

    }
}
