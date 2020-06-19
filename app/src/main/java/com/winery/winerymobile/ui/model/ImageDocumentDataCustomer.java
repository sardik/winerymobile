package com.winery.winerymobile.ui.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageDocumentDataCustomer {

    int imageID;
    String image;

    public ImageDocumentDataCustomer(int imageId, String image) {
        this.imageID = imageId;
        this.image = image;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
