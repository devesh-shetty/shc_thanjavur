package shctnj0407.wixsite.com.shc_thanjavur.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import shctnj0407.wixsite.com.shc_thanjavur.R;
import shctnj0407.wixsite.com.shc_thanjavur.constant.Constants;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = MainActivity.this;
    }


    @OnClick({R.id.fab_church_history, R.id.fab_gallery, R.id.fab_mass_timing})
    public void onFABClick(View v){
        int id = v.getId();
        Intent intent = null;

        switch (id){
            case R.id.fab_church_history:
                intent = new Intent(mContext, PdfViewActivity.class);
                intent.putExtra(PdfViewActivity.PDF_NAME, Constants.PDF_CHURCH_HISTORY);
                break;

            case R.id.fab_mass_timing:
                intent = new Intent(mContext, PdfViewActivity.class);
                intent.putExtra(PdfViewActivity.PDF_NAME, Constants.PDF_MASS_TIMINGS);
                break;

            case R.id.fab_gallery:
                intent = new Intent(mContext, ImageListActivity.class);
                break;
        }

        if(intent != null){
            startActivity(intent);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        Intent intent = null;

        switch (id){
            case R.id.menu_church_history:
                intent = new Intent(mContext, PdfViewActivity.class);
                intent.putExtra(PdfViewActivity.PDF_NAME, Constants.PDF_CHURCH_HISTORY);
                break;

            case R.id.menu_church_timing:
                intent = new Intent(mContext, PdfViewActivity.class);
                intent.putExtra(PdfViewActivity.PDF_NAME, Constants.PDF_MASS_TIMINGS);
                break;

            case R.id.menu_image_gallery:
                intent = new Intent(mContext, ImageListActivity.class);
                break;
        }

        if(intent != null){
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
