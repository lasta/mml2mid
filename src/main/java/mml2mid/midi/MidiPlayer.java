package mml2mid.midi;

import com.google.common.collect.Lists;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import static com.google.common.collect.Queues.newLinkedBlockingQueue;

/**
 * Created by lasta on 2016/05/08.
 */
public class MidiPlayer {
    Synthesizer synthesizer;
    MidiChannel[] channels;
    int max_channel;
    int playMode;

    // 同期的
    public static final int SYNCHRONIZED = 1;
    // 非同期的
    public static final int ASYNCHRONIZED = 2;
    // queue
    public static final int QUEUED = 3;
    BlockingQueue<int[]> notes;

    Thread player;

    /**
     * MidiSynthesizerのコンストラクタ
     *
     * @param play_mode 同期方法
     */
    public MidiPlayer(int play_mode) {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        channels = synthesizer.getChannels();
        max_channel = channels.length;
        this.playMode = playMode;

        if (play_mode == QUEUED) {
            notes = newLinkedBlockingQueue();
            startPlayer();
        }
    }

    private void startPlayer() {
        player = new Thread() {
            public void run() {
                while (true) {
                    try {
                        int[] note = notes.take();
                        if (note[0] == -1)
                            break;
                        for (int i = 3; i < note.length; i++) {
                            noteOn(note[0], note[i], note[1]);
                        }
                        sleep(note[2]);
                        for (int i = 3; i < note.length; i++) {
                            noteOff(note[0], note[i]);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        player.start();
    }

    private synchronized void noteOn(int channel, int note, int vel) {
        channels[channel].noteOn(note, vel);
    }

    private synchronized void noteOn(int channel, int[] notes, int vel) {
        for(int note : notes) {
            channels[channel].noteOn(note, vel);
        }
    }

    private synchronized void noteOff(int channel, int note) {
        channels[channel].noteOff(note);
    }

    private synchronized void noteOff(int channel, int[] notes) {
        for(int note : notes) {
            channels[channel].noteOff(note);
        }
    }
}
