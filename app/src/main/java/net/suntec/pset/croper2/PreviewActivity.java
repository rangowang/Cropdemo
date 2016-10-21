package net.suntec.pset.croper2;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PreviewActivity extends AppCompatActivity {

    private ImageView im;
    private RelativeLayout root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        root = (RelativeLayout) findViewById(R.id.activity_preview);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        Bitmap bp = getIntent().getParcelableExtra("Bitmap");
        Bitmap bp = CroperImage.getBp();
        im = (ImageView) findViewById(R.id.preview_wallopaper);
        im.setImageBitmap(bp);
        im.setScaleType(ImageView.ScaleType.CENTER);
    }
}
