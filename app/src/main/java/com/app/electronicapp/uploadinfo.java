package com.app.electronicapp;

public class uploadinfo {


         public String imageName;
         public String imagephone;
         public String imagetime;
         public String imagedate;
         public String imageURL;
    public uploadinfo() { }

        public uploadinfo(String name, String phone, String date, String day, String url) {
            this.imageName = name;
            this.imagephone =phone;
            this.imagedate = date;
            this.imagetime = day;
            this.imageURL = url;
        }

        public String getImageName() {
            return imageName;
        }
        public String getImagephone() {
        return imagephone;
    }
        public String getImagetime() {
        return imagetime;
    }
        public String getImagedate() {
        return imagedate;
    }
        public String getImageURL() {
            return imageURL;
        }
    }

