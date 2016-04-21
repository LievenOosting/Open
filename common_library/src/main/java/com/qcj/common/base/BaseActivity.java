package com.qcj.common.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.qcj.common.AppManager;
import com.qcj.common.R;
import com.qcj.common.helper.ToolBarHelper;
import com.qcj.common.interf.DialogControl;
import com.qcj.common.interf.UIInterface;
import com.qcj.common.util.DialogHelp;
import com.qcj.common.helper.SystemBarTintManager;
import com.qcj.common.util.TDevice;


/**
 * author：qiuchunjia
 * 引用自：开源中国部分代码
 */
public abstract class BaseActivity extends AppCompatActivity implements
        DialogControl, UIInterface {
    public static final String INTENT_ACTION_EXIT_APP = "INTENT_ACTION_EXIT_APP";

    private boolean _isVisible;
    private ProgressDialog _waitDialog;
    protected LayoutInflater mInflater;
    private SystemBarTintManager mTintManager;
    private ToolBarHelper mToolBarHelper;
    private Toolbar mToolbar;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TDevice.hideSoftKeyboard(getCurrentFocus());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppContext.getNightModeSwitch()) {
            setTheme(R.style.AppBaseTheme_Night);
        } else {
            setTheme(R.style.AppBaseTheme_Light);
        }
        AppManager.getAppManager().addActivity(this);
        initHint();
        onBeforeSetContentLayout();
        if (hasToolBar()) {
            setToolBar(getLayoutId());
        } else {
            setContentView(getLayoutId());
        }
        init(savedInstanceState);
        initView();
        initData();
        setListener();
        _isVisible = true;
    }


    protected void onBeforeSetContentLayout() {
    }

    /**
     * 初始化沉浸式菜单栏
     */
    protected void initHint() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        SystemBarTintManager mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(true);
        mTintManager.setTintResource(R.drawable.new_app_navi01);
    }

    /**
     * 设置toolbar
     */
    private void setToolBar(int layoutID) {
        mToolBarHelper = new ToolBarHelper(this, layoutID);
        mToolbar = mToolBarHelper.getToolBar();
        setContentView(mToolBarHelper.getContentView());
        setSupportActionBar(mToolbar);
    }

    /**
     * 设置toolbar的标题
     */
    public void setToolBarTitle(String title) {
        if (mToolbar != null) {
            if (mToolbar != null) {
                TextView titleTv = (TextView) mToolbar.findViewById(R.id.base_title_tv);
                titleTv.setText(title);
            }
        }
    }

    /**
     * 是否显示back键
     *
     * @param isShow
     */
    public void showBackIcon(boolean isShow) {
        if (!isShow && mToolbar != null) {
            mToolbar.setNavigationIcon(null);
        }
    }

    protected boolean hasToolBar() {
        return true;
    }

    protected abstract int getLayoutId();

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected void init(Bundle savedInstanceState) {
    }

    @Override
    public void setListener() {
        //钩子方法子类想实现就实现
    }

    @Override
    public void onClick(View v) {
        //钩子方法子类想实现就实现
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public ProgressDialog showWaitDialog() {
        return showWaitDialog("加载中...");
    }

    @Override
    public ProgressDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public ProgressDialog showWaitDialog(String message) {
        if (_isVisible) {
            if (_waitDialog == null) {
                _waitDialog = DialogHelp.getWaitDialog(this, message);
            }
            if (_waitDialog != null) {
                _waitDialog.setMessage(message);
                _waitDialog.show();
            }
            return _waitDialog;
        }
        return null;
    }

    @Override
    public void hideWaitDialog() {
        if (_isVisible && _waitDialog != null) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}
