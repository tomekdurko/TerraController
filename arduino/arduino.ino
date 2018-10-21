#include <LiquidCrystal.h> //Dołączenie bilbioteki
#include <Ethernet.h>
#include <SPI.h>


LiquidCrystal lcd(2, 3, 4, 5, 6, 7); //Informacja o podłączeniu nowego wyświetlacza
#include "DHT.h"
#define DHT11_PIN 8
DHT dht;
// Shield Ethernet
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
byte ip[] = { 192, 168, 1, 149 };
byte server[] = { 192, 168, 1, 24 };
byte subnet[] = {255, 255, 255, 0 };
byte gateway[] = { 192, 168, 1, 1 };

EthernetClient client;

void setup() {
  Ethernet.begin(mac, ip); 
  // Ethernet.begin(mac, ip,server,gateway, subnet);
  Serial.begin(9600);
  lcd.begin(16, 2); //Deklaracja typu
  dht.setup(DHT11_PIN);
  pinMode(10, OUTPUT); // didoda czerwona
  pinMode(11, OUTPUT); // dioda zielona
}

void loop() {
  int wilgotnosc = dht.getHumidity();
  int temperatura = dht.getTemperature();
  delay(1000);
  Serial.println("connecting...");
  if (dht.getStatusString() == "OK") {
      lcd.setCursor(0, 0);
      lcd.print("wilg ");
      lcd.print(wilgotnosc);
      Serial.println(wilgotnosc);
      lcd.print("%RH");
      lcd.setCursor(0, 1);
      lcd.print("temp " );
      lcd.print(temperatura);
      Serial.println(temperatura);
      lcd.println("*C");
      if(temperatura>18&&temperatura<30)
      {
        digitalWrite(11, HIGH);
        digitalWrite(10, LOW);
      }
      else
      {
        digitalWrite(10, HIGH);
        digitalWrite(11, LOW);
      }
    if (client.connect(server, 8080)) {
    Serial.println("connected");
    client.print("GET /measurement/arduino?temperature=");
    client.print(temperatura);
    client.print("&humidity=");
    client.print(wilgotnosc);
    client.println(" HTTP/1.0");
    client.println();
    client.stop();
    
  } else {
    Serial.println("connection failed");
  }
       
  }
  delay(100000);
}
