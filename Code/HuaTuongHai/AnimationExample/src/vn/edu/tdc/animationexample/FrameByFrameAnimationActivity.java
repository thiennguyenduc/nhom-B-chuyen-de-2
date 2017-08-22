package vn.edu.tdc.animationexample;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class FrameByFrameAnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_frame_by_frame_animation);
        ImageView img = (ImageView) findViewById(R.id.image);
        img.setVisibility(ImageView.VISIBLE);
        img.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        if(frameAnimation !=null){
        	frameAnimation.start();
        }
    }
}
