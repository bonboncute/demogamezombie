package Effect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CacheDataLoader {
    private static CacheDataLoader instance;
    private String framfile = "Data/frame.txt";

    private Hashtable<String,FrameImage> framImage;
    // private Hashtable<String, Animation> animation;
    private CacheDataLoader(){
        
    }
    public static CacheDataLoader getInstance(){
        if(instance == null){
            instance = new CacheDataLoader();
        }
            return instance;
    }
     public void LoadFrame() throws IOException{
        framImage = new Hashtable<String,FrameImage>();

        FileReader fileReader = new FileReader(framfile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;

        if(bufferedReader.readLine() == null ){
            System.out.println("No data");
            throw new IOException();
        }
        else {
            fileReader = new FileReader(framfile);
            bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()).equals(""));
            int n = Integer.parseInt(line);

            for(int i = 0 ;i < n;i++){
                FrameImage frame = new FrameImage();
                while((line = bufferedReader.readLine()).equals(""));
                frame.setName(line);

                while((line = bufferedReader.readLine()).equals(""));
                String[] str = line.split(" ");
                String path = str[1];

                while((line = bufferedReader.readLine()).equals(""));
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);

                while((line = bufferedReader.readLine()).equals(""));
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);

                while((line = bufferedReader.readLine()).equals(""));
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);

                while((line = bufferedReader.readLine()).equals(""));
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);

                BufferedImage imageData = ImageIO.read(new File(path));
                BufferedImage image = imageData.getSubimage(x,y,w,h);
                frame.setImage(image);

                instance.framImage.put(frame.getName(),frame);
            }

        }
        bufferedReader.close();
     }
}
