package com.example.buttsweetsfinal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;

import de.starkling.shoppingcart.R;


class CounterView extends LinearLayout implements View.OnClickListener {

    private AppCompatImageButton removeItem;
    private AppCompatImageButton addToCart;
    private TextView mTextCounter;
    private int tvValCounter = 0;
    private boolean minFixToOne = false;
    private CounterValueChangeListener tvValCounterChangeListener;

    private static final long MIN_CLICK_INTERVAL = 0;
    private long mLastClickTime;
    private Context mContext;

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_counter, this, true);

        removeItem = (AppCompatImageButton) getChildAt(0);
        mTextCounter = (TextView) getChildAt(1);
        addToCart = (AppCompatImageButton) getChildAt(2);

        setClickListener();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        long currentClickTime = SystemClock.uptimeMillis();
        long elapsedTime = currentClickTime - mLastClickTime;

        mLastClickTime = currentClickTime;

        if (elapsedTime <= MIN_CLICK_INTERVAL)
            return;

        int i = v.getId();
        if (i == R.id.btn_decrease) {

            if (tvValCounter > 0) {

                if (minFixToOne && tvValCounter == 1) {
                    return;
                }

                tvValCounter--;
                mTextCounter.setText(String.valueOf(tvValCounter));
                if (tvValCounterChangeListener != null) {
                    tvValCounterChangeListener.onValueDelete(tvValCounter);
                }
            }


        } else if (i == R.id.btn_increase) {
            tvValCounter++;
            removeItem.setEnabled(true);
            mTextCounter.setText(String.valueOf(tvValCounter));
            if (tvValCounterChangeListener != null) {
                tvValCounterChangeListener.onValueAdd(tvValCounter);
            }

        }
        checkFabColor();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkFabColor() {
        if (tvValCounter > 0) {
            removeItem.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, android.R.color.black)));
        } else {
            removeItem.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(mContext, android.R.color.darker_gray)));
        }
    }

    private void setClickListener() {
        removeItem.setOnClickListener(this);
        addToCart.setOnClickListener(this);
    }

    /**
     * get the counter value
     *
     * @return current value of counter
     */
    public int getCounterValue() {
        return tvValCounter;
    }

    /**
     * set the current value of counter
     *
     * @param tvValCounter
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setCounterValue(int tvValCounter) {
        this.tvValCounter = tvValCounter;
        mTextCounter.setText(String.valueOf(this.tvValCounter));
        checkFabColor();
    }

    public void minFixToOne(boolean enable) {
        minFixToOne = enable;
    }

    public void addCounterValueChangeListener(@NonNull CounterValueChangeListener listener) {
        tvValCounterChangeListener = listener;
    }

    public interface CounterValueChangeListener {
        void onValueAdd(int count);

        void onValueDelete(int count);
    }
}
