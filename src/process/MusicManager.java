package process;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicManager {
	
	private Clip audioClip;
	private String music;
	
	public MusicManager() {
		music = "ambiance";
		String file = "music/zeldaSong8bits.wav";
		File audioFile = new File(file);

		AudioInputStream audioStream;

		try {

			audioStream = AudioSystem.getAudioInputStream(audioFile);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			
			this.audioClip = (Clip) AudioSystem.getLine(info);

			audioClip.open(audioStream);
				
			audioClip.start();
			
			audioStream.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void playMusicBackground(String newMusic) throws LineUnavailableException {

		music = newMusic;
		String file;
		if (music.equals("ambiance")) {
			file = "music/zeldaSong8bits.wav";
		}
		else if (music.equals("attack")){
			file = "music/attackSong.wav";
		}
		else if (music.equals("sword")){
			file = "music/swordSong.wav";
		}
		else if (music.equals("heart")){
			file = "music/heartSong.wav";
		}
		else {
			file = "music/bazookaSong.wav";
		}
		File audioFile = new File(file);

		AudioInputStream audioStream;

		try {

			audioStream = AudioSystem.getAudioInputStream(audioFile);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);
			
			Clip otherAudioClip = (Clip) AudioSystem.getLine(info);

			otherAudioClip.open(audioStream);
				
			otherAudioClip.start();
			
			audioStream.close();

		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// Coupes la musique de fond
	public void stopMusicBackGround() {
		audioClip.stop();
	}
	
	public void upDateMusic(String music) {
		try {
			this.music = music;
			playMusicBackground(music);
			//TROP FACILE DAMIEN EN FAIT MDRR
		}
		catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
