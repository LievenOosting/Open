package com.qcj.common.util;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class MusicPlayer implements OnBufferingUpdateListener,
		OnCompletionListener, MediaPlayer.OnPreparedListener {
	public MediaPlayer mediaPlayer;
	private SeekBar skbProgress;
	private ProgressBar mProgressBar;
	private Timer mTimer = new Timer();

	/**
	 * @param skbProgress
	 */
	public MusicPlayer(SeekBar skbProgress) {
		this.skbProgress = skbProgress;
		try {
			initMediaPlay();
		} catch (Exception e) {
			Log.e("mediaPlayer", "error", e);
		}

		mTimer.schedule(mTimerTask, 0, 1000);
	}

	public MusicPlayer(ProgressBar TvProgress) {
		this.mProgressBar = TvProgress;
		initMediaPlay();
		mTimer.schedule(mTimerTask, 0, 1000);
	}

	private void initMediaPlay() {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setOnBufferingUpdateListener(this);
		mediaPlayer.setOnPreparedListener(this);
	}

	/*******************************************************
	 * 通过定时器和Handler来更新进度条
	 ******************************************************/
	TimerTask mTimerTask = new TimerTask() {
		@Override
		public void run() {
			if (mediaPlayer == null)
				return;
			if (skbProgress != null) {
				if (mediaPlayer.isPlaying() && skbProgress.isPressed() == false) {
					handleProgress.sendEmptyMessage(0);
				}
			}
			if (mProgressBar != null) {
				handleProgress.sendEmptyMessage(0);
			}
		}
	};

	Handler handleProgress = new Handler() {
		public void handleMessage(Message msg) {

			int position = mediaPlayer.getCurrentPosition();
			int duration = mediaPlayer.getDuration();
			Log.i("duration", duration + "");

			if (duration > 0) {
				if (skbProgress != null) {
					long pos = skbProgress.getMax() * position / duration;
					skbProgress.setProgress((int) pos);
				}
				if (mProgressBar != null) {
					int pos = (int) ((float) position / (float) duration * 100);
					mProgressBar.setProgress(pos);
				}
			}
		};
	};

	// *****************************************************

	public void play() {
		mediaPlayer.start();
	}

	public void setplayUrl(String musicUrl) {
		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(musicUrl);
			mediaPlayer.prepare();// prepare之后自动播放
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
	}

	@Override
	/**  
	 * 通过onPrepared播放  
	 */
	public void onPrepared(MediaPlayer arg0) {
		arg0.start();
		Log.e("mediaPlayer", "onPrepared");
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		Log.e("mediaPlayer", "onCompletion");
	}

	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int bufferingProgress) {
		// skbProgress.setSecondaryProgress(bufferingProgress);
		if (skbProgress != null) {
			int currentProgress = skbProgress.getMax()
					* mediaPlayer.getCurrentPosition()
					/ mediaPlayer.getDuration();
			Log.e(currentProgress + "% play", bufferingProgress + "% buffer");
		}

	}

}