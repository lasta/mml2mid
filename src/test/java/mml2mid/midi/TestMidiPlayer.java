package mml2mid.midi;

/**
 * Created by lasta on 2016/05/09.
 */
public class TestMidiPlayer {
    public static void main(String[] args) {
        MidiPlayer player = new MidiPlayer(MidiPlayer.SYNCHRONIZED);

        /*
        channel 1
        bank    1
        program 0 : Piano
         */
        player.setChannel(1, 1, 0);

        System.out.println("start");

        player.play(0,  0,   0, 1000); // rest
        player.play(0, 60, 100, 1000); // C4
        player.play(0, 62, 100, 125); // D
        player.play(0, 64, 100, 125); // E
        player.play(0, 65, 100, 125); // F
        player.play(0, 67, 100, 125); // G
        player.play(0, 69, 100, 125); // A
        player.play(0, 71, 100, 125); // B
        player.play(0, 72, 100, 1000); // C5

        System.out.println("end");
        player.close();
    }
}
