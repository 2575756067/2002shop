package com.example.shopkuang.ui.shopaddress;

public class ShopAddressBean {

    private String name;
    private String phone;
    private String detailsAddress;

    public ShopAddressBean(String name, String phone,  String detailsAddress) {
        this.name = name;
        this.phone = phone;
        this.detailsAddress = detailsAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress;
    }
}
