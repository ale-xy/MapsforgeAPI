package com.alexy.mapsforgeapilibrary;

import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.Point;
import org.mapsforge.map.datastore.MapDataStore;
import org.mapsforge.map.layer.cache.TileCache;
import org.mapsforge.map.layer.hills.HillsRenderConfig;
import org.mapsforge.map.layer.renderer.TileRendererLayer;
import org.mapsforge.map.model.MapViewPosition;

import java.lang.ref.WeakReference;

/**
 * Created by alexeykrichun on 23/01/2018.
 */

public class ClickableTileRendererLayer extends TileRendererLayer {

    private MapsforgeAPI.OnTapListener tapListener;
    private MapsforgeAPI.OnLongPressListener longPressListener;

    public ClickableTileRendererLayer(TileCache tileCache, MapDataStore mapDataStore, MapViewPosition mapViewPosition, GraphicFactory graphicFactory) {
        super(tileCache, mapDataStore, mapViewPosition, graphicFactory);
    }

    public ClickableTileRendererLayer(TileCache tileCache, MapDataStore mapDataStore, MapViewPosition mapViewPosition, boolean isTransparent, boolean renderLabels, boolean cacheLabels, GraphicFactory graphicFactory) {
        super(tileCache, mapDataStore, mapViewPosition, isTransparent, renderLabels, cacheLabels, graphicFactory);
    }

    public ClickableTileRendererLayer(TileCache tileCache, MapDataStore mapDataStore, MapViewPosition mapViewPosition, boolean isTransparent, boolean renderLabels, boolean cacheLabels, GraphicFactory graphicFactory, HillsRenderConfig hillsRenderConfig) {
        super(tileCache, mapDataStore, mapViewPosition, isTransparent, renderLabels, cacheLabels, graphicFactory, hillsRenderConfig);
    }

    public void setTapListener(MapsforgeAPI.OnTapListener tapListener) {
        this.tapListener = tapListener;
    }

    public void setLongPressListener(MapsforgeAPI.OnLongPressListener longPressListener) {
        this.longPressListener = longPressListener;
    }

    @Override
    public boolean onLongPress(LatLong tapLatLong, Point layerXY, Point tapXY) {
        if (longPressListener != null) {
            return longPressListener.onLongPress(tapLatLong.getLatitude(), tapLatLong.getLongitude());
        }
        return super.onLongPress(tapLatLong, layerXY, tapXY);
    }

    @Override
    public boolean onTap(LatLong tapLatLong, Point layerXY, Point tapXY) {
        if (tapListener != null) {
            return tapListener.onTap(tapLatLong.getLatitude(), tapLatLong.getLongitude());
        }
        return super.onTap(tapLatLong, layerXY, tapXY);
    }
}
