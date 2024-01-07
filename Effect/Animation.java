package Effect;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private String name;
    private boolean isRepeated;
    private ArrayList<FrameImage> frameImage;
    private int cFrame;
    private ArrayList <Boolean> ignoreFrame;
    private ArrayList <Double> delayFrame;
    private long beginTime;
    private boolean DrawRectangleOfFrame;

    
    public Animation(){
        delayFrame = new ArrayList<Double>();
        beginTime = 0;
        cFrame = 0;
        ignoreFrame = new ArrayList<Boolean>();
        frameImage = new ArrayList<FrameImage>();
        DrawRectangleOfFrame = false;
        isRepeated = true;
    }
    public Animation(Animation animation){
        beginTime = animation.beginTime;
        cFrame = animation.cFrame;
        DrawRectangleOfFrame = animation.DrawRectangleOfFrame;
        isRepeated = animation.isRepeated;

        delayFrame = new ArrayList<Double>();
        for (Double d : animation.delayFrame){
            delayFrame.add(d);
        }
        ignoreFrame = new ArrayList<Boolean>();
        for ( boolean b : animation.ignoreFrame){
            ignoreFrame.add(b);
        }
        frameImage = new ArrayList<FrameImage>();
        for ( FrameImage f : animation.frameImage){
            frameImage.add(new FrameImage(f));
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsRepeated() {
        return isRepeated;
    }
    public void setIsRepeated(boolean isRepeated) {
        this.isRepeated = isRepeated;
    }
    public ArrayList<FrameImage> getFrameImage() {
        return frameImage;
    }
    public void setFrameImage(ArrayList<FrameImage> frameImage) {
        this.frameImage = frameImage;
    }
    public int getcFrame() {
        return cFrame;
    }
    public void setcFrame(int cFrame) {
        if(cFrame >= 0 && cFrame < frameImage.size())
        this.cFrame = cFrame;
        else this.cFrame = 0;
    }
    public ArrayList<Boolean> getIgnoreFrame() {
        return ignoreFrame;
    }
    public void setIgnoreFrame(ArrayList<Boolean> ignoreFrame) {
        this.ignoreFrame = ignoreFrame;
}
    public ArrayList<Double> getDelayFrame() {
        return delayFrame;
    }
    public void setDelayFrame(ArrayList<Double> delayFrame) {
        this.delayFrame = delayFrame;
    }
    public long getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }
    public boolean getDrawRectangleOfFrame() {
        return DrawRectangleOfFrame;
    }
    public void setDrawRectangleOfFrame(boolean drawRectangleOfFrame) {
        this.DrawRectangleOfFrame = drawRectangleOfFrame;
    }        
    public boolean isIgnoreFrame(int id){
        return ignoreFrame.get(id);
    }
    public void setIgnoreFrame(int id){
        if(id >= 0 && id <ignoreFrame.size()){
            ignoreFrame.set(id, true);
        }
    }
    public void unIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrame.size()){
            ignoreFrame.set(id, false);
        }
    }
    public void reset(){
        cFrame = 0;
        beginTime = 0;

        for(int i = 0; i < ignoreFrame.size();i++){
            ignoreFrame.set(i, false);
        }
    }
    public void add(FrameImage frameImage1, double timeToNextFrame){
        ignoreFrame.add(false);
        frameImage.add(frameImage1);
        delayFrame.add(Double.valueOf(timeToNextFrame));
    }
    public BufferedImage getCurrentImage(){
        return frameImage.get(cFrame).getImage();
    }
    public void Update(long currentTime){
        if(beginTime == 0) beginTime = currentTime;
        else{
            if (currentTime - beginTime > delayFrame.get(cFrame)){
                nextFrame();
                beginTime = currentTime;
            }
        }
    }
    public boolean isLastFrame(){
        if(cFrame == frameImage.size() - 1){
        return true;
        }
        else {
        return false;
        }
    }
    private void nextFrame(){
        if (cFrame >= frameImage.size() - 1){
            if(isRepeated) cFrame = 0;
        }
        else cFrame++;
        if(ignoreFrame.get(cFrame)) nextFrame(); 
            
        }
    public void flipAllImage(){
        for (int i = 0;i < frameImage.size();i++){
            BufferedImage image = frameImage.get(i).getImage();
            AffineTransform tx = AffineTransform.getScaleInstance(-1,1);
            tx.translate(-image.getWidth(), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            frameImage.get(i).setImage(image);
        }
    }
    public void draw(int x,int y, Graphics2D g2){
        BufferedImage image = getCurrentImage();
        g2.drawImage(image,x - image.getWidth()/2 ,y - image.getHeight()/2,null );
        if(DrawRectangleOfFrame)
            g2.drawRect(x-image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
    }

}

    

