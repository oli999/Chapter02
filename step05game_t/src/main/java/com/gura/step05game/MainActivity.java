package com.gura.step05game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
                implements DialogInterface.OnClickListener{
    GameView view;
    //무음 모드인지 여부
    boolean isSilentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //화면 꺼지지 않도록 설정
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Util.keepScreenOn(this);

        //GameView 객체 생성해서 참조값을 변수에 담기
        view=new GameView(this);
        //GameView 객체로 화면을 모두 체울수 있도록
        setContentView(view);
        /*
        //SoundPool 객체를 생성하고
        SoundPool soundPool=
                new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        //효과음을 로딩 시키고 효과음의 아이디를 얻어낸다.(비동기로딩)
        int soundId = soundPool.load(this, R.raw.laser1, 1);
        //재생 시키는 방법
        soundPool.play(soundId, 1,1,1,0,1);
        */

        //Util 클래스에 있는 SoundManager의 참조값 얻어오기
        Util.SoundManager sManager=Util.SoundManager.getInstance();
        //초기화 하기 (Context type 데이터가 필요하다)
        sManager.init(this);
        //효과음 등록하기
        sManager.addSound(MyConstants.SOUND_FIRE, R.raw.laser1);
        sManager.addSound(MyConstants.SOUND_BOOM, R.raw.shoot1);
        sManager.addSound(MyConstants.SOUND_DIE, R.raw.birddie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //메뉴 전개자 객체를 이용해서 res/menu/menu_main.xml
        //문서를 전개해서 메뉴를 구성한다.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }
    //옵션 메뉴를 선택했을때 호출되는 메소드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_start: //게임 시작 선택
                view.startGame();
                break;
            case R.id.menu_pause: //일시 정지 선택
                view.pauseGame();
                break;
            case R.id.menu_sound: //무음 모드 선택
                if(isSilentMode){
                    isSilentMode=false;
                    item.setTitle("무음모드");
                }else{
                    isSilentMode=true;
                    item.setTitle("효과음 내기");
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //효과음을 재생하는 메소드
    public void playSound(int soundType){
        if(isSilentMode){
            return;
        }
        Util.SoundManager.getInstance().play(soundType);
    }
    //게임이 종료 되었을때 처리
    public void gameOver(){
        new AlertDialog.Builder(this)
                .setMessage("다시 시작 하겠습니까?")
                .setPositiveButton("예", this)
                .setNegativeButton("아니요", this)
                .create()
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                //필드 초기화 하고
                view.clearField();
                //게임 다시 시작 시키기
                view.startGame();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                finish();// 액티비티 종료
                break;
        }
    }
}







