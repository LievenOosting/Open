package com.qcj.common.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.qcj.common.AppConfig;
import com.qcj.common.AppManager;
import com.qcj.common.R;
import com.qcj.common.helper.ToolBarHelper;
import com.qcj.common.interf.DialogControl;
import com.qcj.common.interf.UIInterface;
import com.qcj.common.util.Anim;
import com.qcj.common.util.DialogHelp;
import com.qcj.common.helper.SystemBarTintManager;
import com.qcj.common.util.TDevice;

import java.io.Serializable;


/**
 * author：qiuchunjia
 * 引用自：开源中国部分代码
 */
public abstract class BaseActivity extends AppCompatActivity implements
        DialogControl, UIInterface, PopView.PopResultListener {
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
        initHint();
        AppManager.getAppManager().addActivity(this);
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

    /**
     * 显示软键盘
     */
    public void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyBoard() {
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    @Override
    public void finish() {
        super.finish();
        Anim.exit(this);
    }

    /*************************
     * 设置返回上一个activity的数据 以及获取activity传来的数据
     ******************************/
    public static final int GET_DATA_FROM_ACTIVITY = 1; // 用于activity数据传递
    public static final int ACTIVTIY_TRANFER = 2; // 用于activity数据传递

    /**
     * 设置序列化返回
     * <<<<<<< HEAD
     * <p/>
     * 只要传递的值满足序列化就可以了！不管是对象还是对象集合
     * <p/>
     * <p/>
     * =======
     * <p/>
     * 只要传递的值满足序列化就可以了！不管是对象还是对象集合
     * <p/>
     * <p/>
     * >>>>>>> 30c0cab2dbae2e7a455a7387ccc0eb9ce34cb1a6
     * 该方法用于当前activity返回后给上一个acvivity传值 对应解析的方法为getReturnResultSeri
     *
     * @param serializable
     * @param flag
     */
    public void setReturnResultSeri(Serializable serializable, String flag) {
        String defaultFlag = AppConfig.ACTIVITY_TRANSFER_BUNDLE;
        if (flag != null) {
            defaultFlag = flag;
        }
        if (serializable != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable(defaultFlag, serializable);
            intent.putExtras(bundle);
            this.setResult(BaseActivity.ACTIVTIY_TRANFER, intent);
        }
    }


    public void setReturnResultPar(Parcelable parcelable, String flag) {
        String defaultFlag = AppConfig.ACTIVITY_TRANSFER_BUNDLE;
        if (flag != null) {
            defaultFlag = flag;
        }
        if (parcelable != null) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(defaultFlag, parcelable);
            intent.putExtras(bundle);
            this.setResult(BaseActivity.ACTIVTIY_TRANFER, intent);
        }
    }

    /**
     * 获取结果
     *
     * @param flag 区分传的值
     * @return
     */
    public Serializable getReturnResultSeri(int resultCode, Intent intent,
                                            String flag) {
        String defaultFlag = AppConfig.ACTIVITY_TRANSFER_BUNDLE;
        if (resultCode == BaseActivity.ACTIVTIY_TRANFER && intent != null) {
            if (flag != null) {
                defaultFlag = flag;
            }
            Bundle bundle = intent.getExtras();
            return bundle.getSerializable(defaultFlag);
        }
        return null;
    }

    /**
     * 把数据封装到bundel中 用于传递
     */
    public Bundle sendDataToBundle(Serializable serializable, String flag) {
        String defaultFlag = AppConfig.ACTIVITY_TRANSFER_BUNDLE;
        if (flag != null) {
            defaultFlag = flag;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(defaultFlag, serializable);
        return bundle;
    }


    /**
     * 从intent获取里面的bundle然后在获取里面的值
     *
     * @param intent
     * @param flag   可以传可以不传，有默认的
     * @return
     */
    public Serializable getDataFromIntent(Intent intent, String flag) {
        String defaultFlag = AppConfig.ACTIVITY_TRANSFER_BUNDLE;
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                if (flag != null) {
                    defaultFlag = flag;
                }
                return bundle.getSerializable(defaultFlag);
            }
        }
        return null;
    }


    /*************************
     * 设置返回上一个activity的数据 end
     ******************************/

    /*************************
     * popwindow 返回到数据 end
     ******************************/
    @Override
    public Object onPopResult(Object object) {
        return null;
    }


}
