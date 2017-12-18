package com.example.ten.sudoku;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class MainActivity extends AppCompatActivity
                            implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View btnAbout = (Button)findViewById(R.id.about_button);
        btnAbout.setOnClickListener(this);

        View btnNewGame = findViewById(R.id.new_game_button);
        btnNewGame.setOnClickListener(this);

        View btnContinue = findViewById(R.id.continue_button);
        btnContinue.setOnClickListener(this);

        View btnExit = findViewById(R.id.exit_button);
        btnExit.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_button:
                startGame(PuzzleActivity.DIFFICULTY_CONTINUE);
                break;
            case R.id.about_button:
                Intent i = new Intent(this,AboutActivity.class);
                startActivity(i);
                break;
            case R.id.new_game_button:
                openNewGameDiaLog();
                break;
            case R.id.exit_button:
                finish();
                break;
        }
    }
    public boolean onCreateOptionMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_settings){
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    private static final String TAG = "Sudoku";

    private void openNewGameDiaLog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(R.string.new_game_title);

        dialog.setItems(R.array.difficulty,new DialogInterface.OnClickListener(){
            @Override
                    public void onClick(DialogInterface dialog,int i ){
                startGame(i);
            }
        });
        dialog.show();
    }

    private void startGame(int i) {
        Log.d(TAG,"คุณเลือก"+i);

        Intent intent = new Intent(this,PuzzleActivity.class);
        intent.putExtra(PuzzleActivity.KEY_DIFFICULTY,i);
        startActivity(intent);
    }
}
