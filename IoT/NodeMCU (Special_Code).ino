#include<SPI.h>
#include<MFRC522.h>
#include<ArduinoJson.h>
#include<ESP8266WiFi.h>
#include<FirebaseArduino.h>

#define FIREBASE_HOST "rtbms-dev-001.firebaseio.com"
#define AUTH_KEY "R6MghWkhMq98IbowRWOxiHI2xiKfyDoye9On8Exx"
#define WIFI_SSID "Enter_Your_SSID" // Change the name of your WIFI
#define WIFI_PASSWORD "Enter_Your_Password" // Change the password of your WIFI


//creating mfrc522 instance
#define SSPIN 4  //D2
#define RSTPIN 5 //D1
MFRC522 rc(SSPIN, RSTPIN);

#define LEDR D8
#define LEDY D4
#define LEDG D3

const int buzzerPin = 16;

byte readcard[4]; //stores the UID of current tag which is read

void setup() {

  Serial.begin(115200);

  WiFi.begin (WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print("......");
  }

  SPI.begin(); 
  Serial.println ("");
  Serial.println ("WiFi Connected!");
  Firebase.begin(FIREBASE_HOST, AUTH_KEY);

  rc.PCD_Init(); //initialize the receiver

  pinMode(LEDR, OUTPUT);
  pinMode(LEDY, OUTPUT);
  pinMode(LEDG, OUTPUT);

  pinMode(buzzerPin, OUTPUT);
}

void loop() {

  digitalWrite(LEDR, HIGH);
  
  if (WiFi.status() == WL_CONNECTED) {
    digitalWrite(LEDY, HIGH);
    
  }
  else
    digitalWrite(LEDY, LOW);
  
  getid();
  
  delay(500);
  
}

