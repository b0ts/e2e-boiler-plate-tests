#include <Wire.h>
#include <Servo.h>
// Note:  This sketch was basically created by cutting and pasting from the 
// arduino sketches supplied by each component manufacturer that 
// makes a component that plugs into the arduino.

// The Relays are connected to pins 2-5
int RelayPower = 2;
int Relay2 = 3;
int Relay3 = 4;
int Relay4 = 5;

// Color sensor is connected to - pins 6-10
int out=6;
int s0=7,s1=8,s2=9,s3=10;
int flag=0;
int counter=0;
int countR=0,countG=0,countB=0;
 

void setup() {
  Wire.begin();         // join i2c bus (address optional for master)
  Serial.begin(115200);  // start serial for output

  // set all four Relay pins to output mode
  pinMode(RelayPower, OUTPUT);
  pinMode(Relay2, OUTPUT);
  pinMode(Relay3, OUTPUT);
  pinMode(Relay4, OUTPUT);

  // set the pins for the color sensor to output mode
  pinMode(s0,OUTPUT);
  pinMode(s1,OUTPUT);
  pinMode(s2,OUTPUT);
  pinMode(s3,OUTPUT);

}

void toggleRelay(int RelayPin)
{
    // toggle the Relay on the pin passed as an argument
    digitalWrite(RelayPin, !digitalRead(RelayPin));
}

// timers to poll the color sensor
void TCS()
 {
 flag=0; 
 attachInterrupt(0, ISR_INTO, CHANGE);
 timer2_init();
 }
void ISR_INTO()
 {
   counter++;
 }
 void timer2_init(void)
 {
   TCCR2A=0x00;
   TCCR2B=0x07; //the clock frequency source 1024 points
   TCNT2= 100;    //10 ms overflow again
   TIMSK2 = 0x01; //allow interrupt
 }
 int i=0;
 ISR(TIMER2_OVF_vect)//the timer 2, 10ms interrupt overflow again. Internal overflow interrupt executive function
{
 TCNT2=100;
 if(flag==1)
  {
    countR=counter/1.051;
    digitalWrite(s2,HIGH); // setup for green
    digitalWrite(s3,HIGH);
    flag = 2;
  }
 else if(flag==2)
   {
    countG=counter/1.0157;
    digitalWrite(s2,LOW); // setup for blue
    digitalWrite(s3,HIGH);
    flag = 3;
   }
 else if(flag==3)
    {
    countB=counter/1.114;
    digitalWrite(s2,LOW); // setup for red
    digitalWrite(s3,LOW);
    flag = 4;
    }
 else if(flag==4)
    {
       // Serial.print("\n");
       // flag = 1;
     }
 else
     {
      flag=1;
     }
  counter=0;
}

void loop() {

  delay(10);
    
  if (Serial.available() > 0)
  {
    {
    // read a single character over serial
    int inByte = Serial.read();
 
    // do something different for each character
    switch (inByte)
      {       
          // toggle the respective Relay
          case 'p':
              toggleRelay(RelayPower);
              Serial.println("p");
              break;
           case 'f':
              toggleRelay(Relay4);
              Serial.println("f");
              break;   
              break;  
          default:
              // if we receive any other characters do nothing
              break;
      }
    } 
  } else {

    // poll the color sensor
    x = (((int)buff[1]) << 8) | buff[0];   
    y = (((int)buff[3])<< 8) | buff[2];
    z = (((int)buff[5]) << 8) | buff[4];
  
    //we send the x y z values as a string to the serial port
    sprintf(str, "%d %d %d", x, y, z);  
    Serial.print(str);

    TCS();
   
    Serial.print(" - r=");
    Serial.print(countR,DEC);
    Serial.print(" g=");
    Serial.print(countG,DEC);
    Serial.print(" b=");
    Serial.print(countB,DEC);
    if((countR>10)||(countG>10)||(countB>10))
    {
      if((countR>=countG + 10)&&(countR>=countB + 10))
      {
        Serial.print(" = r");
        delay(10);
      }
      else if((countG>=countR + 10)&&(countG>=countB + 10))
      {
         Serial.print(" = g");
         delay(10);
      }
      else if((countB>=countG + 10)&&(countB>=countR + 10))
      {
         Serial.print(" = b");
         delay(10);
       }
       else
       {
        Serial.print(" = w");
        delay(10);
       }  
    }
   
    Serial.print("\n");
    delay(1000); // update printing every second
  }
}

//---------------- Functions
//Writes val to address register on device
void writeTo(int device, byte address, byte val) {
  Wire.beginTransmission(device); //start transmission to device 
  Wire.write(address);        // send register address
  Wire.write(val);        // send value to write
  Wire.endTransmission(); //end transmission
}

//reads num bytes starting from address register on device in to buff array
void readFrom(int device, byte address, int num, byte buff[]) {
  Wire.beginTransmission(device); //start transmission to device 
  Wire.write(address);        //sends address to read from
  Wire.endTransmission(); //end transmission

    Wire.beginTransmission(device); //start transmission to device
  Wire.requestFrom(device, num);    // request 6 bytes from device

  int i = 0;
  while(Wire.available())    //device may send less than requested (abnormal)
  { 
    buff[i] = Wire.read(); // receive a byte
    i++;
  }
  Wire.endTransmission(); //end transmission
}
