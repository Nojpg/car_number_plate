package pirate.nojpg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.objdetect.CascadeClassifier;

public class TestConverter {
    private String path = "/home/sovereign/IdeaProjects/carnumber/src/main/resources/test.jpg";

    @Before
    public void before() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void testImgToInputStream() throws Exception {
        Mat openedImage = Detector.openImage(path);
        Mat readStream = Detector.readInputStreamIntoMat(path);
        Assert.assertEquals(openedImage.cols(), readStream.cols());
        Assert.assertEquals(openedImage.rows(), readStream.rows());
        Assert.assertEquals(openedImage.height(), readStream.height());
        Assert.assertEquals(openedImage.width(), readStream.width());
    }

    @Test
    public void testDetectAndDraw() throws Exception {
        String cascadePath = "/home/sovereign/IdeaProjects/carnumber/src/main/resources/haarcascade_russian_plate_number.xml";
        CascadeClassifier classifier = new CascadeClassifier(cascadePath);
        MatOfRect detections = new MatOfRect();
        Mat image = Detector.readInputStreamIntoMat(path);
        classifier.detectMultiScale(image, detections);
        Assert.assertEquals(detections.toArray().length, 2);
    }

}
