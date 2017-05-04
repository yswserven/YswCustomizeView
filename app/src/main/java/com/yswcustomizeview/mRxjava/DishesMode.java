package com.yswcustomizeview.mRxjava;

import java.util.ArrayList;
import java.util.List;

public class DishesMode {
    private int pageCount;
    private List<DishesMode_List_Details> list = new ArrayList<>();

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<DishesMode_List_Details> getList() {
        return list;
    }

    public void setList(List<DishesMode_List_Details> list) {
        this.list = list;
    }

    public class DishesMode_List_Details {
        private String priceUnit;
        private String isRecommend;
        private String poorNum;
        private String surplusCopies;
        private String isSignature;
        private String price;
        private String middlePath;
        private String bigPath;
        private String smallPath;
        private String imagePath;
        private String dishesName;
        private String discountPrice;
        private String praiseNum;
        private String dishesId;
        private String isHot;
        private String discount;

        public String getPriceUnit() {
            return priceUnit;
        }

        public void setPriceUnit(String priceUnit) {
            this.priceUnit = priceUnit;
        }

        public String getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            this.isRecommend = isRecommend;
        }

        public String getPoorNum() {
            return poorNum;
        }

        public void setPoorNum(String poorNum) {
            this.poorNum = poorNum;
        }

        public String getSurplusCopies() {
            return surplusCopies;
        }

        public void setSurplusCopies(String surplusCopies) {
            this.surplusCopies = surplusCopies;
        }

        public String getIsSignature() {
            return isSignature;
        }

        public void setIsSignature(String isSignature) {
            this.isSignature = isSignature;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMiddlePath() {
            return middlePath;
        }

        public void setMiddlePath(String middlePath) {
            this.middlePath = middlePath;
        }

        public String getBigPath() {
            return bigPath;
        }

        public void setBigPath(String bigPath) {
            this.bigPath = bigPath;
        }

        public String getSmallPath() {
            return smallPath;
        }

        public void setSmallPath(String smallPath) {
            this.smallPath = smallPath;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getDishesName() {
            return dishesName;
        }

        public void setDishesName(String dishesName) {
            this.dishesName = dishesName;
        }

        public String getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(String discountPrice) {
            this.discountPrice = discountPrice;
        }

        public String getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(String praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getDishesId() {
            return dishesId;
        }

        public void setDishesId(String dishesId) {
            this.dishesId = dishesId;
        }

        public String getIsHot() {
            return isHot;
        }

        public void setIsHot(String isHot) {
            this.isHot = isHot;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }
    }
}
