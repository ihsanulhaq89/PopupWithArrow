package example.popup.ihsan.popup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PopupWithArrow popup;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.hello);
        popup = new PopupWithArrow(this, R.layout.arrow, R.layout.popup);
        tv.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      popup.show(tv, PopupWithArrow.FLOAT.RIGHT);
                                     // popup.show(tv, PopupWithArrow.FLOAT.LEFT);
                                     // popup.show(tv, PopupWithArrow.FLOAT.CENTER);
                                  }
                              }
        );

    }

}
