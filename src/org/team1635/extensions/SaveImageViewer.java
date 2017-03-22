package org.team1635.extensions;

import edu.wpi.first.smartdashboard.gui.StaticWidget;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.properties.StringProperty;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class SaveImageViewer extends StaticWidget {

	// that doesn't seem right. Check to see if OurStreamViewerImpl has a TYPES.
	// public static final DataType[] TYPES = {ButtonType.get()};
	public static final String NAME = "Save Image Viewer";
	public final StringProperty fileNameProperty = new StringProperty(this, "File Name", "c:\\temp\\image");
	private String fileName = "";
	private int fileNumber = 0;
	private OurStreamViewerImpl streamViewer;

	@Override
	public void init() {
		JButton saveButton = new JButton("Save");
		streamViewer = new OurStreamViewerImpl();
		streamViewer.init();
		System.out.println("debug: SaveImageViewer: viewer property = " + streamViewer.urlProperty.getValue());

		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("debug: SaveImageViewer: button pressed");
				BufferedImage bufferedImage = streamViewer.getImage();

				//TODO: the button should be disabled if no image from server
				if (bufferedImage != null) {
					File outputfile = new File(getFileName());
					try {
						ImageIO.write(bufferedImage, "jpg", outputfile);
						fileNumber++;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("SaveImageViewer: no image to save");
				}
			}
		});

		saveButton.setFocusable(false);

		fileNumber++;
		fileName = fileNameProperty.getValue();

		setLayout(new BorderLayout());
		add(saveButton, BorderLayout.SOUTH);
		add(streamViewer, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	@Override
	public void propertyChanged(Property property) {
		if (property == fileNameProperty) {
			fileName = fileNameProperty.getValue();

		}

	}
	
	private String getFileName() {
		return fileName + fileNumber + ".jpg";
	}

}
