/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fleetflow.utils;

/**
 *
 * @author henry
 */
public enum VehicleManufacturerEnum {
    VW("Volkswagen"),
    TOYOTA("Toyota"),
    FORD("Ford"),
    BMW("BMW");

    private final String name;

    VehicleManufacturerEnum(String name) {
        this.name = name;
    }
}
