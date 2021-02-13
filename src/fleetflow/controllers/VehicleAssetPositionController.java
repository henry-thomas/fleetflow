/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow.controllers;

import fleetflow.entity.Asset;
import fleetflow.entity.AssetPositionHistory;
import fleetflow.entity.VehicleAsset;
import fleetflow.facade.AssetPositionHistoryDao;
import fleetflow.utils.PersistanceUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author henry
 */
public class VehicleAssetPositionController {

    VehicleAsset asset;
    AssetPositionHistory history;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FleetFlowPU");
    AssetPositionHistoryDao dao = new AssetPositionHistoryDao(emf);

    public VehicleAssetPositionController(VehicleAsset asset) {
        this.asset = asset;
    }

    public void recordPostion(Asset asset, Double longitude, Double latitude) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        PersistanceUtils pu = new PersistanceUtils();
        Date date = pu.parseTimestamp(LocalDateTime.now().format(myFormatObj));
        history = new AssetPositionHistory(asset, longitude, latitude, date);
//        history.setAssetId(asset.getId());
//        history.setDateTime(date);
//        history.setLatitude(latitude);
//        history.setLongitude(longitude);
        dao.create(history);
    }
}
