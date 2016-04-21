package com.qcj.common.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * 自动全屏的VideoView
 */
public class FullScreenVideoView extends VideoView {

	private int videoWidth;
	private int videoHeight;

	public FullScreenVideoView(Context context) {
		super(context);
	}

	public FullScreenVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FullScreenVideoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// int width = getDefaultSize(480, widthMeasureSpec);
		// int height = getDefaultSize(300, heightMeasureSpec);
		// if (videoWidth > 0 && videoHeight > 0) {
		// if (videoWidth * height > width * videoHeight) {
		// height = width * videoHeight / videoWidth;
		// } else if (videoWidth * height < width * videoHeight) {
		// width = height * videoWidth / videoHeight;
		// }
		// }
		int videoWidth = UIUtils.getWindowWidth(getContext());
		int videoHeight = (int) (videoWidth * 0.57);
		setMeasuredDimension(videoWidth, videoHeight);
	}

	public int getVideoWidth() {
		return videoWidth;
	}

	public void setVideoWidth(int videoWidth) {
		this.videoWidth = videoWidth;
	}

	public int getVideoHeight() {
		return videoHeight;
	}

	public void setVideoHeight(int videoHeight) {
		this.videoHeight = videoHeight;
	}

}
