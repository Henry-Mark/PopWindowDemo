# PopWindowDemo
PopupWindow 透明背景、底部弹起的通用父类BasePopupWindow

#使用时，只需要继承BasePopupWindow即可，然后实现getLayout()、getPopupTopView()、bindView()、dealEvent()四个方法即可。
如下：


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
    
    
    
使用的时候直接显示popupwindow就可以了，可以通过调用addOnItemClickListener（）方法添加对话框中按钮的监听事件。
    
详细可见：http://www.jianshu.com/p/aaeda50eb2a5
