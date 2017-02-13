package com.gura.step05game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    GameView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            case R.id.menu_start:
                view.startGame();
                break;
            case R.id.menu_pause:
                view.pauseGame();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}







