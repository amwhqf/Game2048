package com.game.amw.game2048;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by amw on 2018/1/9.
 */

public class CardGrid extends GridLayout {
    private static final String TAG = "CardGrid";
    Card cardItem;
    float curx = 0, cury = 0, movex, movey;
    long curtime = 0;
    long time = 0;

    List<Point> pointList = new ArrayList<Point>();
    private Card[][] cardlist = new Card[4][4];

    int cardwith = 0;
    long score_num = 0;
    long score_num_max = 0;

    public void setTv_score(TextView tv_score) {
        this.tv_score = tv_score;
    }

    public void setTv_score_max(TextView tv_score_max) {
        this.tv_score_max = tv_score_max;
    }

    TextView tv_score;
    TextView tv_score_max;

    public long getScore_num() {
        return score_num;
    }

    public long getScore_num_max() {
        return score_num_max;
    }


    public CardGrid(Context context) {
        super(context);
        initView();
    }

    public CardGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CardGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {

        //Log.d(TAG, "initView:getWidth "+getWidth());
        //addCard(cardwith, cardwith);
        setColumnCount(4);
        if (pointList.size() > 0) {
            ranDonXY();
            ranDonXY();
        }
        setBackgroundColor(getResources().getColor(R.color.CardGrid_color));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        curtime = event.getDownTime();
                        curx = event.getX();
                        cury = event.getY();
                        //Log.d(TAG, "onTouch: ACTION_DOWN" + curx + " " + cury);
                        break;
                    case MotionEvent.ACTION_UP:
                        time = event.getEventTime();
                        movex = event.getX() - curx;
                        movey = event.getY() - cury;
                        //Log.d(TAG, "onTouch: diff" + (time - curtime));

                        if ((time - curtime) < 10) {

                        } else {
                            if (Math.abs(movex) > Math.abs(movey)) {
                                Log.d(TAG, "onTouch: movex");
                                if (movex < -50) {
                                    MoveLeft();
                                } else if (movex > 50) {
                                    MoveRight();
                                } else
                                    ;
                            } else {
                                Log.d(TAG, "onTouch: movey");
                                if (movey < -50) {
                                    MoveTop();
                                } else if (movey > 50)
                                    MoveDown();
                                else
                                    ;
                            }

                            tv_score.setText("" + score_num);
                            tv_score_max.setText("" + score_num_max);
                        }
                        break;
                }
                return true;
            }
        });

    }

    private void MoveTop() {
        boolean flag = false;
        Log.d(TAG, "MoveTop: ");
        for (int y = 0; y < 4; y++) {
            for (int z = 0; z < 3; z++) {
                for (int x = 0; x + 1 < 4; x++) {
                    int cur = cardlist[x][y].getNum();
                    int tmp = cardlist[x + 1][y].getNum();
                    if (cur == tmp || cur == 0) {
                        cardlist[x][y].setNum(cur + tmp);
                        cardlist[x + 1][y].setNum(0);
                        if (cur > 0 && cur == tmp) {
                            flag = true;
                            score_num = score_num + tmp + cur;
                            score_num_max = Math.max(score_num, score_num_max);
                        } else if (cur == 0 && tmp > 0) {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag) {
            ResetPointList();
            ranDonXY();
        }


    }

    private void MoveDown() {
        boolean flag = false;
        Log.d(TAG, "MoveDown: ");
        for (int y = 0; y < 4; y++) {
            for (int z = 0; z < 3; z++) {
                for (int x = 3; x > 0; x--) {
                    int cur = cardlist[x][y].getNum();
                    int tmp = cardlist[x - 1][y].getNum();
                    if (cur == tmp || cur == 0) {
                        cardlist[x][y].setNum(cur + tmp);
                        cardlist[x - 1][y].setNum(0);
                        if (cur > 0 && cur == tmp) {
                            flag = true;
                            score_num = score_num + tmp + cur;
                            score_num_max = Math.max(score_num, score_num_max);
                        } else if (cur == 0 && tmp > 0) {
                            flag = true;
                        }
                    }
                }
            }
        }
        if (flag) {
            ResetPointList();
            ranDonXY();
        }
    }


    private void MoveRight() {
        Log.d(TAG, "MoveRight: ");
        boolean flag = false;
        for (int x = 0; x < 4; x++) {
            for (int z = 0; z < 3; z++) {
                for (int y = 3; y > 0; y--) {
                    int cur = cardlist[x][y].getNum();
                    int tmp = cardlist[x][y - 1].getNum();
                    if (cur == tmp || cur == 0) {
                        cardlist[x][y].setNum(cur + tmp);
                        cardlist[x][y - 1].setNum(0);
                        if (cur > 0 && cur == tmp) {
                            flag = true;
                            score_num = score_num + tmp + cur;
                            score_num_max = Math.max(score_num, score_num_max);
                        } else if (cur == 0 && tmp > 0) {
                            flag = true;
                        }
                    }
                }
            }
        }
        if (flag) {
            ResetPointList();
            ranDonXY();
        }
    }

    private void MoveLeft() {
        boolean flag = false;
        Log.d(TAG, "MoveLeft: ");
        for (int x = 0; x < 4; x++) {
            for (int z = 0; z < 3; z++) {
                for (int y = 0; y + 1 < 4; y++) {
                    int cur = cardlist[x][y].getNum();
                    int tmp = cardlist[x][y + 1].getNum();
                    if (cur == tmp || cur == 0) {
                        cardlist[x][y].setNum(cur + tmp);
                        cardlist[x][y + 1].setNum(0);
                        if (cur > 0 && cur == tmp) {
                            flag = true;
                            score_num = score_num + tmp + cur;
                            score_num_max = Math.max(score_num, score_num_max);
                        } else if (cur == 0 && tmp > 0) {
                            flag = true;
                        }

                    }
                }
            }
        }

        if (flag) {
            ResetPointList();
            ranDonXY();
        }
    }

    private void ResetPointList() {
        pointList.clear();
        Log.d(TAG, "ResetPointList: " + pointList.size());
        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 4; y++) {
                cardlist[i][y].setBackgroundColor(getResources().getColor(R.color.Card_color));
                int num = cardlist[i][y].getNum();
                if (num > 0)
                    continue;
                else {
                    Point point = new Point(i, y);
                    pointList.add(point);
                }
            }
        }
        Log.d(TAG, "ResetPointList: " + pointList.size());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "addCard: " + w + " " + h);

        super.onSizeChanged(w, h, oldw, oldh);
        cardwith = ((Math.min(w, h) - 20) / 4);
        addCard(cardwith, cardwith);
    }

    public int getCardwith() {
        return cardwith;
    }

    private void addCard(int cardwith, int cardheight) {
        Log.d(TAG, "addCard: " + cardwith + " " + cardheight);
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                Card card = new Card(getContext());
                cardlist[x][y] = card;
                addView(card, cardwith, cardheight);
                Point point = new Point(x, y);
                pointList.add(point);
            }
        }
    }

    private void ranDonXY() {
        int pos = getRandom(0, pointList.size());
        Log.d(TAG, "ranDonXY: pos=" + pos);

        if (pointList.size() > 0) {
            Point tmpPoint = pointList.get(pos);
            int num = getRandom(0, 100) > 5 ? 2 : 4;
            Log.d(TAG, "ranDonXY: num=" + num);
            Log.d(TAG, "ranDonXY: x,y=" + tmpPoint.x + "," + tmpPoint.y);
            cardlist[tmpPoint.x][tmpPoint.y].setNum(num);
            // cardlist[tmpPoint.x][tmpPoint.y].startAnimotion();
            cardlist[tmpPoint.x][tmpPoint.y].setBackgroundColor(getResources().getColor(R.color.colorAccent));
            pointList.remove(pos);
            Log.d(TAG, "ranDonXY: pointList.size()" + pointList.size());
        }
        if (pointList.size() == 0) {
            //判断是否gameover
            if (isGameOver() == true) {
                Log.d(TAG, "ranDonXY: pos=" + 0);
                Log.d(TAG, "ranDonXY: 开始新游戏..............");
                showGameOver();
            }


        }


    }

    private void showGameOver() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("游戏结束");
        TextView textView = new TextView(getContext());
        textView.setText("GAME OVER");
        textView.setTextSize(32);
        dialog.setView(textView);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newGame();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    public void newGame() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                cardlist[x][y].setNum(0);
            }
        }
        score_num = 0;
        tv_score.setText("0");
        ResetPointList();
        ranDonXY();
        ranDonXY();
    }

    private int getRandom(int start, int end) {
        int number = (int) (Math.random() * (end - start)) + start;
        return number;
    }

    private boolean isGameOver() {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                if (cardlist[x][y].getNum() == cardlist[x][y + 1].getNum()) {
                    Log.d(TAG, "isGameOver: x,y=" + x + "," + y);
                    return false;
                }
                if (x == 3) {
                    continue;
                } else {
                    if (cardlist[x][y].getNum() == cardlist[x + 1][y].getNum()) {
                        Log.d(TAG, "isGameOver: x,y=" + x + "," + y);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}


