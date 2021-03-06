package shctnj0407.wixsite.com.shc_thanjavur.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shctnj0407.wixsite.com.shc_thanjavur.R;
import shctnj0407.wixsite.com.shc_thanjavur.custom.SquareImageView;
import shctnj0407.wixsite.com.shc_thanjavur.model.Item;

/**
 * The ImageAdapter that will supply the data source to the gridView
 * @author Devesh Shetty
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Item> mDataSource;
    private LayoutInflater mLayoutInflater;

    public ImageAdapter(Context mContext, List<Item> mDataSource) {
        this.mContext = mContext;
        this.mDataSource = mDataSource;
        mLayoutInflater = ((Activity)mContext).getLayoutInflater();

    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Item getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.image_item, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);


        //load the imageView with the bitmap
//        Picasso.with(mContext)
//                .load(item.getImgDrawableSrc())
//                .resize(Config.IMAGE_ITEM_SIZE, Config.IMAGE_ITEM_SIZE)
//                .centerInside()
//                .placeholder(R.drawable.progress_animation)
//                .error(R.drawable.error)
//                .into(target);

        Glide.with(mContext)
                .load(item.getImgDrawableSrc())
                .centerCrop()
                .error(R.drawable.error)
                .placeholder(R.drawable.progress_animation)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivItem);

        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.iv_item_image)SquareImageView ivItem;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }


    }



}