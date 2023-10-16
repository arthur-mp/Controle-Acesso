#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

#include <SoftwareSerial.h>

#ifndef STASSID
#define STASSID "SUPRANET"
#define STAPSK "18112006"
#endif

String data;

void setup() {

  Serial.begin(9600);
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


  //Conexão WIFI - Aguarda a conexão
  if ((WiFi.status() == WL_CONNECTED)) {


    HTTPClient http;
    Serial.print("Iniciando solicitação HTTP...\n");
    int httpCode = http.begin(client, "http://192.168.1.17:8080/veiculo/getAll");  //HTTP

    // Configura a URL e o servidor recebido

    http.addHeader("Content-Type", "application/json");

    httpCode = http.GET();


    // httpCode quando da negativo é erro de solicitação
    if (httpCode > 0) {
      //O cabeçalho HTTP foi enviado e o cabeçalho de resposta do servidor foi tratado
      Serial.printf("Metodo GET... código: %d\n", httpCode);
      Serial.println(httpCode);
      String payload = http.getString();
      Serial.println(payload);

      //Resposta encontrada no servidor
      if (httpCode == HTTP_CODE_OK) {
        Serial.printf("Metodo GET Funcionou");
      }

    } else {
      Serial.printf("Metodo GET falhou, erro: %s\n", http.errorToString(httpCode).c_str());
    }
    http.end();
    delay(1000);
  }

}