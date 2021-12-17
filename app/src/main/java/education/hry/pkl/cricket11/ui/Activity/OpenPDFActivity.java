package education.hry.pkl.cricket11.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.databinding.DataBindingUtil;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.databinding.ActivityOpenPdfactivityBinding;
import education.hry.pkl.cricket11.utility.BaseActivity;
import education.hry.pkl.cricket11.utility.CSPreferences;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class OpenPDFActivity extends BaseActivity implements DownloadFile.Listener {

    private RemotePDFViewPager remotePDFViewPager;

    private PDFPagerAdapter pdfPagerAdapter;

    private String url;

    private ProgressBar progressBar;

    private LinearLayout pdfLayout;

    ActivityOpenPdfactivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_open_pdfactivity);

        try {

            Bundle extras = getIntent().getExtras();

            if (extras != null) {
                String result = extras.getString("title");
                url = extras.getString("Url");

                binding.toolbar.tvToolbarTitle.setText(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //initialize the pdfLayout
        pdfLayout = findViewById(R.id.pdf_layout);

        //Create a RemotePDFViewPager object
        remotePDFViewPager = new RemotePDFViewPager(this, url, this);




    }


    @Override
    public void onSuccess(String url, String destinationPath) {




        // That's the positive case. PDF Download went fine
        pdfPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));


        remotePDFViewPager.setAdapter(pdfPagerAdapter);
        updateLayout();
        progressBar.setVisibility(View.GONE);
    }

    private void updateLayout() {

        pdfLayout.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onFailure(Exception e) {
        // This will be called if download fails
    }

    @Override
    public void onProgressUpdate(int progress, int total) {
        // You will get download progress here
        // Always on UI Thread so feel free to update your views here
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
        }
    }

    @Override
    public void initData() {
        binding.toolbar.tvToolbarTitle.setText(" Match Score Card");


    }

    @Override
    public void initListeners() {

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


}