//function to get the UID of the card
int getid() {

String ADDRESS = "web/hospitals/Punjab/Patiala/HID0702";  /// Location of the data 

  if (!rc.PICC_IsNewCardPresent()) {
    return 0;
  }
  if (!rc.PICC_ReadCardSerial()) {
    return 0;
  }

  String content = "";
  byte letter;
  Serial.println();
  Serial.println(" THE UID OF THE SCANNED CARD IS: ");
  for (byte i = 0; i < rc.uid.size; i++)  {
    Serial.print(rc.uid.uidByte[i] < 0x10 ? " 0" : " ");
    Serial.print(rc.uid.uidByte[i], HEX);
    content.concat(String(rc.uid.uidByte[i] < 0x10 ? " 0" : " "));
    content.concat(String(rc.uid.uidByte[i], HEX));
  }
  Serial.println();
  Serial.println();
  content.toUpperCase();
  

  // loop to define the blood grop value
    String bloodGroup = "";
    if(String(rc.uid.uidByte[0], HEX)=="db"){
      bloodGroup = "A+";
    }
    else if(String(rc.uid.uidByte[0], HEX)=="fb"){
      bloodGroup = "A-";
    }
    else if(String(rc.uid.uidByte[0], HEX)=="4b"){
      bloodGroup = "B+";
    }
    else if(String(rc.uid.uidByte[0], HEX)=="90"){
      bloodGroup = "B-";
    }
    else if(String(rc.uid.uidByte[0], HEX)=="2b"){
      bloodGroup = "O+";
    }
    else if(String(rc.uid.uidByte[0], HEX)=="5b"){
      bloodGroup = "O-";
    }
    else if(String(rc.uid.uidByte[0], HEX)=="8b"){
      bloodGroup = "AB+";
    }
    else {
      bloodGroup = "AB-";
    }
  
    Serial.println(" Blood Group : ");
    Serial.println(" "+bloodGroup);

  

  //  Fetch data from DB  
      String UID2 = content.substring(1);
      String UID1 = Firebase.getString("iot/hospitals/Punjab/Patiala/HID0702/"+content.substring(1)+"/cardUID");
      if(UID1 == UID2){
        digitalWrite(LEDG, LOW);
        tone(buzzerPin, 50);
        Firebase.remove("iot/hospitals/Punjab/Patiala/HID0702/"+content.substring(1));

        if(String(rc.uid.uidByte[0], HEX)=="db"){
          double count = Firebase.getInt(ADDRESS + "/aPCount") - 1;
          String strcon1 = ADDRESS + "/aPCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="fb"){
          double count = Firebase.getInt(ADDRESS + "/aNCount") - 1;
          String strcon1 = ADDRESS + "/aNCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="4b"){
          double count = Firebase.getInt(ADDRESS + "/bPCount") - 1;
          String strcon1 = ADDRESS + "/bPCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="90"){
          double count = Firebase.getInt(ADDRESS + "/bNCount") - 1;
          String strcon1 = ADDRESS + "/bNCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="2b"){
          double count = Firebase.getInt(ADDRESS + "/oPCount") - 1;
          String strcon1 = ADDRESS + "/oPCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="5b"){
          double count = Firebase.getInt(ADDRESS + "/oNCount") - 1;
          String strcon1 = ADDRESS + "/oNCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="8b"){
          double count = Firebase.getInt(ADDRESS + "/abPCount") - 1;
          String strcon1 = ADDRESS + "/abPCount";
          Firebase.set(strcon1, count);
        }
        else {
          double count = Firebase.getInt(ADDRESS + "/abNCount") - 1;
          String strcon1 = ADDRESS + "/abNCount";
          Firebase.set(strcon1, count);
        }
        digitalWrite(LEDG, HIGH);
        noTone(buzzerPin);
        
      }
      else {
        digitalWrite(LEDG, LOW);
        tone(buzzerPin, 50);
        DynamicJsonBuffer jsonBuffer;
        JsonObject& iotObject = jsonBuffer.createObject();
        iotObject["cardUID"] = content.substring(1);
        iotObject["bloodGroup"] = bloodGroup;
        String strcon = "iot/hospitals/Punjab/Patiala/HID0702/" + content.substring(1);
        Firebase.set(strcon, iotObject);

       /*double count = Firebase.getInt("iot/hospitalDetails/Chandigarh/HID1262/Count1") + 1;
        String strcon1 = "iot/hospitalDetails/Chandigarh/HID1262/Count1";
        Firebase.set(strcon1, count);*/




        if(String(rc.uid.uidByte[0], HEX)=="db"){
          double count = Firebase.getInt(ADDRESS + "/aPCount") + 1;
          String strcon1 = ADDRESS + "/aPCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="fb"){
          double count = Firebase.getInt(ADDRESS + "/aNCount") + 1;
          String strcon1 = ADDRESS + "/aNCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="4b"){
          double count = Firebase.getInt(ADDRESS + "/bPCount") + 1;
          String strcon1 = ADDRESS + "/bPCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="90"){
          double count = Firebase.getInt(ADDRESS + "/bNCount") + 1;
          String strcon1 = ADDRESS + "/bNCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="2b"){
          double count = Firebase.getInt(ADDRESS + "/oPCount") + 1;
          String strcon1 = ADDRESS + "/oPCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="5b"){
          double count = Firebase.getInt(ADDRESS + "/oNCount") + 1;
          String strcon1 = ADDRESS + "/oNCount";
          Firebase.set(strcon1, count);
        }
        else if(String(rc.uid.uidByte[0], HEX)=="8b"){
          double count = Firebase.getInt(ADDRESS + "/abPCount") + 1;
          String strcon1 = ADDRESS + "/abPCount";
          Firebase.set(strcon1, count);
        }
        else {
          double count = Firebase.getInt(ADDRESS + "/abNCount") + 1;
          String strcon1 = ADDRESS + "/abNCount";
          Firebase.set(strcon1, count);
        }

        
        digitalWrite(LEDG, HIGH);
        noTone(buzzerPin);
      }

  return 1;


   }
