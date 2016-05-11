package com.casper.thetmb.thetmb;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

/**
 * Created by casper on 09.05.16.
 */
public class ProfileUserFragment extends Fragment {
    public static final int RESULT_LOAD_IMAGE = 101;
    private static final String TAG = "ProfileUserFragment_";
    private static Bitmap bitmapProfilePhoto;
    private View btnAddPhoto;
    private ImageView profilePhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_user_fr, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        btnAddPhoto = view.findViewById(R.id.btn_add_photo);
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        animateBtnAddPhoto(view);
        profilePhoto = (ImageView) view.findViewById(R.id.profile_photo);
        if (bitmapProfilePhoto != null) {
            profilePhoto.setImageBitmap(bitmapProfilePhoto);
        }
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    private void animateBtnAddPhoto(View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnAddPhoto.setVisibility(View.VISIBLE);
                ObjectAnimator scaleBtnAddPhoto = ObjectAnimator.ofPropertyValuesHolder(btnAddPhoto,
                        PropertyValuesHolder.ofFloat("scaleX", 0f, 1f),
                        PropertyValuesHolder.ofFloat("scaleY", 0f, 1f));
                scaleBtnAddPhoto.setInterpolator(new BounceInterpolator());
                scaleBtnAddPhoto.setDuration(300);
                scaleBtnAddPhoto.start();
            }
        }, 500);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult requestCode: " + requestCode + " resultCode: " + resultCode + " data: " + data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
            Log.d(TAG, "onActivityResult true");
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            try {
                bitmapProfilePhoto = BitmapFactory.decodeFile(picturePath);
            } catch (Exception e) {
                Log.d(TAG, "onActivityResult: " + e);
            }
        }
    }
}
