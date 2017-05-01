package com.smartd90eventscamera;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    public  SettingFragment mSettingFragment = new SettingFragment();
    public  CameraFragment mCameraFragment = new CameraFragment();
    public  PhotoFragment mPhotoFragment = new PhotoFragment();
    private FtpItem mFtpitem = new FtpItem(
            "192.168.110.1",
            "1212",
            "dps",
            "dps",
            "10x15 Portrait"  );
    private  static SharedPreferences setup;
    private static final int REQUEST_ACCESS_WIFI_STATE_PERMISSION = 1;
    private static final int REQUEST_CHANGE_WIFI_STATE_PERMISSION = 1;
    private static final int REQUEST_ACCESS_COARSE_LOCATION_PERMISSION = 1;
    private static final int REQUEST_ACCESS_NETWORK_STATE_PERMISSION = 1;
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_MANAGE_DOCUMENTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setup = getSharedPreferences("config_pref", MODE_PRIVATE);
        mFtpitem.setFtphost(setup.getString("FTPHOST","192.168.110.1"));
        mFtpitem.setFtpport(setup.getString("FTPPORT","1212"));
        mFtpitem.setFtpuser(setup.getString("FTPUSER","dps"));
        mFtpitem.setFtppass(setup.getString("FTPPASS","dps"));
        mFtpitem.setHotfolder(setup.getString("HOTFOLDER","Polaroid"));

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_NETWORK_STATE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                        REQUEST_ACCESS_NETWORK_STATE_PERMISSION );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_WIFI_STATE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_WIFI_STATE},
                        REQUEST_ACCESS_WIFI_STATE_PERMISSION );

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CHANGE_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CHANGE_WIFI_STATE)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CHANGE_WIFI_STATE},
                        REQUEST_CHANGE_WIFI_STATE_PERMISSION );
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_ACCESS_COARSE_LOCATION_PERMISSION );
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_READ_EXTERNAL_STORAGE);
            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.MANAGE_DOCUMENTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.MANAGE_DOCUMENTS)) {
                // Explain to the user why we need to read the contacts
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.MANAGE_DOCUMENTS},
                    REQUEST_MANAGE_DOCUMENTS);
            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mSettingFragment.isVisible()  && !mSettingFragment.isRemoving()) {
                camera();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left)
                        .remove(mSettingFragment).commit();
            }
            else if (mPhotoFragment.isVisible() && !mPhotoFragment.isRemoving())
            {
                getFragmentManager().beginTransaction()
                        .remove(mPhotoFragment).commit();
                camera();
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.exitapp))
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void savesettings( FtpItem ftpitem)
    {
        SharedPreferences.Editor editasetup = setup.edit();
        editasetup.putString("FTPHOST", ftpitem.getFtphost());
        editasetup.putString("FTPPORT", ftpitem.getFtpport());
        editasetup.putString("FTPUSER", ftpitem.getFtpuser());
        editasetup.putString("FTPPASS", ftpitem.getFtppass());
        editasetup.putString("HOTFOLDERPOLAROID", ftpitem.getHotfolder());
        editasetup.commit();
        this.mFtpitem = ftpitem;
    }

    public void photodone(File mImageFile)
    {
        getFragmentManager().beginTransaction()
                .remove(mCameraFragment).commit();
        mPhotoFragment = new PhotoFragment();
        Bundle argsPhotoFragment = new Bundle();
        argsPhotoFragment.putString("FTPHOST", mFtpitem.getFtphost());
        argsPhotoFragment.putString("FTPPORT", mFtpitem.getFtpport());
        argsPhotoFragment.putString("FTPUSER", mFtpitem.getFtpuser());
        argsPhotoFragment.putString("FTPPASS", mFtpitem.getFtppass());
        argsPhotoFragment.putString("HOTFOLDER", mFtpitem.getHotfolder());
        argsPhotoFragment.putString("PHOTOFILE", mImageFile.getAbsolutePath());
        mPhotoFragment.setArguments(argsPhotoFragment);;
        getFragmentManager().beginTransaction()
                .replace(R.id.photocontainer, mPhotoFragment)
                .show(mPhotoFragment).commit();
    }

    public void camera()
    {
        if (mPhotoFragment.isVisible() && !mPhotoFragment.isRemoving())
            getFragmentManager().beginTransaction()
                    .remove(mPhotoFragment).commit();
        getFragmentManager().beginTransaction()
                .replace(R.id.cameracontainer, mCameraFragment)
                .show(mCameraFragment).commit();
    }

    public void settings()
    {
        getFragmentManager().beginTransaction()
                .remove(mCameraFragment).commit();
        mSettingFragment = new SettingFragment();
        Bundle argsSettingFragment = new Bundle();
        argsSettingFragment.putString("FTPHOST", mFtpitem.getFtphost());
        argsSettingFragment.putString("FTPPORT", mFtpitem.getFtpport());
        argsSettingFragment.putString("FTPUSER", mFtpitem.getFtpuser());
        argsSettingFragment.putString("FTPPASS", mFtpitem.getFtppass());
        argsSettingFragment.putString("HOTFOLDER", mFtpitem.getHotfolder());
        mSettingFragment.setArguments(argsSettingFragment);;
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left)
                .replace(R.id.settingscontainer, mSettingFragment)
                .show(mSettingFragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getFragmentManager().beginTransaction()
                .remove(mCameraFragment).commit();
    }

}
