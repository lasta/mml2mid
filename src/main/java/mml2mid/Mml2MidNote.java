package mml2mid;

import static java.lang.String.*;

/**
 * Created by lasta on 2016/05/09.
 */
public class Mml2MidNote extends MidNote{
    private int note;
    private int length;
    private int vel;

    public Mml2MidNote(char scale, char accidental, int octave, int length, int vel) {
        this.note = scaleName2noteNumber(scale, accidental, octave);
        this.length = lengthMml2Mid(length);
        this.vel = vel;
    }


    // TODO: テンポに合わせて長さを修正
    // 現状は 120BPM
    // TODO: MidiPlayer用に特化している
    private int lengthMml2Mid(int length) {
        final double fourth = 2;
        switch(length) {
            case 1
                return (int) (4000 / fourth);
            case 2:
                return (int) (2000 / fourth);
            case 4:
                return (int) (1000 / fourth);
            case 8:
                return (int) (500 / fourth);
            case 16:
                return (int) (250 / fourth);
            case 32:
                return (int) (125 / fourth);
            default:
                return (int) (1000 / fourth);
        }
    }
    private int scaleName2noteNumber(char scale, char accidental, int octave) {
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
            case '-' : baseNoteNum--; break;
            case '+' : baseNoteNum++; break;
            default : break;
        }

        // 国際式のオクターブ表記
        // c4 : 真ん中のド
        note = baseNoteNum + (octave + 1) * 12;
        return note;
    }
}
