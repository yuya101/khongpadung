package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus on 12/10/2017 AD.
 */

public class ProductDetailNewDescriptionItemDao implements Parcelable {
    @SerializedName("description1")
    @Expose
    private String description1;
    @SerializedName("description2")
    @Expose
    private String description2;
    @SerializedName("description3")
    @Expose
    private String description3;
    @SerializedName("description4")
    @Expose
    private String description4;
    @SerializedName("description5")
    @Expose
    private String description5;
    @SerializedName("description6")
    @Expose
    private String description6;
    @SerializedName("description7")
    @Expose
    private String description7;
    @SerializedName("description8")
    @Expose
    private String description8;
    @SerializedName("description9")
    @Expose
    private String description9;
    @SerializedName("description10")
    @Expose
    private String description10;
    @SerializedName("description11")
    @Expose
    private String description11;
    @SerializedName("description12")
    @Expose
    private String description12;
    @SerializedName("description13")
    @Expose
    private String description13;
    @SerializedName("description14")
    @Expose
    private String description14;
    @SerializedName("description15")
    @Expose
    private String description15;
    @SerializedName("description16")
    @Expose
    private String description16;
    @SerializedName("description17")
    @Expose
    private String description17;
    @SerializedName("description18")
    @Expose
    private String description18;
    @SerializedName("description19")
    @Expose
    private String description19;
    @SerializedName("description20")
    @Expose
    private String description20;

    protected ProductDetailNewDescriptionItemDao(Parcel in) {
        description1 = in.readString();
        description2 = in.readString();
        description3 = in.readString();
        description4 = in.readString();
        description5 = in.readString();
        description6 = in.readString();
        description7 = in.readString();
        description8 = in.readString();
        description9 = in.readString();
        description10 = in.readString();
        description11 = in.readString();
        description12 = in.readString();
        description13 = in.readString();
        description14 = in.readString();
        description15 = in.readString();
        description16 = in.readString();
        description17 = in.readString();
        description18 = in.readString();
        description19 = in.readString();
        description20 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description1);
        dest.writeString(description2);
        dest.writeString(description3);
        dest.writeString(description4);
        dest.writeString(description5);
        dest.writeString(description6);
        dest.writeString(description7);
        dest.writeString(description8);
        dest.writeString(description9);
        dest.writeString(description10);
        dest.writeString(description11);
        dest.writeString(description12);
        dest.writeString(description13);
        dest.writeString(description14);
        dest.writeString(description15);
        dest.writeString(description16);
        dest.writeString(description17);
        dest.writeString(description18);
        dest.writeString(description19);
        dest.writeString(description20);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductDetailNewDescriptionItemDao> CREATOR = new Creator<ProductDetailNewDescriptionItemDao>() {
        @Override
        public ProductDetailNewDescriptionItemDao createFromParcel(Parcel in) {
            return new ProductDetailNewDescriptionItemDao(in);
        }

        @Override
        public ProductDetailNewDescriptionItemDao[] newArray(int size) {
            return new ProductDetailNewDescriptionItemDao[size];
        }
    };

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getDescription4() {
        return description4;
    }

    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    public String getDescription5() {
        return description5;
    }

    public void setDescription5(String description5) {
        this.description5 = description5;
    }

    public String getDescription6() {
        return description6;
    }

    public void setDescription6(String description6) {
        this.description6 = description6;
    }

    public String getDescription7() {
        return description7;
    }

    public void setDescription7(String description7) {
        this.description7 = description7;
    }

    public String getDescription8() {
        return description8;
    }

    public void setDescription8(String description8) {
        this.description8 = description8;
    }

    public String getDescription9() {
        return description9;
    }

    public void setDescription9(String description9) {
        this.description9 = description9;
    }

    public String getDescription10() {
        return description10;
    }

    public void setDescription10(String description10) {
        this.description10 = description10;
    }

    public String getDescription11() {
        return description11;
    }

    public void setDescription11(String description11) {
        this.description11 = description11;
    }

    public String getDescription12() {
        return description12;
    }

    public void setDescription12(String description12) {
        this.description12 = description12;
    }

    public String getDescription13() {
        return description13;
    }

    public void setDescription13(String description13) {
        this.description13 = description13;
    }

    public String getDescription14() {
        return description14;
    }

    public void setDescription14(String description14) {
        this.description14 = description14;
    }

    public String getDescription15() {
        return description15;
    }

    public void setDescription15(String description15) {
        this.description15 = description15;
    }

    public String getDescription16() {
        return description16;
    }

    public void setDescription16(String description16) {
        this.description16 = description16;
    }

    public String getDescription17() {
        return description17;
    }

    public void setDescription17(String description17) {
        this.description17 = description17;
    }

    public String getDescription18() {
        return description18;
    }

    public void setDescription18(String description18) {
        this.description18 = description18;
    }

    public String getDescription19() {
        return description19;
    }

    public void setDescription19(String description19) {
        this.description19 = description19;
    }

    public String getDescription20() {
        return description20;
    }

    public void setDescription20(String description20) {
        this.description20 = description20;
    }
}
