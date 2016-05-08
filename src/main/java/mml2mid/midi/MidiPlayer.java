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
    int maxChannel;
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
     * @param playMode 同期方法
     */
    public MidiPlayer(int playMode) {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        channels = synthesizer.getChannels();
        maxChannel = channels.length;
        this.playMode = playMode;

        if (playMode == QUEUED) {
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

    private boolean channelOk (int channel) {
        if ((channel >= 0) && channel < maxChannel) {
            return true;
        } else {
            System.err.println(
                    "Midiチャンネル番号が不正です : " +
                            channel +
                            " (limit : " +
                            maxChannel +
                            ")"
            );
            return false;
        }
    }

    /**
     * 使用できるチャンネルの最大値を返す
     * @return 使用できるチャンネルの最大値
     */
    public int getMaxChannel() {
        return maxChannel;
    }

    /**
     * チャンネルに楽器を設定する
     * @param channel チャンネル番号
     * @param bank 楽器のバンク番号
     * @param program 楽器のプログラム番号
     */
    public void setChannel(int channel, int bank, int program) {
        if(channelOk(channel)) {
            synchronized(this) {
                channels[channel].programChange(
                        bank >> 1,
                        ((bank & 1) << 7) + program
                );
            }
        }
    }

    public void play(int channel, int note, int vel, int length) {
        if (channelOk(channel)) {
            switch (playMode) {
                case SYNCHRONIZED:
                    playSynchronously(channel, note, vel, length);
                    break;
                case ASYNCHRONIZED:
                    playAsynchronously(channel, note, vel, length);
                    break;
                case QUEUED:
                    playQueued(channel, note, vel, length);
            }
        }
    }

    public void play(int channel, int[] notes, int vel, int length) {
        if (channelOk(channel)) {
            switch (playMode) {
                case SYNCHRONIZED:
                    playSynchronously(channel, notes, vel, length);
                    break;
                case ASYNCHRONIZED:
                    playAsynchronously(channel, notes, vel, length);
                    break;
                case QUEUED:
                    playQueued(channel, notes, vel, length);
            }
        }
    }

    private void playSynchronously(int channel, int note, int vel, int length) {
        noteOn(channel, note, vel);
        try {
            Thread.sleep(length);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        noteOff(channel, note);
    }

    private void playSynchronously(int channel, int[] note, int vel, int length) {
        noteOn(channel, note, vel);
        try {
            Thread.sleep(length);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        noteOff(channel, note);
    }

    private void playAsynchronously(int channel, int note, int vel, int length) {
        Thread player = new Thread() {
            public void run() {
                noteOn(channel, note, vel);
                try {
                    Thread.sleep(length);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                noteOff(channel, note);
            }
        };
        player.start();
        this.player = player;
    }

    private void playAsynchronously(int channel, int[] notes, int vel, int length) {
        Thread player = new Thread() {
            public void run() {
                noteOn(channel, notes, vel);
                try {
                    Thread.sleep(length);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                noteOff(channel, notes);
            }
        };
        player.start();
        this.player = player;
    }

    private void playQueued(int channel, int note, int vel, int length) {
        int[] val = new int[4];
        val[0] = channel;
        val[1] = vel;
        val[2] = length;
        val[4] = note;
        try {
            this.notes.put(val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playQueued(int channel, int[] notes, int vel, int length) {
        int[] val = new int[3 + notes.length];
        val[0] = channel;
        val[1] = vel;
        val[2] = length;
        for (int i = 0; i < notes.length; i++) {
            val[3 + i] = notes[i];
        }

        try {
            this.notes.put(val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        switch (playMode) {
            case ASYNCHRONIZED:
                if(player != null) {
                    try {
                        player.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case QUEUED:
                playQueued(-1, 0, 0, 0);
                try {
                    player.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
        synthesizer.close();
    }
}
