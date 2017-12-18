package henry.com.popwindowdemo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * author : Henry
 * time :  2017/9/1 16:44
 * email : heneymark@gmail.com
 * description :底部弹出的PopupWindow基类
 */

public abstract class BasePopupWindow extends PopupWindow {

    protected Activity activity;
    protected View mContentView;
    protected OnItemClickListener listener;

    public BasePopupWindow(Activity activity) {
        this.activity = activity;
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = inflater.inflate(getLayout(), null);

        bindView();
        dealEvent();

        //设置PopupWindow的View
        this.setContentView(mContentView);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.bottom_anim);
        //关闭事件
        this.setOnDismissListener(new popupDismissLister());

        backgroundAlpha(0.5f);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mContentView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mContentView.findViewById(getPopupTopView()).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 获取PopupWindow最高控件
     *
     * @return
     */
    protected abstract int getPopupTopView();

    /**
     * 绑定控件
     */
    protected abstract void bindView();

    /**
     * 处理相关事件
     */
    protected abstract void dealEvent();

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mContentView.findViewById(id);
    }

    /**
     * 设置popupwindow外面背景透明度
     *
     * @param bgalpha 透明度  0-1   0-透明   1-不透明
     */
    private void backgroundAlpha(float bgalpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgalpha;
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 添加子空间的监听事件
     *
     * @param listener
     */
    public void addOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClicked(int flag);
    }

    private class popupDismissLister implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {

            backgroundAlpha(1f);
        }
    }
}
