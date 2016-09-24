package shctnj0407.wixsite.com.shc_thanjavur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

public class DisplayImageActivity extends AppCompatActivity {

    @BindView(R.id.iv_display)ImageView mImageView;

    public static final String IMAGE_CLICKED_ID = "IMAGE_CLICKED_ID";
    private PhotoViewAttacher mPhotoViewAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        int imageResource = intent.getIntExtra(IMAGE_CLICKED_ID, -1);

        if(imageResource != -1){
            mImageView.setImageResource(imageResource);
            mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        }


    }
}
