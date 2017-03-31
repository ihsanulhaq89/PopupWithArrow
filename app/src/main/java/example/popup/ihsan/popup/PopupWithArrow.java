package example.popup.ihsan.popup;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

public class PopupWithArrow implements ViewTreeObserver.OnGlobalLayoutListener {
    private final PopupWindow mPopupWindow;
    private final PopupWindow arrow;
    private final View popupContentView;
    private final View arrowView;
    private View anchor;
    private FLOAT FLOAT;

    public PopupWithArrow(Context context, @LayoutRes int arrowLayoutID, @LayoutRes int popupLayoutID) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        popupContentView = inflater.inflate(popupLayoutID, null);
        arrowView = inflater.inflate(arrowLayoutID, null);
        mPopupWindow = new PopupWindow(
                popupContentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        this.arrow = new PopupWindow(
                arrowView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }

    public void show(View anchor, FLOAT gravity) {
        this.anchor = anchor;
        this.FLOAT = gravity;
        arrowView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        arrow.showAsDropDown(anchor, anchor.getWidth() / 2, 0);
    }

    @Override
    public void onGlobalLayout() {
        //now we can retrieve the width and height
        int width = arrowView.getWidth();
        int height = arrowView.getHeight();
        arrow.dismiss();
        arrow.showAsDropDown(anchor, anchor.getWidth() / 2 - width / 2, 0);
        int popupX = 0;
        switch (this.FLOAT){
            case LEFT:
                popupX = 0;
                break;
            case RIGHT:
                popupX = -anchor.getWidth()/2;
                break;
            case CENTER:
                popupX = -anchor.getWidth()/4;
                break;
        }
        mPopupWindow.showAsDropDown(anchor, popupX, height);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            arrowView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            arrowView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }

    }

    public enum FLOAT {
        LEFT, RIGHT, CENTER
    }

    public void dismiss() {
        arrow.dismiss();
        arrow.dismiss();
    }
}
