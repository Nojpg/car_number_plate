package pirate.nojpg;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class FileChooser {

    void run() throws Exception {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose image to detect car number plate: ");
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG, JPG, JPEG images", "png", "jpg", "jpeg");
        jfc.addChoosableFileFilter(filter);
        jfc.showOpenDialog(null);
        if (jfc.getSelectedFile() != null) {
            System.out.println(jfc.getSelectedFile().getPath());
            new Detector().run(jfc.getSelectedFile().getPath());
        }
    }

}
