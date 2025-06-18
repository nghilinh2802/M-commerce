//package com.nghilinh.models;
//
//import androidx.annotation.NonNull;
//
//import java.io.Serializable;
//
//public class TelephonieInfor implements Serializable {
//    private String name;
//    public String phone;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public TelephonieInfor(String name, String phone) {
//        this.name = name;
//        this.phone = phone;
//    }
//
//    public TelephonieInfor() {
//    }
//
//    @NonNull
//    @Override
//    public String toString() {
//        return this.name+ "\n" + this.phone;
//    }
//
//}

package com.nghilinh.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TelephonieInfor implements Serializable {
    private String name;
    public String phone;
    private String networkProvider;

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
        this.networkProvider = determineNetworkProvider(phone);
    }

    public String getNetworkProvider() {
        return networkProvider;
    }

    public TelephonieInfor(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.networkProvider = determineNetworkProvider(phone);
    }

    public TelephonieInfor() {
    }

    private String determineNetworkProvider(String phone) {
        if (phone == null || phone.isEmpty()) return "Unknown";

        // Chuẩn hóa số điện thoại: loại bỏ ký tự không phải số và lấy 10 chữ số cuối
        String normalizedPhone = phone.replaceAll("[^0-9]", "");
        if (normalizedPhone.length() >= 10) {
            normalizedPhone = normalizedPhone.substring(normalizedPhone.length() - 10);
        }

        // Đầu số nhà mạng Viettel
        String[] viettelPrefixes = {"086", "096", "097", "098", "032", "033", "034", "035", "036", "037", "038", "039"};
        // Đầu số nhà mạng Mobifone
        String[] mobifonePrefixes = {"089", "090", "093", "070", "076", "077", "078", "079"};

        String prefix = normalizedPhone.substring(0, Math.min(3, normalizedPhone.length()));

        for (String viettelPrefix : viettelPrefixes) {
            if (prefix.equals(viettelPrefix)) {
                return "Viettel";
            }
        }

        for (String mobifonePrefix : mobifonePrefixes) {
            if (prefix.equals(mobifonePrefix)) {
                return "Mobifone";
            }
        }

        return "Other";
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + "\n" + this.phone + "\n" + this.networkProvider;
    }
}