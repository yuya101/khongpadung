package com.tmadigital.khongpagung.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Maximus on 12/10/2017 AD.
 */

public class ProductDetailNewHeaderItemDao implements Parcelable {
    @SerializedName("header1")
    @Expose
    private String header1;
    @SerializedName("header2")
    @Expose
    private String header2;
    @SerializedName("header3")
    @Expose
    private String header3;
    @SerializedName("header4")
    @Expose
    private String header4;
    @SerializedName("header5")
    @Expose
    private String header5;
    @SerializedName("header6")
    @Expose
    private String header6;
    @SerializedName("header7")
    @Expose
    private String header7;
    @SerializedName("header8")
    @Expose
    private String header8;
    @SerializedName("header9")
    @Expose
    private String header9;
    @SerializedName("header10")
    @Expose
    private String header10;
    @SerializedName("header11")
    @Expose
    private String header11;
    @SerializedName("header12")
    @Expose
    private String header12;
    @SerializedName("header13")
    @Expose
    private String header13;
    @SerializedName("header14")
    @Expose
    private String header14;
    @SerializedName("header15")
    @Expose
    private String header15;
    @SerializedName("header16")
    @Expose
    private String header16;
    @SerializedName("header17")
    @Expose
    private String header17;
    @SerializedName("header18")
    @Expose
    private String header18;
    @SerializedName("header19")
    @Expose
    private String header19;
    @SerializedName("header20")
    @Expose
    private String header20;

    protected ProductDetailNewHeaderItemDao(Parcel in) {
        header1 = in.readString();
        header2 = in.readString();
        header3 = in.readString();
        header4 = in.readString();
        header5 = in.readString();
        header6 = in.readString();
        header7 = in.readString();
        header8 = in.readString();
        header9 = in.readString();
        header10 = in.readString();
        header11 = in.readString();
        header12 = in.readString();
        header13 = in.readString();
        header14 = in.readString();
        header15 = in.readString();
        header16 = in.readString();
        header17 = in.readString();
        header18 = in.readString();
        header19 = in.readString();
        header20 = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(header1);
        dest.writeString(header2);
        dest.writeString(header3);
        dest.writeString(header4);
        dest.writeString(header5);
        dest.writeString(header6);
        dest.writeString(header7);
        dest.writeString(header8);
        dest.writeString(header9);
        dest.writeString(header10);
        dest.writeString(header11);
        dest.writeString(header12);
        dest.writeString(header13);
        dest.writeString(header14);
        dest.writeString(header15);
        dest.writeString(header16);
        dest.writeString(header17);
        dest.writeString(header18);
        dest.writeString(header19);
        dest.writeString(header20);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductDetailNewHeaderItemDao> CREATOR = new Creator<ProductDetailNewHeaderItemDao>() {
        @Override
        public ProductDetailNewHeaderItemDao createFromParcel(Parcel in) {
            return new ProductDetailNewHeaderItemDao(in);
        }

        @Override
        public ProductDetailNewHeaderItemDao[] newArray(int size) {
            return new ProductDetailNewHeaderItemDao[size];
        }
    };

    public String getHeader1() {
        return header1;
    }

    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public String getHeader3() {
        return header3;
    }

    public void setHeader3(String header3) {
        this.header3 = header3;
    }

    public String getHeader4() {
        return header4;
    }

    public void setHeader4(String header4) {
        this.header4 = header4;
    }

    public String getHeader5() {
        return header5;
    }

    public void setHeader5(String header5) {
        this.header5 = header5;
    }

    public String getHeader6() {
        return header6;
    }

    public void setHeader6(String header6) {
        this.header6 = header6;
    }

    public String getHeader7() {
        return header7;
    }

    public void setHeader7(String header7) {
        this.header7 = header7;
    }

    public String getHeader8() {
        return header8;
    }

    public void setHeader8(String header8) {
        this.header8 = header8;
    }

    public String getHeader9() {
        return header9;
    }

    public void setHeader9(String header9) {
        this.header9 = header9;
    }

    public String getHeader10() {
        return header10;
    }

    public void setHeader10(String header10) {
        this.header10 = header10;
    }

    public String getHeader11() {
        return header11;
    }

    public void setHeader11(String header11) {
        this.header11 = header11;
    }

    public String getHeader12() {
        return header12;
    }

    public void setHeader12(String header12) {
        this.header12 = header12;
    }

    public String getHeader13() {
        return header13;
    }

    public void setHeader13(String header13) {
        this.header13 = header13;
    }

    public String getHeader14() {
        return header14;
    }

    public void setHeader14(String header14) {
        this.header14 = header14;
    }

    public String getHeader15() {
        return header15;
    }

    public void setHeader15(String header15) {
        this.header15 = header15;
    }

    public String getHeader16() {
        return header16;
    }

    public void setHeader16(String header16) {
        this.header16 = header16;
    }

    public String getHeader17() {
        return header17;
    }

    public void setHeader17(String header17) {
        this.header17 = header17;
    }

    public String getHeader18() {
        return header18;
    }

    public void setHeader18(String header18) {
        this.header18 = header18;
    }

    public String getHeader19() {
        return header19;
    }

    public void setHeader19(String header19) {
        this.header19 = header19;
    }

    public String getHeader20() {
        return header20;
    }

    public void setHeader20(String header20) {
        this.header20 = header20;
    }
}
