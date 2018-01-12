package com.game.amw.game2048;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by amw on 2018/1/10.
 */

public class Card extends FrameLayout {


    private int num;
    private TextView lab;

    public Card(Context context) {
        super(context);
        initView();
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Card(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        //setBackgroundColor();
        lab = new TextView(getContext());
        lab.setTextSize(32);
        lab.setGravity(Gravity.CENTER);
        setNum(0);
        LayoutParams layoutParams = new LayoutParams(-1,-1);
        lab.setBackgroundColor(getResources().getColor(R.color.Card_color));
        layoutParams.setMargins(10,10,10,10);
        addView(lab,layoutParams);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num==0)
            lab.setText("");
        else
            lab.setText(""+num);
    }



/*    public void startAnimotion() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(lab,"scaleX", 1f, 5f, 1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(lab,"scaleY", 1f, 5f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.playTogether(animator,animator2);
        animator.start();

    }*/
}
