package shctnj0407.wixsite.com.shc_thanjavur.activity;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import shctnj0407.wixsite.com.shc_thanjavur.R;
import shctnj0407.wixsite.com.shc_thanjavur.adapter.GalleryAdapter;
import shctnj0407.wixsite.com.shc_thanjavur.app.AppController;
import shctnj0407.wixsite.com.shc_thanjavur.config.Config;
import shctnj0407.wixsite.com.shc_thanjavur.constant.Constants;
import shctnj0407.wixsite.com.shc_thanjavur.model.Item;

public class ImageListActivity extends AppCompatActivity {

    private String TAG = ImageListActivity.class.getSimpleName();

    private static final String endpoint = Constants.BASE_URL+ Config.GALLERY_WEB_SERVICE_URL;
    private ArrayList<Item> mImages;
    private ProgressDialog mProgressDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_images);

        mProgressDialog = new ProgressDialog(this);
        mImages = new ArrayList<>();
        mAdapter = new GalleryAdapter(mImages, getApplicationContext());

        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), mRecyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", mImages);
                bundle.putInt("position", position);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        fetchImages();
    }

    private void fetchImages() {
        mProgressDialog.setMessage("Fetching Images ...");
        mProgressDialog.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                endpoint, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    mImages.clear();
                    int totalCount = Integer.parseInt(response.getString(Constants.TOTAL_COUNT)) + 2;

                    // looping through All Images
                    for (int i = 2; i < totalCount; i++) {

                        String imageUrl = response.getString(i+"");

                        Item item = new Item(Constants.BASE_URL+Config.GALLERY_IMAGE_SUB_DIRECTORY+imageUrl);
                        mImages.add(item);

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
                mAdapter.notifyDataSetChanged();
                mProgressDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                mProgressDialog.hide();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }
}
