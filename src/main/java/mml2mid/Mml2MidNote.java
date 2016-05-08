package mml2mid;

import static java.lang.String.*;

/**
 * Created by lasta on 2016/05/09.
 */
public class Mml2MidNote {
    private int note;
    private int length;

    public Mml2MidNote(char scale, char accidental, int octave, int length) {
        this.note = scaleName2noteNumber(scale, accidental, octave);
        this.length = lengthMml2Mid(length);
    }


    private int lengthMml2Mid(int length) {
        switch(length) {
            case 1:
                return 1920;
            case 2:
                return 960;
            case 4:
                return 480;
            case 8:
                return 240;
            case 16:
                return 120;
            case 32:
                return 60;
            default:
                return 480;
        }
    }
    private int scaleName2noteNumber(char scale, int accidental, int octave) {
        int note;
        int baseNoteNum;

        // TODO: これあってる?
        switch(scale) {
            case 'a' : baseNoteNum = 9; break;
            case 'b' : baseNoteNum = 11; break;
            case 'c' : baseNoteNum = 0; break;
            case 'd' : baseNoteNum = 2; break;
            case 'e' : baseNoteNum = 4; break;
            case 'f' : baseNoteNum = 5; break;
            case 'g' : baseNoteNum = 7; break;
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
