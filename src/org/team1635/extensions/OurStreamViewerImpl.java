package org.team1635.extensions;

import org.team1635.extensions.OurStreamViewer;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.properties.StringProperty;

import java.awt.image.BufferedImage;
import java.util.stream.Stream;


public class OurStreamViewerImpl extends OurStreamViewer {
    public static final String NAME = "Our Stream Viewer";
	public final StringProperty urlProperty = new StringProperty(this, "Our Server URL", "http://raspberrypi.local:1186/stream.mjpg");
	private String url = "";

	@Override
	public void onInit() {
		url = STREAM_PREFIX + urlProperty.getValue();
	}

	@Override
	public void onPropertyChanged(Property property) {
	  if (property == urlProperty) {
		  url = STREAM_PREFIX + urlProperty.getValue();
	      cameraChanged();
	  }
    }

	@Override
	public Stream<String> streamPossibleCameraUrls() {
	    return Stream.of(url);
	}
	
	public BufferedImage getImage() {
		return imageToDraw;
	}
}