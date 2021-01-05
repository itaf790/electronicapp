package com.app.electronicapp;

public class uploadinfo {

    public String imageURL;
         public String imagefirstname;
         public String imagelastname;
         public String imagephone;
         public String imageadress;
    public String imageagender;
    public uploadinfo() { }

        public uploadinfo(String fname, String phone, String lname, String adress, String url,String gender) {
            this.imagefirstname = fname;
            this.imagephone =phone;
            this.imageadress = adress;
            this.imageagender = gender;
            this.imageURL = url;
            this.imagelastname= lname;
        }

        public String getImagefirstname() {
            return imagefirstname;
        }
        public String getImagephone() {
        return imagephone;
    }
        public String getImageadress() {
        return imageadress;
    }
        public String getImageagender() {
        return imageagender;
    }
        public String getImageURL() {
            return imageURL;
        }
         public String getImagelastname() {
        return imagelastname;
    }
    }

