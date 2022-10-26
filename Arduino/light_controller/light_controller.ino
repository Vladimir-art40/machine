#include <FastLED.h>
CRGB drone[8];
void setup() {
  pinMode(5, OUTPUT);
  digitalWrite(5, 1);
  Serial.begin(9600);
  Serial.println("ID: Light");
  FastLED.addLeds<WS2812B, 2, GRB>(drone, 8);
}

void policeBlink() {
  for (int n = 0; n < 2; n++) {
    for (int i = 0; i < 255; i += 3) {
      drone[(n) % 8] = CRGB(i, 0, 0);
      drone[(n + 2) % 8] = CRGB(i, 0, 0);
      drone[(n + 4) % 8] = CRGB(i, 0, 0);
      drone[(n + 6) % 8] = CRGB(i, 0, 0);

      drone[(n + 1) % 8] = CRGB(0, 0, i);
      drone[(n + 3) % 8] = CRGB(0, 0, i);
      drone[(n + 5) % 8] = CRGB(0, 0, i);
      drone[(n + 7) % 8] = CRGB(0, 0, i);
      FastLED.show();
    }
    for (int i = 255; i >= 0; i -= 3) {
      drone[(n) % 8] = CRGB(i, 0, 0);
      drone[(n + 2) % 8] = CRGB(i, 0, 0);
      drone[(n + 4) % 8] = CRGB(i, 0, 0);
      drone[(n + 6) % 8] = CRGB(i, 0, 0);

      drone[(n + 1) % 8] = CRGB(0, 0, i);
      drone[(n + 3) % 8] = CRGB(0, 0, i);
      drone[(n + 5) % 8] = CRGB(0, 0, i);
      drone[(n + 7) % 8] = CRGB(0, 0, i);
      FastLED.show();
    }
  }
}

void airBlink() {
  for (int a = 0; a < 8; a++) {
    for (int i = 0; i < 255; i += 4) {
      int ia = map(i, 0, 255, 255, 255 / 2);
      int ib = map(i, 0, 255, 255 / 2, 255 / 4);
      int ic = map(i, 0, 255, 255 / 4, 255 / 8);
      int id = map(i, 0, 255, 0, 255);
      drone[(a + 0) % 8] = CRGB(0, 0, id);
      drone[(a + 1) % 8] = CRGB(0, 0, ic);
      drone[(a + 2) % 8] = CRGB(0, 0, ib);
      drone[(a + 3) % 8] = CRGB(0, 0, ia);
      drone[(a + 4) % 8] = CRGB(0, 0, id);
      drone[(a + 5) % 8] = CRGB(0, 0, ic);
      drone[(a + 6) % 8] = CRGB(0, 0, ib);
      drone[(a + 7) % 8] = CRGB(0, 0, ia);
      FastLED.show();
    }
  }
}

int mode = 0;
void loop() {
  if (mode == 1)
    policeBlink();
  if (mode == 2)
    airBlink();
  if (Serial.available()) {
    int nM = Serial.parseInt();
    if (nM != 0) {
      if (nM == 100)
        digitalWrite(5, 1);
      else if (nM == 101)
        digitalWrite(5, 0);
      else
        mode = nM;
      Serial.println("OK");
    }
  }
}