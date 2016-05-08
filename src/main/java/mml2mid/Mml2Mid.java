package mml2mid;

import static java.lang.String.*;

/**
 * Created by lasta on 2016/05/09.
 */
public class Mml2Mid {
    private int octave;
    private int length;
    private String scale;

    // オクターブのデフォルトは4 (A4 = 440)
    private int currentOctave = 4;

    public int getCurrentOctave() {
        return currentOctave;
    }

    public void setCurrentOctave(int currentOctave) {
        this.currentOctave = currentOctave;
    }


    public Mml2Mid(char scale) {
    }

    private int scaleName2noteNumber(String scale, int accidental, int octave) {
        int note;
        int baseNoteNum;
        int acc;

        // TODO: これあってる?
        switch(String.valueOf(scale)) {
            case "a" : baseNoteNum = 9; break;
            case "b" : baseNoteNum = 11; break;
            case "c" : baseNoteNum = 0; break;
            case "d" : baseNoteNum = 2; break;
            case "e" : baseNoteNum = 4; break;
            case "f" : baseNoteNum = 5; break;
            case "g" : baseNoteNum = 7; break;
                // defaultにはいかない
            default : baseNoteNum = 0;
                break;
        }

        switch(accidental) {
            case -1 : baseNoteNum--; break;
            case 0  : break;
            case 1  : baseNoteNum++; break;
            default : break;
        }

        // 国際式のオクターブ表記
        // c4 : 真ん中のド
        note = baseNoteNum + (octave + 1) * 12;
        return note;
    }
}
