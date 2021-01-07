package com.app.electronicapp;

public class uploadinfo {


         public String imagefirstname;
         public String imagelastname;
         public String imagephone;
         public String imageadress;
         public String imageagender;
       public String imagetime;
        public String imagedate;
       public String imageduration;
       public String imageURL;
       public uploadinfo() { }

        public uploadinfo(String fname, String lname, String phone, String adress, String gender,String time,String date,String duration,String url) {
            this.imagefirstname = fname;
            this.imagelastname= lname;
            this.imagephone =phone;
            this.imageadress = adress;
            this.imageagender = gender;
            this.imagetime = time;
            this.imagedate =date;
            this.imageduration = duration;
            this.imageURL = url;






        }

        public String getImagefirstname() {
            return imagefirstname;
        }
        public String getImagelastname() {
        return imagelastname;
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
         public String getImagetime() {
        return imagetime;
    }
       public String getImagedate() {
        return imagedate;
    }
       public String getImageduration() {
        return imageduration;
    }
        public String getImageURL() {
            return imageURL;
        }




    }

