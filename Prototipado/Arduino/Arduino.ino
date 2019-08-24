//Jhonny Lopez 
//Proyecto ODIN APP, Programacion de Sitemas Telematicos
#include <Servo.h>//importamos la libreria para el monejo de los servo motores ,tanto de la puerta como del seguro
int buttonState = 0;//Variable para manejar los estados de los sensores de movimiento 
int opciones;//opciones ingresadas por puerto serial para realizar la apertura-seguro
int pos_puerta;
Servo servo_puerta,servo_seguro;//objetos servo asignados a cada uno de los motores del sistema
void setup()
{
  pinMode(3, INPUT);//pin asignado a la lectura del sensor externo
  pinMode(4, INPUT);//Pin asignado al sensor interno
  servo_puerta.attach(9);//pin de salida para el manejo del servo motor de la puerta
  servo_seguro.attach(10);//pin de salida para el manejo del seguro de la puerta
  servo_seguro.write(60);//Asignamos una posicion inicial del seguro de la puerta
  servo_puerta.write(75);//Asignamos una posicion inicial del seguro de la puerta
  
  Serial.begin(9600);//Iniciamos la comunicacion serial 
}

void loop()
{
   SensorMovimientoExterno ();//Iniciamos la deteccion de movimiento del sensor externo
   SensorMovimientoInterno ();//Iniciamos la deteccion de movimiento del sensor interno
  if(Serial.available()>0){//validamos la entrada de datos al puerto serial , si existe deteccion se procede a analizar el ingreso
    opciones=Serial.read();
    if(opciones=='a'){//Secuencia de apertura de la puerta
      seguro(false);
      delay(2000);
      puerta(true);
    }
    if(opciones=='b'){//secuencia de cerrado de la puerta
      puerta(false);
      delay(2000);
      seguro(true);
    }
  }
}
void puerta(boolean r){//funcion que maneja la posicion del motor que controla la puerta
  if(r==true){
    for(pos_puerta=80;pos_puerta>=0;pos_puerta-=1){
    servo_puerta.write(pos_puerta);
    delay(15);
    }
  }
  else{
    for(pos_puerta=0;pos_puerta<=80;pos_puerta+=1){
    servo_puerta.write(pos_puerta);
    delay(15);}
    }
}
void seguro(boolean r){//funcion de maneja la posicon del motor que controla el seguro de la puerta 
  if(r==true){
    servo_seguro.write(60);
  }
  else{
    servo_seguro.write(20);
    }
}
void SensorMovimientoExterno (){//funcion de imprime en el monitor serial el estado de deteccion del sensor externo
  buttonState =LOW;
  buttonState = digitalRead(3);
  delay(50);
  if (buttonState == HIGH) {
    delay(1000);
    Serial.println("externo_prendido");
    
  } else if(buttonState == LOW){
    Serial.println("externo_apagado");
    delay(1000);
  }
}

void SensorMovimientoInterno (){//funcion de imprime en el monitor serial el estado de deteccion del sensor interno
  buttonState =LOW;
  buttonState = digitalRead(3);
  delay(50);
  if (buttonState == HIGH) {
    delay(1000);
    Serial.println("interno_prendido");
    
  } else if(buttonState == LOW){
    Serial.println("interno_apagado");
    delay(1000);
  }
}
