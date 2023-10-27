#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

#include <ArduinoJson.h> // Inclua a biblioteca ArduinoJson

#include <SoftwareSerial.h>

#ifndef STASSID
#define STASSID "HOUSE_Ext"
#define STAPSK "53096883"
#endif

SoftwareSerial NodeMCU(D7, D8);
String data;

void setup() {

  Serial.begin(9600);
  NodeMCU.begin(9600);
  Serial.println();

  //Conexão WIFI com senha e rede
  WiFi.begin(STASSID, STAPSK);

  //Enquanto não houver conexão será impresso pontos
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.print("wifi conectado");
}



void loop() {
  WiFiClient client;

  //lendo a string enviada do Arduino
  delay(5000);
  data = NodeMCU.readString();
  Serial.println("RECEBIDO: "+data);

  //Conexão WIFI - Aguarda a conexão
  if (!data.isEmpty() && (WiFi.status() == WL_CONNECTED)) {


    HTTPClient http;
    Serial.print("Iniciando solicitação HTTP...\n");
    int httpCode = http.begin(client, "http://10.100.0.111:8080/tagsTemporary/create");  //HTTP
    http.addHeader("Content-Type", "application/json");

    httpCode = http.POST("{\"id\":\"\",\"codeTag\":\"" + data + "\"}");

    // httpCode quando da negativo é erro de solicitação
    if (httpCode > 0) {
      //O cabeçalho HTTP foi enviado e o cabeçalho de resposta do servidor foi tratado
      Serial.printf("Metodo POST... código: %d\n", httpCode);
      Serial.println(httpCode);
      String payload = http.getString();

      if(payload != "" && payload != "null" && payload != "undefined"){
        Serial.println("Payload:");
        Serial.println(payload);  

          //Resposta encontrada no servidor
        if (httpCode == HTTP_CODE_OK) {
          Serial.printf("Metodo POST Funcionou");
          NodeMCU.write(15);
        } else{
          if (httpCode == HTTP_CODE_UNAUTHORIZED) {
            NodeMCU.write(10);
          }
        }
        
      }else{
        Serial.println("Não veio payload");

        int httpCode2 = http.begin(client, "http://10.100.0.111:8080/user/getUserCodeTag?codigoTag=" + data);
        http.addHeader("Content-Type", "application/json");

        httpCode2 = http.GET();

        Serial.println(httpCode2);
        String payload = http.getString();
        Serial.println(payload);

        if(payload != "" && payload != "null" && payload != "undefined"){
          DynamicJsonDocument doc(1024); 
          DeserializationError error = deserializeJson(doc, payload);

          if (!error) {
            String nome = doc["nome"];
            String sobrenome = doc["sobrenome"];

            Serial.println("Nome: " + nome);
            Serial.println("Sobrenome: " + sobrenome);
          } else {
            Serial.print("Erro ao analisar o JSON: ");
            Serial.println(error.c_str());
          }

          NodeMCU.write(16);
        }else{
          NodeMCU.write(11);
        }

        
      }
      

    } else {
      Serial.printf("Metodo POST falhou, erro: %s\n", http.errorToString(httpCode).c_str());
      NodeMCU.write(0);
    }
    http.end();
    delay(1000);
  }

}