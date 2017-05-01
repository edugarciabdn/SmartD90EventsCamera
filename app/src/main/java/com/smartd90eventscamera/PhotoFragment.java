package com.smartd90eventscamera;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

public class PhotoFragment extends Fragment {

    public TouchImageView mPhoto;
    public ImageView mPrint, cancel;
    public ProgressBar mProgress;
    private Bitmap mBitmap;
    private FtpItem mFtpitem = new FtpItem(
            "192.168.110.1",
            "1212",
            "dps",
            "dps",
            "10x15 Portrait"  );
    private String mFilePath = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        mFtpitem.setFtphost(data.getString("FTPHOST"));
        mFtpitem.setFtpport(data.getString("FTPPORT"));
        mFtpitem.setFtpuser(data.getString("FTPUSER"));
        mFtpitem.setFtppass(data.getString("FTPPASS"));
        mFtpitem.setHotfolder(data.getString("HOTFOLDER"));
        mFilePath = data.getString("PHOTOFILE");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPhoto = (TouchImageView)view.findViewById(R.id.photo);
        mPhoto.setVisibility(View.GONE);
        mProgress = (ProgressBar)view.findViewById(R.id.progress) ;
        mPrint = (ImageView)view.findViewById(R.id.print) ;
        mPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mFilePath.equals(""))
                    new FtpFileTransfer(getActivity(), new PrintParam(
                            mFtpitem.getFtphost(),
                            mFtpitem.getFtpport(),
                            mFtpitem.getFtpuser(),
                            mFtpitem.getFtppass(),
                            mFilePath,
                            mFtpitem.getHotfolder()));
                else
                    Toast.makeText(getActivity(), getString(R.string.noimage),
                            Toast.LENGTH_SHORT).show();
            }
        });
        cancel = (ImageView)view.findViewById(R.id.cancel) ;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).camera();
            }
        });
        mPhoto.setImageResource(android.R.color.transparent);
        mPhoto.setVisibility(View.VISIBLE);
        Glide
                .with(getActivity())
                .load(mFilePath)
                .asBitmap()
                .toBytes(Bitmap.CompressFormat.PNG, 100)
                .atMost()
                .override(2048, 2048)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(new SimpleTarget<byte[]>() {
                    @Override public void onLoadStarted(Drawable ignore) {
                        mPrint.setVisibility(View.GONE);
                        cancel.setVisibility(View.GONE);
                        mProgress.setVisibility(View.VISIBLE);
                    }
                    @Override public void onResourceReady(byte[] resource, GlideAnimation<? super byte[]> ignore) {
                        mBitmap = BitmapFactory.decodeByteArray(resource, 0, resource.length);
                        mProgress.setVisibility(View.GONE);
                        mPhoto.setImageBitmap(mBitmap);
                        mPhoto.setMaxZoom(4f);
                        mPrint.setVisibility(View.VISIBLE);
                        cancel.setVisibility(View.VISIBLE);
                    }
                    @Override public void onLoadFailed(Exception ex, Drawable ignore) {
                    }
                }) ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
