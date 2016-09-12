package shctnj0407.wixsite.com.shc_thanjavur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import shctnj0407.wixsite.com.shc_thanjavur.config.Config;

public class MainActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {


    private static final String TAG = MainActivity.class.getName() ;

    @BindView(R.id.pdfView)
    PDFView mPdfView;

    Integer pageNumber = 0;
    private String currentPdf = "DUMMY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_history, R.id.btn_mass_timings})
    public void viewHistory(View view){

        int id = view.getId();
        String pdf = null;

        if( id == R.id.btn_history){

            pdf = Config.PDF_CHURCH_HISTORY;

        }else if(id == R.id.btn_mass_timings){

            pdf = Config.PDF_MASS_TIMINGS;

        }

        if(currentPdf.equals(pdf)){
            return;
        }else{
            currentPdf = pdf;
        }

        mPdfView.recycle();
        
        mPdfView.fromAsset(pdf)
                .defaultPage(0)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = mPdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", "pdf", page + 1, pageCount));
    }
}
