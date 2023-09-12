package Java;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicPlayer{
    public static void playmusicforever(String path){
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    MusicPlayer.class.getResourceAsStream(path));

            // 获取音频剪辑
            Clip clip = AudioSystem.getClip();

            // 打开音频剪辑并开始播放
            clip.open(audioInputStream);

            // 设置循环播放（无限循环）
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // 播放一段时间后停止（可以根据需要调整）
            Thread.sleep(50000000);

            // 停止播放并关闭剪辑和音频输入流
            clip.stop();
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void playmusiconce(String path){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    MusicPlayer.class.getResourceAsStream(path));

            // 获取音频剪辑
            Clip clip = AudioSystem.getClip();

            // 打开音频剪辑并开始播放
            clip.open(audioInputStream);
            // 获取音量控制



            // 设置循环播放（无限循环）
            clip.loop(0);

            // 播放一段时间后停止（可以根据需要调整）
            Thread.sleep(4000);

            // 停止播放并关闭剪辑和音频输入流
            clip.stop();
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            System.out.println("播放失败");
            e.printStackTrace();
        }
    }
}