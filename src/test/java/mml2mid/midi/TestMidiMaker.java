package mml2mid.midi;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Created by lasta on 2016/05/09.
 */
public class TestMidiMaker {
    public static void main(String[] args) {
        MidiMaker midiMaker = new MidiMaker();
        try {
            midiMaker.MidiMaker();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }
}