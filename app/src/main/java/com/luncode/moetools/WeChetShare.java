package com.luncode.moetools;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WeChetShare extends AppCompatActivity{
    private ImageView imageView;
    private EditText editText;
    private IWXAPI iwxapi;
    private Button shareh;
    private Button shareq;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);
        setTitle("微信贺卡分享");
        iwxapi = WXAPIFactory.createWXAPI(this,"wx46332be31472ef40");
        iwxapi.registerApp("wx46332be31472ef40");

        imageView = (ImageView) findViewById(R.id.image1);
        shareh = (Button) findViewById(R.id.buttonshareh);
        editText = (EditText) findViewById(R.id.edittext);
        shareq = (Button) findViewById(R.id.buttonshareq);
        editText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/test.ttf"));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,100);
            }
        });
        shareh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wecharshare(0);
                shareh.setVisibility(View.VISIBLE);
                shareq.setVisibility(View.VISIBLE);

            }
        });
        shareq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wecharshare(1);
                shareh.setVisibility(View.VISIBLE);
                shareq.setVisibility(View.VISIBLE);

            }
        });

    }

    private void wecharshare(int flag){
        WXWebpageObject webpage = new WXWebpageObject();
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.mediaObject = new WXImageObject(SpringCard());
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag ==0?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        iwxapi.sendReq(req);
    }

    private Bitmap SpringCard(){
        shareh.setVisibility(View.INVISIBLE);
        shareq.setVisibility(View.INVISIBLE);
        View view = getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==100){
            if(data!=null){
                imageView.setImageURI(data.getData());
            }
        }
    }

}
