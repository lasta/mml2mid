package mml2mid.midi;

import javax.sound.midi.*;
import java.io.IOException;

/**
 * Created by lasta on 2016/05/08.
 */
public class MidiMaker {
    public void MidiMaker() throws InvalidMidiDataException {
        // TODO: 24tick = 4分音符 ??
        Sequence sequence = new Sequence(Sequence.PPQ, 24);
        Track track = sequence.createTrack();

        // MidiMessage
        MetaMessage metaMessage = new MetaMessage();

        // TODO: 本来外部から設定すべきパラメタ
        int channel = 0;
        int scale = 60; // C4
        int vel = 100;
        int instrument = 0; // piano

        // TODO: Tempoの設定
        /*
        Tempoの設定
         */
        final int tempo = 135;
        int l = 60 * 1000000 / tempo;
        metaMessage.setMessage(
                0x51,
                new byte[]{
                        // TODO: 割るのださいのでbit演算で
                        (byte) (l / 65536),
                        (byte) (l % 65536 / 256),
                        (byte) (l % 256)
                },
                3
        );

        /*
        音色の設定
         */
        ShortMessage shortMessage = new ShortMessage();
        shortMessage.setMessage(
                ShortMessage.PROGRAM_CHANGE,
                channel,
                instrument,
                0
        );

        /*
        音を鳴らす
         */
        shortMessage = new ShortMessage();
        shortMessage.setMessage(
                ShortMessage.NOTE_ON,
                channel,
                scale,
                vel
        );
        track.add(new MidiEvent(shortMessage, 0));
        // 4分音符とするので、24tick後Note Off
        shortMessage = new ShortMessage();
        shortMessage.setMessage(
                ShortMessage.NOTE_OFF,
                channel,
                scale,
                vel
        );
        track.add(new MidiEvent(shortMessage, 24));

        /*
        Midiファイルの書き出し
         */
        try {
            MidiSystem.write(
                    sequence,
                    0,
                    new java.io.File("test.mid")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
