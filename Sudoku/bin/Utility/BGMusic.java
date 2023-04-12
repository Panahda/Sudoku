package Sudoku.bin.Utility;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class BGMusic {
    private Clip clip;
    
    public BGMusic(String filename) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Sudoku/bin/Utility/music/" + filename).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        System.out.println("music playing...");
    }
    
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
