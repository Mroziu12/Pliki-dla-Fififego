package com.example.dzik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.concurrent.BlockingDeque;

public class AcReport extends AppCompatActivity {
    private static final String TAG = "Mroziu_acReport";
    private static final int PICK_IMAGE = 100;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView1Photo;
    private ImageView imageView2Photo;
    private ImageView imageView3Photo;
    EditText editTextAdult;
    EditText editTextChlid;
    int nrZdj;
    Uri[] tabUri = new Uri[3];
    Bitmap [] tabBitmap = new Bitmap[3];
    RadioGroup radioGroupLiveDead;
    RadioButton radioButtonLive;
    RadioButton radioButtonDead;
    Dialog mDialog;
    ImageView imageViewFromGallery;
    ImageView imageViewFromCamera;


    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2 ;
    RadioButton radioButton3 ;
    RadioButton radioButton4 ;
    RadioButton radioButton5;
    RadioButton radioButton6 ;
    EditText editText ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_report);
        Button btnZglos=findViewById(R.id.btn_report);
        Log.d(TAG, "onCreate: Czy to w ogole?");

        radioGroupLiveDead=(RadioGroup) findViewById(R.id.live_dead_button_view) ;
        radioButtonLive = (RadioButton) findViewById(R.id.radio_live);
        radioButtonDead = (RadioButton) findViewById(R.id.radio_dead);
        imageView1 = (ImageView) findViewById(R.id.image_view1);
        imageView2 = (ImageView) findViewById(R.id.image_view2);
        imageView3 = (ImageView) findViewById(R.id.image_view3);
        editTextAdult=(EditText) findViewById(R.id.et_adult);
        editTextChlid=(EditText) findViewById(R.id.et_child);
        //editTextAdult.setText(0);
        //editTextChlid.setText(0);
/*        NumberPicker numberPickerAdult= findViewById(R.id.np_adult);
        numberPickerAdult.setMaxValue(0);
        numberPickerAdult.setMaxValue(99);
        numberPickerAdult.get*/
/*        radioGroup = (RadioGroup)findViewById(R.id.adult_button_view);
        radioGroup.clearCheck();*/
        btnZglos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int czyPoprawne = sprawdzPoprawnosc();
                if(czyPoprawne==0){
                    //Wszytko dobrze przejdz dalej
                }else if(czyPoprawne==1){
                    //Zerowa liczba dzików
                }else if(czyPoprawne==2){
                    //brak zdj dla padliny
                }
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nrZdj=0;
                Log.d(TAG, "onClick: asekuracyjnie");
                pokazDialogPhoto();

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nrZdj=1;
                pokazDialogPhoto();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                nrZdj=2;
                pokazDialogPhoto();
            }
        });

    }

    private void pokazDialogPhoto() {
        mDialog = new Dialog(this);
        LayoutInflater li = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.dialog_add_photo_second, null, false);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);

            //mDialog.setContentView(R.layout.dialog_add_photo_second);

        
        Log.d(TAG, "pokazDialogPhoto: Po dodaniu xmla");

        Button btnReturn = (Button) mDialog.findViewById(R.id.btn_return_wybor_zdj);
           imageViewFromGallery = (ImageView) mDialog.findViewById(R.id.iv_from_gallery);
        imageViewFromCamera = (ImageView) mDialog.findViewById(R.id.iv_from_camera);
        Log.d(TAG, "pokazDialogPhoto: po przypisanach");

        imageViewFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        imageViewFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                mDialog.dismiss();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.cancel();
            }
        });

        Log.d(TAG, "pokazDialogPhoto: przed getwindow");
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Log.d(TAG, "PokazDialog: przed show");
        Log.d(TAG, "PokazDialog: pokazuje dialog:" + mDialog);
        mDialog.show();
    }


    private void openGallery() {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
        mDialog.dismiss();
    }

    private int sprawdzPoprawnosc(){

        if(editTextAdult.getText().toString().equals(0)&&editTextChlid.getText().toString().equals(0)) return 1;
        if(editTextAdult.getText().toString().equals(0)&&editTextChlid.getText().toString()==null) return 1;
        if(editTextAdult.getText().toString()==null&&editTextChlid.getText().toString().equals(0)) return 1;
        if(editTextAdult.getText().toString()==null&&editTextChlid.getText().toString()==null) return 1;
        if (radioButtonLive.isChecked()==true){
            //Żywe

        }else{
            //Padlina
            if (tabBitmap[0]==null&&tabUri[0]==null) return 2;
        }
        return 0;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            //------------------TUTAJ MASZ SWOJE URI--------------------
            Uri imageUri = data.getData();

            //---------------------------------------------------------
            switch (nrZdj){
                case 0:
                    imageView1.setImageURI(imageUri);
                    tabUri[nrZdj]= imageUri;
                    imageView2.setVisibility(View.VISIBLE);
                    imageView2.setClickable(true);

                    break;
                case 1:
                    imageView2.setImageURI(imageUri);
                    tabUri[nrZdj]= imageUri;
                    imageView3.setVisibility(View.VISIBLE);
                    imageView3.setClickable(true);
                    break;
                case 2:
                    imageView3.setImageURI(imageUri);
                    tabUri[nrZdj]= imageUri;
                    break;
            }

        }

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Uri imageUri = data.getData();
            Log.d(TAG, "onActivityResult: to jest uri:"+imageUri);
            switch (nrZdj){
                case 0:
                    tabBitmap[0]=(Bitmap) data.getExtras().get("data");
                    imageView1.setImageBitmap(tabBitmap[0]);
                    imageView2.setVisibility(View.VISIBLE);
                    imageView2.setClickable(true);

                    break;
                case 1:
                    tabBitmap[1]=(Bitmap) data.getExtras().get("data");
                    imageView2.setImageBitmap(tabBitmap[1]);
                    imageView3.setVisibility(View.VISIBLE);
                    imageView3.setClickable(true);
                    break;
                case 2:
                    tabBitmap[2]=(Bitmap) data.getExtras().get("data");
                    imageView3.setImageBitmap(tabBitmap[2]);
                    break;
            }

        }
    }
}