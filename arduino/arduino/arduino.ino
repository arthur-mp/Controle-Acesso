#include <SPI.h> //biblioteca serial
#include <MFRC522.h> //biblioteca do RFID
#include <Wire.h>
#include <SoftwareSerial.h>

#include <Servo.h> //biblioteca do servo motor

#define SS_PIN 10 //PINO SDA
#define RST_PIN 9 //PINO DE RESET

SoftwareSerial ArduinoUno(7,8);
MFRC522 rfid(SS_PIN, RST_PIN); //cria a instancia da biblioteca

int st;
String data;

Servo servo;
int angulo;

int pinosensor = 6;   //Ligado ao pino "coletor" do sensor óptico 
int leitura;      //Armazena informações sobre a leitura do sensor  

const int pinoLedVerde = 3; //PINO DIGITAL REFERENTE AO LED VERDE
const int pinoLedVermelho = 4; //PINO DIGITAL REFERENTE AO LED VERMELHO

void setup() {
  Serial.begin(9600);
  ArduinoUno.begin(9600);
  
  SPI.begin();
  rfid.PCD_Init(); //inicia rfid

  servo.attach(2);
  angulo = 130;
  servo.write(angulo);

  pinMode(pinosensor, INPUT);   //Define o pino do sensor como entrada

  pinMode(pinoLedVerde, OUTPUT); //DEFINE O PINO COMO SAÍDA
  pinMode(pinoLedVermelho, OUTPUT); //DEFINE O PINO COMO SAÍDA
  
  digitalWrite(pinoLedVerde, LOW); //LED INICIA DESLIGADO
  digitalWrite(pinoLedVermelho, LOW); //LED INICIA DESLIGADO
}

void loop() {
  if (!rfid.PICC_IsNewCardPresent() || !rfid.PICC_ReadCardSerial()) //VERIFICA SE O CARTÃO PRESENTE NO LEITOR É DIFERENTE DO ÚLTIMO CARTÃO LIDO. CASO NÃO SEJA, FAZ
    return; //RETORNA PARA LER NOVAMENTE

  //UID da tag na porta serial
  Serial.print("UID da tag: ");
  String conteudo;
  String conteudo2 = "";
  
  //Descodificando o ID da tag
  for(byte i = 0; i<rfid.uid.size; i++){
    conteudo.concat(String(rfid.uid.uidByte[i], HEX));
  }
  
  Serial.println(conteudo);

  //Evita que ao manter a tag no sensor ele envie a mesma tag varias vezes  
  while (conteudo != conteudo2){
    conteudo2 = conteudo;

    //transforma a string em char para enviar pela porta serial
    char* pt1 = (char*) malloc(sizeof(char)*conteudo.length()+1);
    conteudo.toCharArray(pt1, conteudo.length() + 1);

    //escreve na porta serial o char*
    ArduinoUno.write(pt1);
    Serial.print(pt1);
    Serial.println();


    //recebe o resultado da busca no banco
    delay(5000);
    st = ArduinoUno.read();
    while(st == -1){
      st = ArduinoUno.read();
    }
    Serial.println(st);

    if(st == 15){
      Serial.println("Cadastrou nova tag");
      notificaViaLed(pinoLedVerde);
    }else{
      if(st == 10){
        Serial.println("Falha para cadastrar nova tag");
        notificaViaLed(pinoLedVermelho);
      }else{
        if(st == 16){
          Serial.println("Acesso liberado");
          notificaViaLed(pinoLedVerde);
          angulo = 0;
          servo.write(angulo);

          delay(5000);
          Serial.println("Verificar sensor");

          //Le as informações do pino do sensor
          leitura = digitalRead(pinosensor); 
          if(leitura == 0){
            
            while(digitalRead(pinosensor) == 0)  
            {  
            Serial.println("Objeto detectado no sensor");
            delay(5000);  
            }  
          }else{
            Serial.println("Objeto não detectado no sensor");
          }

          angulo = 130;
          servo.write(angulo);

        }else{
          Serial.println("Acesso negado");
          notificaViaLed(pinoLedVermelho);
        }
      }
    }

  }

}

void notificaViaLed(int color){
  digitalWrite(color, HIGH); //LIGA O LED VERDE
  delay(3000); //INTERVALO DE 4 SEGUNDOS
  digitalWrite(color, LOW); //DESLIGA O LED VERDE
  delay(3000); //INTERVALO DE 4 SEGUNDOS
  digitalWrite(color, HIGH); //LIGA O LED VERDE
  delay(3000); //INTERVALO DE 4 SEGUNDOS
  digitalWrite(color, LOW); //DESLIGA O LED VERDE
}
