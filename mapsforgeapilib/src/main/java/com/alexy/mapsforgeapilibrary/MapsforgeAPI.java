package com.alexy.mapsforgeapilibrary;

public interface MapsforgeAPI {
    interface OnTapListener {
        boolean onTap(double lat, double lon);
    }

    interface OnLongPressListener {
        boolean onLongPress(double lat, double lon);
    }

    void loadMap(String fileName);

    void destroy();

    void zoomIn(boolean animated);

    void zoomOut(boolean animated);

    void zoomTo(int zoom, boolean animated);

    void moveTo(double lat, double lon);

    void animateTo(double lat, double lon);

    void setOnTapListener(OnTapListener listener);

    void sedOnLongPressListener(OnLongPressListener listener);
}
