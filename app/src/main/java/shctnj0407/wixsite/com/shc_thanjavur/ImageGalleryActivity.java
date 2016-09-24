package shctnj0407.wixsite.com.shc_thanjavur;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import shctnj0407.wixsite.com.shc_thanjavur.adapter.ImageAdapter;
import shctnj0407.wixsite.com.shc_thanjavur.model.Item;

public class ImageGalleryActivity extends AppCompatActivity {

    @BindView(R.id.gridView_gallery)
    GridView mGridViewGallery;

    private ImageAdapter mImageAdapter;
    private ArrayList<Item> mItemList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        ButterKnife.bind(this);
        mContext = ImageGalleryActivity.this;

        mItemList = new ArrayList<>();
        int []photos = {
                R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
                R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8,
                R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12,
                R.drawable.img_13, R.drawable.img_14, R.drawable.img_15, R.drawable.img_16,
                R.drawable.img_17
        };

        for(int pic : photos){
            Item item = new Item(pic);
            mItemList.add(item);
        }

        mImageAdapter = new ImageAdapter(mContext, mItemList);
        mGridViewGallery.setAdapter(mImageAdapter);
        mGridViewGallery.setOnItemClickListener(mOnItemClickListener);


    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Item item = mItemList.get(position);
            int resId = item.getImgDrawableSrc();
            Intent intent = new Intent(mContext, DisplayImageActivity.class);
            intent.putExtra(DisplayImageActivity.IMAGE_CLICKED_ID, resId);
            startActivity(intent);
        }
    };

}
