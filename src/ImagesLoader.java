import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by рома on 22.10.2016.
 */
public class ImagesLoader  implements Observer{

    private String[] urls;
    private int countImages = 0;

    public ImagesLoader(String[] urls) {this.urls = urls;}

    public void update (Observable obj, Object arg){
        System.out.println("Загружена картинка: " + arg);
        countImages++;
        if (countImages == urls.length){
             System.out.println("Загрузка картинок завершена!" );
        }
    }

    public void loadImages() {
        String folder = "d:/";
        String extension = ".jpg";
        for (int i = 0; i < urls.length; i++) {

            class ImageLoader extends Observable implements Runnable {

                String urlS;
                String fileName;

                ImageLoader(String urlS, String fileName, ImagesLoader imagesLoader) {
                    this.addObserver(imagesLoader);
                    this.urlS = urlS;
                    this.fileName = fileName;
                    new Thread(this).start();
                }

                private void LoadImage(String urlS, String fileName) {

                    try {
                        URL url = new URL(urlS);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.connect();
                        InputStream inputStream = urlConnection.getInputStream();

                        byte[] buffer = new byte[1024];
                        int bufferLength = 0;

                        File file = new File(folder + fileName + extension);
                        FileOutputStream fos = new FileOutputStream(file);

                        while ((bufferLength = inputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, bufferLength);
                        }

                        fos.close();
                        inputStream.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                public void run(){
                   LoadImage(urlS,fileName);
                    setChanged();
                    notifyObservers(folder + fileName + extension);
                }
            }
            ImageLoader imageLoader = new ImageLoader(urls[i],"File" + i, this);
        }
    }
}
