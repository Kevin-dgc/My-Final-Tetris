import javax.sound.sampled.*;
import java.io.File;
import java.io.*;


public class MusicPlayer{
    
    private static Clip backgroundClip; 
    private static long clipPosition = 0;
    public static int[] volumes = {-80,-70,-51, -42, -32, -22, -13, -3, 6,}; 
    // 9 values here 1=9 , 0-8
    //range -80.0 to 6.0206 db
    public static double volumeNUM = 5;
    
    public static Clip playBackgroundTheme() {
         try (InputStream inputStream = MusicPlayer.class.getResourceAsStream("/theme.wav");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
             
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedInputStream);            
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volumes[(int)volumeNUM-1]); 
            // range -80.0 to 6.0206 db
            
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
            clip.start();
            backgroundClip = clip; 
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            }
    }
    
    public static void newVolume(){
        if(backgroundClip != null){
            FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volumes[(int)volumeNUM]);        
        }
    }

    public static void pauseBackgroundTheme() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            clipPosition = backgroundClip.getMicrosecondPosition();
            backgroundClip.stop();
        }
    }

    public static void resumeBackgroundTheme() {
        if (backgroundClip != null && !backgroundClip.isRunning()) {
            backgroundClip.setMicrosecondPosition(clipPosition); 
            backgroundClip.start();
        }
    }

    public static void stopBackgroundTheme() {
        if (backgroundClip != null) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }
    
    public static void playSound(int num){
        String filePath = "";
        if(num == 1){
            filePath = "/rotation.wav";
        }
        if(num == 2){
            filePath = "/delete line.wav";
        }
        if(num == 3){
            filePath = "/touch floor.wav";
        }
        if(num == 4){
            filePath = "/gameover.wav";
        }
        if(num == 5){
            filePath = "/theme.wav";
        }
         try (InputStream inputStream = MusicPlayer.class.getResourceAsStream(filePath);
         BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

        AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedInputStream);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        int vol = (int)volumeNUM-1;
        if(vol < 0){
            vol = 0;
        }
        gainControl.setValue(volumes[vol]);
        clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }