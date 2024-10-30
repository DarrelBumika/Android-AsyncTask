package com.example.praktikum_10;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.net.URL;

public class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

    private ProgressDialog progressDialog;
    private Context contex;

    public DownloadFilesTask(Context context) {
        this.contex = context;
    }

    @Override
    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;

        for (int i = 0; i < count; i++) {
            totalSize += Downloader.downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));

            // Stop if task is cancelled
            if (isCancelled()) break;
        }
        return totalSize;
    }

    @Override
    protected void onPostExecute(Long result) {
        Toast.makeText(contex, "Downloaded " + result + " bytes", Toast.LENGTH_SHORT).show();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(contex);
        progressDialog.setMessage("Downloading files...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.show();
    }

    static class Downloader {
        static long downloadFile(URL url) {
            try {
                // Simulate download delay
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Simulate downloaded file size
            return 1024 * 100;
        }
    }
}
