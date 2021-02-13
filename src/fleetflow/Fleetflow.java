/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow;

import controllers.VehicleAssetPositionController;
import fleetflow.entity.VehicleAsset;
import fleetflow.utils.PersistanceUtils;
import fleetflow.utils.VehicleManufacturerEnum;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author henry
 */
public class Fleetflow {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double latitude = -33.828807;
        double longitude = 18.544825;

        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        PersistanceUtils pu = new PersistanceUtils();
        VehicleManufacturerEnum vm = VehicleManufacturerEnum.TOYOTA;

        pu.clearTable("VehicleAsset");
        VehicleAsset vAsset = new VehicleAsset();
        vAsset.setDateAdded((new Date(System.currentTimeMillis())));
        vAsset.setHomeLatitude(latitude);
        vAsset.setHomeLongitude(longitude);
        vAsset.setManufacturer(vm.name());
        vAsset.setMileage(24001.2);
        vAsset.setModel("Tiguan");
        vAsset.setRegistrationNumber("CPH575L");
        vAsset.setVinNumber("12V234LKN98J45");
        pu.store(vAsset);

        VehicleAssetPositionController controller = new VehicleAssetPositionController(vAsset);
        pu.clearTable("AssetPositionHistory");
        for (int i = 0; i < 10; i++) {
            latitude = latitude + i;
            longitude = longitude + i;
//            System.out.println(LocalDateTime.now().format(myFormatObj));
//            System.out.println(pu.parseTimestamp(LocalDateTime.now().format(myFormatObj)));
            controller.recordPostion(vAsset, longitude, latitude);
        }
    }

}
