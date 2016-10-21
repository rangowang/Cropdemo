package net.suntec.pset.croper2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView im;
    private Bitmap result;
    private Button ok;

    // 正方形的剪裁框坐标，及长度。
    private int positionX;
    private int positionY;
    private int mLength;
    private boolean isSizeChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a00000);
        final ZoomImageView zoom = (ZoomImageView) findViewById(R.id.zoom_image_view);
        zoom.setImageBitmap(bitmap);

        ok = (Button) findViewById(R.id.image_details_btn);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(!isSizeChange) {
                        int scale = (int) getResources().getDisplayMetrics().density;  //屏幕像素密度
                        mLength = 200 * scale;
                        positionX = (int) (zoom.getWidth() - mLength) / 2;
                        positionY = (int) (zoom.getHeight() - mLength) / 2;
                    }

                    final Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, zoom.getWidth(),
                            zoom.getHeight(), true);
                    result = Bitmap.createBitmap(scaledBitmap, positionX, positionY, mLength, mLength);
                    Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
//                    intent.putExtra("Bitmap", result);
                    CroperImage.setBp(result);
                    startActivity(intent);
//                    im.setImageBitmap(result);
//                    scaledBitmap.recycle();
                }
                catch(Exception e){
                    Log.d("MainActivity", "create Bitmap 超出边界");
                }
            }
        });

        ChooseBorderView chooseBorderView = (ChooseBorderView) findViewById(R.id.choose_border_view);
        chooseBorderView.setonImageDetailsSizeChangged(new ChooseBorderView.onImageDetailsSizeChangged() {
            @Override
            public void onBorderSizeChangged(int x, int y, int length) {
                Log.d("MainActivity", "bitmap长度：" + bitmap.getHeight() + ", 宽度: " + bitmap.getWidth());
                Log.d("MainActivity", "ZoomImage长度：" + zoom.getHeight() + ", 宽度: " + zoom.getWidth());
                Log.d("MainActivity", "Border左上角x: " + x + ", y: " + y + "。长度: " + length +
                        "*************************************************************/n");
                //从图中截取BorderView部分。
                isSizeChange = true;
                positionX = x;
                positionY = y;
                mLength = length;
            }
        });
    }
}
