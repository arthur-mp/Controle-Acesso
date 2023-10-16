#include <SPI.h> //biblioteca serial
#include <MFRC522.h> //biblioteca do RFID
#include <Wire.h>
#include <SoftwareSerial.h>

#define SS_PIN 10 //PINO SDA
#define RST_PIN 9 //PINO DE RESET

SoftwareSerial ArduinoUno(7,8);
MFRC522 rfid(SS_PIN, RST_PIN); //cria a instancia da biblioteca

int st;
String data;

void setup() {
  Serial.begin(9600);
  ArduinoUno.begin(9600);
  
  SPI.begin();
  rfid.PCD_Init(); //inicia rfid

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
    Serial.println(st);

    if(st == 15){
        
        Serial.println("Acesso Liberado");

      }

       if(st == 10){
        
        Serial.println("Acesso Negado");

       }

  }

}
