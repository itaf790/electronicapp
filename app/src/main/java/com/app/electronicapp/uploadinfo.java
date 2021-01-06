package com.app.electronicapp;

public class uploadinfo {

    public String imageURL;
         public String imagefirstname;
         public String imagelastname;
         public String imagephone;
         public String imageadress;
    public String imageagender;
    public String imagedate;
    public String imagetime;
    public String imageduration;

    public uploadinfo() { }

        public uploadinfo(String fname, String phone, String lname, String adress, String url,String gender,String date,String time,String duration) {
            this.imagefirstname = fname;
            this.imagephone =phone;
            this.imagelastname= lname;
            this.imageadress = adress;
            this.imageURL = url;
            this.imagedate =date;
            this.imagetime = time;
            this.imageduration = duration;
            this.imageagender = gender;


        }

        public String getImagefirstname() {
            return imagefirstname;
        }
       public String getImagephone() {
        return imagephone;
    }
        public String getImagelastname() {
        return imagelastname;
    }
        
        public String getImageadress() {
        return imageadress;
    }
       
        public String getImageURL() {
            return imageURL;
        }
        public String getImageagender() {
        return imageagender;
    }
       public String getImagedate() {
        return imageURL;
    }
       public String getImagetime() {
        return imageURL;
    }
       public String getImageduration() {
        return imageURL;
    }

    }

