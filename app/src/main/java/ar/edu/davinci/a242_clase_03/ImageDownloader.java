package ar.edu.davinci.a242_clase_03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
    private ImageView imageView;

    public ImageDownloader(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... URLs) {
        String URL_query = URLs[0];
        try {
            URL request = new URL(URL_query);
            InputStream content = (InputStream) request.getContent();
            return BitmapFactory.decodeStream(content);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(Bitmap imageDownloaded) {
        super.onPostExecute(imageDownloaded);
        if(imageDownloaded == null) return;
        // acá actualizo
        this.imageView.setImageBitmap(imageDownloaded);
    }
}
