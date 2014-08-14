package com.example.imageviewanim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;
/**
 * ʵ�ֿ���չʾ��֡ͼƬ��imageview�ؼ�
 * @author ��˶
 * @time 20140814 19:35
 * 
 */
public class AnimImageView extends ImageView{
	/**����---�����߳�**/
	private AnimThread mThread;
	/**��ֵ---��Ҫչʾ��ͼƬid**/
	private int niFame;
	
	private Paint mPaint;
	private Context mContext;
	private int[] arr_Bmp;
	public AnimImageView(Context context,int[] arr_bmp) {
		super(context);
		mContext = context;
		arr_Bmp = arr_bmp;
		Init();
		mThread = new AnimThread();
	}

	/*******************
	 * ���������ݵĳ�ʼ��
	 ******************/
	private void Init(){
		mPaint = new Paint();
		//�����
		mPaint.setAntiAlias(true);
	}
	
	/****************************************
	 * ����������id��ȡ��Դ�ļ��������bitmap����������id
	 * 	        ���ɵ�bitmapΪ�գ�����Ĭ�ϵ�ͼƬ��������ֿ�ָ�����
	 * @param id
	 * @return bitmap
	 ****************************************/
	private Bitmap getBitmapFromAsset(int id){
		Bitmap mBmp = BitmapFactory.decodeResource(mContext.getResources(), id);
		Bitmap mDefault = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
		if(mBmp != null){
			return mBmp;
		}
		return mDefault;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(getBitmapFromAsset(arr_Bmp[niFame]), 0, 0, mPaint);
	}
	
	
	public void startAnim(){
		mThread.isRun = true;
		mThread.start();
	}
	
	public void stopAnim(){
		mThread.isRun = false;
	}
	/**
	 *	�ı���֡ͼƬID���߳� 
	 * @author ��˶
	 * @time 20140814 19:37
	 */
	class AnimThread extends Thread{
		public boolean isRun;
		@Override
		public void run() {
			while(isRun){
				try {
					niFame++;
					if(niFame % (arr_Bmp.length-1) == 0){
						niFame = 0;
					}
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				postInvalidate();
			}
		}
		
	}
}
