package shctnj0407.wixsite.com.shc_thanjavur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DisplayImageActivity extends AppCompatActivity {

    @BindView(R.id.iv_display)ImageView mImageView;

    public static final String IMAGE_CLICKED_ID = "IMAGE_CLICKED_ID";
    private PhotoViewAttacher mPhotoViewAttacher;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = DisplayImageActivity.this;

        Intent intent = getIntent();
        String imageResource = intent.getStringExtra(IMAGE_CLICKED_ID);

        if(imageResource != null){

            Glide.with(mContext)
                    .load(imageResource)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mImageView);

            mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        }
        else {
            mImageView.setImageResource(R.drawable.error);
        }


    }
}
