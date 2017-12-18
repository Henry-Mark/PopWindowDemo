package henry.com.popwindowdemo;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

/**
 * Author:Henry
 * Date:2017/12/18
 * Email:Heneymark@gmail.com.
 * Description:底部谈起提示对话框
 */

public class RemindPopWindow extends BasePopupWindow implements View.OnClickListener {

    public final static int FLAG_EDIT = 0;
    public final static int FLAG_DELETE = 1;

    TextView mEdit, mDelete, mCancel;

    public RemindPopWindow(Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayout() {
        return R.layout.window_remind;
    }

    @Override
    protected int getPopupTopView() {
        return R.id.layout;
    }

    @Override
    protected void bindView() {
        mEdit = getViewById(R.id.tv_edit);
        mDelete = getViewById(R.id.tv_delete);
        mCancel = getViewById(R.id.tv_cancel);

    }

    @Override
    protected void dealEvent() {
        mEdit.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mEdit) {
           if (listener!=null)listener.onClicked(FLAG_EDIT);
        }

        if (view == mDelete) {
            if (listener!=null)listener.onClicked(FLAG_DELETE);
        }
        if (view == mCancel) {
            dismiss();
        }
    }
}
