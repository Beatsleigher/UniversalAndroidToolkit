//<editor-fold defaultstate="collapsed" desc="Android Tab">
    //<editor-fold defaultstate="collapsed" desc="Install Application">
    /**
     * Allows user to select a .APK to install to (selected) device
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser apkChooser = new JFileChooser();
        apkChooser.setApproveButtonText("Install this Application");
        apkChooser.setFileFilter(new APKFilter());
        apkChooser.setDialogTitle("Select a .APK file to install to device...");
        int dialogRes = apkChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION)
            jTextField1.setText(apkChooser.getSelectedFile().toString());
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Installs selected application to selected device.
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        log.log(LogLevel.INFO, "Installing application " + jTextField1.getText() + " to device " + devices.getSelectedADBDevice());
        try {
            log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, false, devices.getSelectedADBDevice(), new String[]{"install", jTextField1.getText()}));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while installing application!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Uninstall Application">
    /**
     * Allows user to select an application file from his hard drive, which is then used to uninstall the application from the device.
     * This method is purely for convenience.
     * Personally, I added it, because I always find I have apps on my hard drive(s), which I don't need on my device, 
     * and you never get the ability to just pick an app you have and then just have it removed.
     * @param evt 
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser apkChooser = new JFileChooser();
        
        JOptionPane.showMessageDialog(null, "INFORMATION: Only use this, if you have the application file on your computer.\n"
                + "This is purely for convenience. Otherwise, you will have to\n"
                + "1) Wait until the file and app managers are released\n"
                + "2) Type the application package in, yourself.\n"
                + "Sorry if you awaited something different. Rest assured, it's on its way!", "Don't Worry - It's Coming!", JOptionPane.INFORMATION_MESSAGE);
        
        apkChooser.setApproveButtonText("Select this Application");
        apkChooser.setFileFilter(new APKFilter());
        apkChooser.setDialogTitle("Select a .APK file to uninstall from device...");
        int dialogRes = apkChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION)
            jTextField2.setText(apkChooser.getSelectedFile().toString());
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * Attempts to uninstall the application selected in the jTextField from  the selected device.
     * This code may be broken, due to a brain fart, but we'll see...
     * @param evt 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String deviceSerial = devices.getSelectedADBDevice();
        String[] packageName = jTextField2.getText().split("/|\\\\"); // This is where the brain fart will most likely occur.
        String pack = packageName[packageName.length - 1];
        String[] cmd = {"uninstall", pack};
        log.log(LogLevel.INFO, "Uninstalling " + pack + " from device " + deviceSerial + ".");
        try {
            log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, false, deviceSerial, cmd));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while uninstalling application from device!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Push File">
    /**
     * Allows users to select a file from the computer and push (copy) it to the device.
     * @param evt 
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (usedPushes == 10) {
            JOptionPane.showMessageDialog(null, "Me matey. Ye be pushin' some load o' files, thar...", "Arrrrr!", JOptionPane.INFORMATION_MESSAGE);
            log.log(LogLevel.FINEST, "Achievement Get! Found Easter Egg \"Captain, ahoy!\""); // Dat Minecraft reference...
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setDialogTitle("Choose a file to push the your device...");
        fileChooser.setApproveButtonText("Push this File...");
        
        int dialogRes = fileChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION)
            jTextField3.setText(fileChooser.getSelectedFile().toString());
        
        usedPushes++;
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * Prompts user to input a location to push the selected file to, then pushes file and clears text field.
     * @param evt 
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        log.log(LogLevel.INFO, "Pushing file to device " + devices.getSelectedADBDevice() + ". Prompting user for file destination.");
        String dest = JOptionPane.showInputDialog(null, "Please enter a destination on the selected device to push the selected file to.", "Choose File Destination", JOptionPane.QUESTION_MESSAGE);
        String deviceSerial = devices.getSelectedADBDevice();
        String[] cmd = { "push", jTextField3.getText(), dest };
        try {
            boolean remount = false;
            if (dest.contains("/system")) remount = true;
            log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, remount, deviceSerial, cmd));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while pushing file " + jTextField3.getText() + " to device!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    //</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="Pull File">
    /**
     * This will soon allow users to browse their device for files.
     * @param evt 
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        JOptionPane.showMessageDialog(null, "INFORMATION: Sorry, but this features has not yet been implemented.\n"
                + "Please try again in a later version of Universal Android Toolkit. \n"
                + "However, you can still type the location of the file and pull it that way.", "Feature not yet Implemented.", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * Prompts user to select a destination on the hard drive and attempts to pull selected file.
     * @param evt 
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String dest = JOptionPane.showInputDialog(null, "INFORMATION: Please enter a destination on your hard drive to pull the file to.", "Select Destination...", JOptionPane.INFORMATION_MESSAGE);
        log.log(LogLevel.INFO, "Pulling file " + jTextField4.getText() + " to " + dest + " from device " + devices.getSelectedADBDevice());
        try {
            log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, false, devices.getSelectedADBDevice(), new String[]{"pull", jTextField4.getText(), dest}));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while pulling file from device!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton8ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Install (root) Application">
    /**
     * Allows user to select a .APK file from the hard drive, which should be installed.
     * @param evt 
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        JFileChooser apkChooser = new JFileChooser();
        apkChooser.setApproveButtonText("Install this Application");
        apkChooser.setFileFilter(new APKFilter());
        apkChooser.setDialogTitle("Select a .APK file to install to device...");
        int dialogRes = apkChooser.showOpenDialog(null);
        if (dialogRes == JOptionPane.OK_OPTION)
            jTextField5.setText(apkChooser.getSelectedFile().toString());
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * Installs selected application to device.
     * @param evt 
     */
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        log.log(LogLevel.INFO, "Installing application " + jTextField5.getText() + " to /system/app.");
        try {
            log.log(LogLevel.INFO, "ADB Output: " + adbController.executeADBCommand(false, true, devices.getSelectedADBDevice(), new String[]{"push", jTextField5.getText(), "/system/app/"}));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while installing application as root to device!" + ex.toString());
        }
    }//GEN-LAST:event_jButton10ActionPerformed
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Uninstall (Root) Application)">
    /**
     * Will soon allow users to select an application from their device to uninstall.
     * @param evt 
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        JOptionPane.showMessageDialog(null, "INFORMATION: Sorry, but this features has not yet been implemented.\n"
                + "Please try again in a later version of Universal Android Toolkit. \n"
                + "However, you can still type the location of the file and pull it that way.", "Feature not yet Implemented.", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * Attempts to uninstall the selected app from the system partition.
     * @param evt 
     */
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        log.log(LogLevel.INFO, "Uninstalling application " + jTextField6.getText() + " from device " + devices.getSelectedADBDevice());
        try {
            log.log(LogLevel.FINE, "ADB Output: " + adbController.executeADBCommand(true, true, devices.getSelectedADBDevice(), new String[]{"rm", jTextField6.getText()}));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error while uninstalling application from system!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton12ActionPerformed
    //</editor-fold>
    //</editor-fold>
    
    ==============================================================================================================================================================================
    ==============================================================================================================================================================================
    ==============================================================================================================================================================================
    ==============================================================================================================================================================================
    
    //editor-fold defaultstate="collapsed" desc="Fastboot tab">
    //<editor-fold defaultstate="collapsed" desc="Flash Image">
    /**
     * Allows user to select an image file, to flash to system.
     * @param evt 
     */
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        JFileChooser imageChooser = new JFileChooser();
        imageChooser.setDialogTitle("Please Select an Image to Flash to your Device...");
        imageChooser.setApproveButtonText("Flash this image.");
        imageChooser.setFileFilter(new IMGFilter());
        int dialogRes = imageChooser.showOpenDialog(null);
        if ((dialogRes == JOptionPane.OK_OPTION) && (imageChooser.getSelectedFile().toString().toLowerCase().endsWith(".img")))
            jTextField7.setText(imageChooser.getSelectedFile().toString());
    }//GEN-LAST:event_jButton19ActionPerformed

    /**
     * Flashes selected image to device via fastboot.
     * Tries to detect image type, via file name. If image type cannot be analyzed, user will be prompted to enter.
     * @param evt 
     */
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        String img = jTextField7.getText().toLowerCase();
        String imgType = "";
        String deviceSerial = devices.getSelectedFastbootDevice();
        /*Log*/ log.log(LogLevel.INFO, "Flashing image " + img + " to device " + deviceSerial + ". Detecting image type.");
        String[] cmds = null;
        // Detect image type.
        if ((img.contains("recovery")) || (img.contains("cwm")) || (img.contains("twrp")))
            imgType = "recovery";
        else if (img.contains("system"))
            imgType = "system";
        else if ((img.contains("boot")) || img.contains("kernel"))
            imgType = "boot";
        else if (img.contains("userdata"))
            imgType = "userdata";
        else if ((img.contains("radio")) || (img.contains("modem")))
            imgType = "radio";
        else if (img.contains("bootloader"))
            imgType = JOptionPane.showInputDialog(null, "WARNING: Image type could not be identified! Please enter the image type below:\n"
                    + "You have following choices:\n"
                    + "recovery\n"
                    + "boot\n"
                    + "userdata\n"
                    + "system\n"
                    + "radio\n"
                    + "bootloader", "Could not Identify Image!", JOptionPane.WARNING_MESSAGE);
        /*Log*/ log.log(LogLevel.INFO, "Detected image type: " + imgType);
        // Prepare commands and flash image.
        try {
            cmds = new String[]{"flash", imgType};
            log.log(LogLevel.INFO, "Fastboot output: " + adbController.executeFastbootCommand(deviceSerial, cmds));
        } catch (IOException ex) {
            log.log(LogLevel.SEVERE, "ERROR: Error flashing image to device!\n" + ex.toString());
        }
    }//GEN-LAST:event_jButton20ActionPerformed
    //</editor-fold>