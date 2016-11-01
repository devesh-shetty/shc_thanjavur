package shctnj0407.wixsite.com.shc_thanjavur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import shctnj0407.wixsite.com.shc_thanjavur.adapter.ImageAdapter;
import shctnj0407.wixsite.com.shc_thanjavur.config.Config;
import shctnj0407.wixsite.com.shc_thanjavur.constant.Constants;
import shctnj0407.wixsite.com.shc_thanjavur.http.HttpHandler;
import shctnj0407.wixsite.com.shc_thanjavur.model.Item;

public class ImageGalleryActivity extends AppCompatActivity {

    @BindView(R.id.gridView_gallery)
    GridView mGridViewGallery;

    private String TAG = ImageGalleryActivity.class.getSimpleName();

    private ImageAdapter mImageAdapter;
    private ArrayList<Item> mItemList;
    private Context mContext;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);
        ButterKnife.bind(this);
        mContext = ImageGalleryActivity.this;

        mItemList = new ArrayList<>();

        mImageAdapter = new ImageAdapter(mContext, mItemList);
        mGridViewGallery.setAdapter(mImageAdapter);
        mGridViewGallery.setOnItemClickListener(mOnItemClickListener);

        new GetImages().execute();

    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Item item = mItemList.get(position);
            String imageUrl = item.getImgDrawableSrc();
            Intent intent = new Intent(mContext, DisplayImageActivity.class);
            intent.putExtra(DisplayImageActivity.IMAGE_CLICKED_ID, imageUrl);
            startActivity(intent);
        }
    };

    /*
    * Async task class to get json by making HTTP call
    */
    private class GetImages extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(Constants.BASE_URL+Config.GALLERY_WEB_SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    int totalCount = Integer.parseInt(jsonObj.getString(Constants.TOTAL_COUNT)) + 2;

                    // looping through All Images
                    for (int i = 2; i < totalCount; i++) {

                        String imageUrl = jsonObj.getString(i+"");

                        Item item = new Item(Constants.BASE_URL+Config.GALLERY_IMAGE_SUB_DIRECTORY+imageUrl);
                        mItemList.add(item);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Data error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get data from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get data from server. Check your internet connection",
                        Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            mImageAdapter.notifyDataSetChanged();
        }

    }


}
