package com.show.car.dto;

public class SearchParam {

    private String brand ;

    private String model ;

    private String name ;

    private float  milesMin ;

    private float milesMax ;

    private int yearMin ;

    private int yearMax ;

    private String origin ;

    private GearboxEnum gearbox;


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMilesMin() {
        return milesMin;
    }

    public void setMilesMin(float milesMin) {
        this.milesMin = milesMin;
    }

    public float getMilesMax() {
        return milesMax;
    }

    public void setMilesMax(float milesMax) {
        this.milesMax = milesMax;
    }

    public int getYearMin() {
        return yearMin;
    }

    public void setYearMin(int yearMin) {
        this.yearMin = yearMin;
    }

    public int getYearMax() {
        return yearMax;
    }

    public void setYearMax(int yearMax) {
        this.yearMax = yearMax;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public GearboxEnum getGearbox() {
        return gearbox;
    }

    public void setGearbox(GearboxEnum gearbox) {
        this.gearbox = gearbox;
    }


    //useful methods to check for params

    public  boolean isBrandParamExists(){
        return  this.brand != null ;
    }

    public  boolean isModelParamExists(){
        return  this.model != null ;
    }

    public  boolean isNameParamExists(){
        return  this.name != null ;
    }

    public  boolean isMilesMinParamExists(){
        return  this.milesMin != 0.0 ;
    }

    public  boolean isMilesMaxParamExists(){
        return  this.milesMax != 0.0 ;
    }

    public  boolean isYearMinParamExists(){
        return  this.yearMin != 0 ;
    }

    public  boolean isYearMaxParamExists(){
        return  this.yearMax != 0 ;
    }

    public  boolean isOriginExists(){
        return  this.origin != null ;
    }

    public  boolean isGearBoxExists(){
        return  this.gearbox != null ;
    }

    @Override
    public String toString() {
        return "SearchParam{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                ", milesMin=" + milesMin +
                ", milesMax=" + milesMax +
                ", yearMin=" + yearMin +
                ", yearMax=" + yearMax +
                ", origin='" + origin + '\'' +
                ", gearbox=" + gearbox +
                '}';
    }
}
