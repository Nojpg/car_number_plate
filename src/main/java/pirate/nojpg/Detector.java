package pirate.nojpg;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.highgui.ImageWindow;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class Detector {
    private CascadeClassifier classifier;


    private void load() {
//        String cascadePath = "src/main/resources/haarcascade_russian_plate_number.xml";
        classifier = new CascadeClassifier(/*cascadePath*/getClass().getResource("./haarcascade_russian_plate_number.xml").getFile());
    }

    static Mat openImage(String fileName) throws Exception {
        final Mat image = Imgcodecs.imread(fileName);
        if (image.dataAddr() == 0) {
            throw new Exception("Couldn't open file " + fileName);
        }
        return image;
    }

    private void detectAndDraw(Mat image) {

        MatOfRect detections = new MatOfRect();
        classifier.detectMultiScale(image, detections, 1.1, 4, 0, new Size(40, 40), new Size());
        for (Rect rect : detections.toArray()) {
            Imgproc.rectangle(
                    image,
                    rect.tl(),
                    rect.br(),
                    new Scalar(0, 255, 0)
            );
        }
        ;
//        String newImage = "/home/sovereign/IdeaProjects/carnumber/src/main/resources/test.jpg";
        Imgcodecs.imwrite(getClass().getResource("result.jpg").getFile(), image);
        swingDraw(image);
    }

    private void swingDraw(Mat image) {
        final ImageIcon imageIcon = new ImageIcon();
        imageIcon.setImage(HighGui.toBufferedImage(image));
        final ImageWindow title = new ImageWindow("foo", image);
        final JFrame frame = HighGui.createJFrame("bar", 0);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        title.setFrameLabelVisible(frame, new JLabel(imageIcon));
    }

    void run(String path) throws Exception {
        load();
        Mat image;
//         = openImage(path)
        image = readInputStreamIntoMat(path);
        detectAndDraw(image);
    }

    static Mat readInputStreamIntoMat(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        byte[] temporaryImageInMemory = readStream(inputStream);
        Mat image = Imgcodecs.imdecode(new MatOfByte(temporaryImageInMemory), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        return image;
    }

    private static byte[] readStream(InputStream stream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = stream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] temporaryImageInMemory = buffer.toByteArray();
        buffer.close();
        stream.close();
        return temporaryImageInMemory;
    }
}
