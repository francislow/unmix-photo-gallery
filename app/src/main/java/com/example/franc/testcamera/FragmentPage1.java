package com.example.franc.testcamera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;

/**
 * Created by franc on 1/6/2019.
 */

public class FragmentPage1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Getting stored information from bundle (shared by all fragments)
        Bundle bundle = getArguments();
        String message = Integer.toString(bundle.getInt("count"));
        View view = inflater.inflate(R.layout.fragment_page1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Camera button
        Button button0 = (Button) getActivity().findViewById(R.id.mybutton1);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start camera
                ((ActivityMain)getActivity()).takePicture();
                //This activity will go on pause and camera will pop up
            }
        });
    }

    @Override
    public void onPause() {
        System.out.println("fragmentpage1 PAUSED");
        super.onPause();
    }

    @Override
    public void onResume() {
        System.out.println("fragmentpage1 RESUMED");
        super.onResume();
        //If picture was taken
        if (((ActivityMain)getActivity()).wasPictureTaken()) {
            System.out.println("checked picture taken or not");
            //Get the image file
            File currentImageFile = ((ActivityMain)getActivity()).getPicture();
            //Show picture as an image view in xml design
            String currentPhotoPath = currentImageFile.getAbsolutePath();
            File imgFile = new  File(currentPhotoPath);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //imageView0.setImageBitmap(myBitmap);
        }
        else {
            System.out.println("MY PICTURE WAS NOT TAKEN");
        }
    }
}
