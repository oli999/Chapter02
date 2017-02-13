package com.gura.step05game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-02-10.
 */

public class GameView extends View {
    //배경이미지
    Bitmap backImg;
    //View 의 폭과 높이
    int viewWidth, viewHeight;
    //배경이미지1 의 y 좌표
    int back1Y, back2Y;
    //배경이미지 스크롤 속도
    int scrollSpeed;
    //드레곤의 좌표
    int unitX, unitY;
    //드레곤의 크기
    int unitW, unitH;
    //드레곤의 이미지
    Bitmap[] unitImgs=new Bitmap[2];
    //드레곤의 이미지 인덱스
    int unitIndex;

    //action down 이 일어난곳의 x 좌표
    int lastX;
    // 유닛의 x 좌표 최대값 , 최소값
    int minUnitX, maxUnitX;

    //Missile 관련 필드
    List<Missile> missList=new ArrayList<>();
    Bitmap missImg; //미사일 이미지
    int missW, missH; //미사일의 폭과 높이
    int missSpeed; //미사일의 속도

    //count 를 셀 변수
    int count;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    /*
        onDraw() 메소드가 호출되기 직전에 먼저 호출되면서
        View 가 차지하고 있는 크기가 메소드의 인자로 전달된다.
        혹은 도중에 View 가 차지하는 크기가 바뀌어도 호출된다.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //인자로 전달된 화면의 폭과 높이를 맴버필드에 저장한다.
        viewWidth=w;
        viewHeight=h;

        //초기화 메소드 호출
        init();

    }
    //초기화 하는 메소드
    public void init(){
        //스크롤 스피드 지정
        scrollSpeed = viewHeight/100;

        //배경이미지의 초기 좌표 지정
        back1Y=0;
        back2Y=-viewHeight;

        //배경이미지 읽어들이기
        Bitmap backImg=BitmapFactory
                .decodeResource(getResources(), R.drawable.backbg);
        //배경이미지를 View 의 가로세로에 일치하게 스케일링해서
        //맴버필드에 저장한다.
        this.backImg=Bitmap
                .createScaledBitmap(backImg, viewWidth, viewHeight, false);
        //드레곤의 크기지정
        unitW = viewWidth/5; //화면폭의 1/5
        unitH = unitW; //높이는 폭과 같도록
        //드레곤의 초기 좌표
        unitX = viewWidth/2;
        unitY = viewHeight - unitH*2;

        //드레곤의 최소 x 좌표, 최대 y 좌표
        //minUnitX = unitW/2;
        //maxUnitX = viewWidth-unitW/2;
        minUnitX=0;
        maxUnitX=viewWidth;

        //드레곤 이미지 읽어들이기
        Bitmap unitImg1=BitmapFactory
                .decodeResource(getResources(), R.drawable.unit1);
        //드레곤 이미지 스케일링
        unitImg1=Bitmap
                .createScaledBitmap(unitImg1, unitW, unitH, false);

        //드레곤 이미지 읽어들이기
        Bitmap unitImg2=BitmapFactory
                .decodeResource(getResources(), R.drawable.unit2);
        //드레곤 이미지 스케일링
        unitImg2=Bitmap
                .createScaledBitmap(unitImg2, unitW, unitH, false);
        //2개의 드레곤 이미지를 Bitmap[] 에 저장
        unitImgs[0]=unitImg1;
        unitImgs[1]=unitImg2;


        //미사일의 폭과 높이 결정하기
        missW = unitW;
        missH = unitH;

        //미사일 이미지 읽어들이고 스케일링하기
        Bitmap missImg = BitmapFactory
                .decodeResource(getResources(), R.drawable.mi1);
        this.missImg = Bitmap.createScaledBitmap(missImg, missW, missH, false);
        //미사일의 진행 속도
        missSpeed = viewHeight / 100;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //배경 이미지 그리기
        // .drawBitmap(이미지, x, y, 효과)
        canvas.drawBitmap(backImg, 0, back1Y, null);
        canvas.drawBitmap(backImg, 0, back2Y, null);
        //미사일 이미지 그리기
        for(Missile tmp:missList){
            canvas.drawBitmap(missImg, tmp.getX()-missW/2,
                    tmp.getY()-missW/2, null);
        }

        //드레곤 이미지 그리기
        canvas.drawBitmap(unitImgs[unitIndex], unitX-unitW/2, unitY-unitH/2, null);
        //배경이미지 스크롤 처리
        backScroll();
        //미사일 만들기
        makeMissile();
        //미사일 움직이기
        moveMissile();
        checkMissile();
        //유닛 애니메이션 처리
        unitAnimation();

        count++; //카운트 증가 시키기
    }

    public void unitAnimation(){
        //너무 자주 바뀌지 않도록
        if(count%20 != 0){
            return;
        }
        //인덱스를 1 증가 시키고
        unitIndex++;
        if(unitIndex==2){//만일 존재하지 않는 인덱스라면
            // 인덱스를 0으로 고정한다.
            unitIndex=0;
        }
    }

    //배열에서 제거할 미사일은 제거하는 메소드
    public void checkMissile(){
        //배열에 저장된 Missile 객체를 마지막번째 방에서 부터 하나씩 불러와서
        //배열에서 제거할 Missile 객체는 제거한다.
        for(int i=missList.size()-1 ; i>=0; i--){
            Missile tmp=missList.get(i);
            if(tmp.isDead()){ //제거해야할 Misslie 객체라면
                //i번째 인덱스에 있는 객체를 제거한다.
                missList.remove(i);
            }
        }
    }

    //미시일 움직이는 메소드
    public void moveMissile(){
        for(Missile tmp:missList){
            //미사일의 현재 y 좌표에서 속도값 만큼 뺀값을 얻어낸다.
            int resultY=tmp.getY()-missSpeed;
            //현재 미사일 객체에 넣어주기
            tmp.setY(resultY);
            //y좌표가 위쪽 화면을 벗어 났을때
            if(resultY < -missH/2){
                //배열에서 제거 될수 있도록 표시한다.
                tmp.setDead(true);
            }
        }
    }

    //미사일 만드는 메소드
    public void makeMissile(){
        if(count%10 != 0){
            return;
        }
        //효과음 재생하기
        Util.SoundManager.getInstance().play(MyConstants.SOUND_FIRE);
        //미사일 객체 생성하기
        Missile m=new Missile();
        m.setX(unitX);
        m.setY(unitY);
        //배열에 저장
        missList.add(m);
    }

    //배경이미지 스크롤 처리
    public void backScroll(){
        back1Y += scrollSpeed;
        back2Y += scrollSpeed;
        //배경1이 한계점에 다다랏을때
        if(back1Y >= viewHeight){
            back1Y = -viewHeight;
            back2Y = 0;
        }
        //배경2가 한계점에 다다랏을때
        if(back2Y >= viewHeight){
            back2Y = -viewHeight;
            back1Y = 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //이벤트가 일어난 곳의 x 좌표 얻어오기
        int eventX=(int)event.getX();
        int eventY=(int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //터치가 시작 되었을때 x 좌표를 맴버필드에 저장
                lastX=eventX;
                break;
            case MotionEvent.ACTION_MOVE:
                //이벤트가 일어난 x 좌표와 터치가 시작된 좌표의 차이를 구한다.
                int delteX=lastX-eventX;
                //드레곤의 x 좌표에 반영한다.
                unitX=unitX-delteX;
                //현재의 x 좌표는 다음번 action move 될때 과거의 좌표이다.
                lastX=eventX;
                //왼쪽으로 벗어나지 않도록
                if(unitX < minUnitX){
                    unitX = minUnitX;
                }
                //오른쫏으로 벗어나지 않도록
                if(unitX > maxUnitX){
                    unitX = maxUnitX;
                }
                break;
        }
        return true;
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            invalidate();//화면 갱신하기
            // 20/1000 초 이후에 hander 객체에 빈 메세지 보내기
            handler.sendEmptyMessageDelayed(0, 20);
        }
    };
    //게임을 시작하는 메소드
    public void startGame(){
        //핸들러 객체에 메세지를 보내서 게임이 진행되도록 한다.
        handler.sendEmptyMessage(0);
    }
    //게임을 일시 정지하는 메소드
    public void pauseGame(){
        //핸들러에 메세지를 제거해서 게임이 진행되지 않도록 한다.
        handler.removeMessages(0);
    }

}




















