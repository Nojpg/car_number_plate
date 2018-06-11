package pirate.nojpg;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class FileChooser extends JFrame {
    String cascadePath;
    String imagePath;

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
            pathEditor();
            new Detector().run(jfc.getSelectedFile().getPath(), cascadePath, imagePath);
        }
    }

    void pathEditor() {
        cascadePath = JOptionPane.showInputDialog(null, "enter path for cascade classifier:");
        if (cascadePath!=null) {
            System.out.println(cascadePath);
            imagePath = JOptionPane.showInputDialog(null, "enter path for new image: ");
            System.out.println(imagePath);
        }
    }

}
