package henry.com.popwindowdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import static henry.com.popwindowdemo.RemindPopWindow.FLAG_DELETE;
import static henry.com.popwindowdemo.RemindPopWindow.FLAG_EDIT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open(View view) {
        RemindPopWindow window = new RemindPopWindow(this);
        window.addOnItemClickListener(new BasePopupWindow.OnItemClickListener() {
            @Override
            public void onClicked(int flag) {
                if (FLAG_EDIT == flag) {
                    Toast.makeText(MainActivity.this, "编辑", Toast.LENGTH_SHORT).show();
                }
                if (FLAG_DELETE == flag) {
                    Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
                }

            }
        });
        window.showAtLocation(findViewById(R.id.layout), Gravity.BOTTOM | Gravity
                .CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

    }
}
