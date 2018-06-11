package pirate.nojpg;

import org.opencv.core.Core;

public class App {

    public static void main(String[] args) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new FileChooser().run();
    }

}
