package mml2mid.midi;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
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

}
