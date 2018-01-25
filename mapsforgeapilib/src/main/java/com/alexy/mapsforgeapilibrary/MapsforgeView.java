package com.alexy.mapsforgeapilibrary;

import android.content.Context;
import android.util.AttributeSet;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
import org.mapsforge.map.android.util.AndroidUtil;
import org.mapsforge.map.android.view.MapView;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.reader.MapFile;
import org.mapsforge.map.rendertheme.InternalRenderTheme;

import java.io.File;

/**
 * Created by alexeykrichun on 22/01/2018.
 */

public class MapsforgeView extends MapView implements MapsforgeAPI{

    private TileCache tileCache;
    private ClickableTileRendererLayer tileRendererLayer;

    private OnTapListener onTapListener;
    private OnLongPressListener onLongPressListener;

    public MapsforgeView(Context context) {
        super(context);
    }

    public MapsforgeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void loadMap(String fileName) {
        createTileCache();

        setClickable(true);
        getMapScaleBar().setVisible(false);
        setBuiltInZoomControls(false);

        MapDataStore mapDataStore = new MapFile(new File(fileName));
        tileRendererLayer = new ClickableTileRendererLayer(tileCache, mapDataStore, getModel().mapViewPosition, AndroidGraphicFactory.INSTANCE);
        tileRendererLayer.setXmlRenderTheme(InternalRenderTheme.DEFAULT);

        tileRendererLayer.setTapListener(onTapListener);
        tileRendererLayer.setLongPressListener(onLongPressListener);

        getLayerManager().getLayers().add(tileRendererLayer);
    }


    @Override
    public void zoomIn(boolean animated) {
        getModel().mapViewPosition.zoomIn(animated);
    }

    @Override
    public void zoomOut(boolean animated) {
        getModel().mapViewPosition.zoomOut(animated);
    }

    @Override
    public void zoomTo(int zoom, boolean animated) {
        getModel().mapViewPosition.setZoomLevel((byte)zoom, animated);
    }

    @Override
    public void moveTo(double lat, double lon) {
        getModel().mapViewPosition.setCenter(new LatLong(lat, lon));
    }

    @Override
    public void animateTo(double lat, double lon) {
        getModel().mapViewPosition.animateTo(new LatLong(lat, lon));
    }

    private void createTileCache() {
        if (tileCache == null) {
            tileCache = AndroidUtil.createTileCache(getContext(),
                    getContext().getPackageName() + ".mapcache",
                    getModel().displayModel.getTileSize(),
                    1f,
                    this.getModel().frameBufferModel.getOverdrawFactor());
        }
    }

    @Override
    public void setOnTapListener(OnTapListener listener) {
        onTapListener = listener;
        if (tileRendererLayer != null) {
            tileRendererLayer.setTapListener(listener);
        }
    }

    @Override
    public void sedOnLongPressListener(OnLongPressListener listener) {
        onLongPressListener = listener;
        if (tileRendererLayer != null) {
            tileRendererLayer.setLongPressListener(listener);
        }
    }

    public void destroy() {
        destroyAll();
    }

}
