#include <Servo.h>

Servo servo;

int angulo;

void setup() {
  servo.attach(2);
  angulo = 130;
  servo.write(angulo);
}

void loop() {
  
  delay(5000);
  if(angulo == 130){
    angulo = 0;
  }else{
    angulo = 130;
  }

  servo.write(angulo);

}
