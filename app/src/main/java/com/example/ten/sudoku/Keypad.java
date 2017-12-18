package com.example.ten.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class Keypad extends Dialog{
    protected static final String TAG = "Sudoku";
    private final View keys[] = new View[10];
    private final int useds[];
    private final PuzzleView puzzleView;

    public Keypad(Context context , int useds[], PuzzleView puzzleView){
        super(context);
        this.useds = useds;
        this.puzzleView = puzzleView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setTitle(R.string.keypad_title);
        setContentView(R.layout.keypad);
        findView();

        for (int element : useds){
            keys[element].setVisibility(View.INVISIBLE);
        }
        setListeners();
    }

    private void findView() {
        keys[0] = findViewById(R.id.keypad_clear);
        keys[1] = findViewById(R.id.keypad_1);
        keys[2] = findViewById(R.id.keypad_2);
        keys[3] = findViewById(R.id.keypad_3);
        keys[4] = findViewById(R.id.keypad_4);
        keys[5] = findViewById(R.id.keypad_5);
        keys[6] = findViewById(R.id.keypad_6);
        keys[7] = findViewById(R.id.keypad_7);
        keys[8] = findViewById(R.id.keypad_8);
        keys[9] = findViewById(R.id.keypad_9);
    }

    private void setListeners() {
        for (int i = 0; i < keys.length; i++){
            final int t = i;
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    returnResult(t);
                }
            });
        }

    }
    @Override
    public boolean onKeyDown (int keycode,KeyEvent event){
        int tile = 0;
        switch (keycode){
            case KeyEvent.KEYCODE_0:
            case KeyEvent.KEYCODE_SPACE:
                tile = 0;
                break;
            case KeyEvent.KEYCODE_1:
                tile = 1;
                break;
            case KeyEvent.KEYCODE_2:
                tile = 2;
                break;
            case KeyEvent.KEYCODE_3:
                tile = 3;
                break;
            case KeyEvent.KEYCODE_4:
                tile = 4;
                break;
            case KeyEvent.KEYCODE_5:
                tile = 5;
                break;
            case KeyEvent.KEYCODE_6:
                tile = 6;
                break;
            case KeyEvent.KEYCODE_7:
                tile = 7;
                break;
            case KeyEvent.KEYCODE_8:
                tile = 8;
                break;
            case KeyEvent.KEYCODE_9:
                tile = 9;
                break;
                default: return super.onKeyDown(keycode,event);
        }

        if (isValid(tile)){
            returnResult(tile);
        }
        return true;
    }
    private boolean isValid(int tile){
        for (int t : useds){
            if (tile == t)
                return false;
        }
        return true;
    }
    private void returnResult(int tile){
        puzzleView.setSelectedTile(tile);
        dismiss();
    }


}

