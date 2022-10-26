#include <Servo.h>
#include <FastLED.h>

Servo door;
CRGB samosvalLed[1];
CRGB policeLed[1];
CRGB svetofor[2];
CRGB help1[1];
CRGB help2[1];

void startFan() {
  digitalWrite(12, 0);
}

void stopFan() {
  digitalWrite(12, 1);
}

void samosvalLedBlink(int n) {
  for (int i = 0; i < n; i++) {
    for (int k = 0; k < 255; k += 3) {
      samosvalLed[0] = CRGB(k, k / 2, 0);
      FastLED.show();
    }
    for (int k = 255; k >= 0; k -= 3) {
      samosvalLed[0] = CRGB(k, k / 2, 0);
      FastLED.show();
    }
  }
}


void policeLedBlink(int n) {
  for (int i = 0; i < n; i++) {
    for (int k = 0; k < 255; k += 3) {
      policeLed[0] = CRGB(k, 0, 0);
      FastLED.show();
    }
    for (int k = 255; k >= 0; k -= 3) {
      policeLed[0] = CRGB(k, 0, 0);
      FastLED.show();
    }
    for (int k = 0; k < 255; k += 3) {
      policeLed[0] = CRGB(0, 0, k);
      FastLED.show();
    }
    for (int k = 255; k >= 0; k -= 3) {
      policeLed[0] = CRGB(0, 0, k);
      FastLED.show();
    }
  }
}


void samosvalActive() {
  digitalWrite(11, 1);
  digitalWrite(10, 0);
  samosvalLedBlink(4);
  digitalWrite(11, 0);
  digitalWrite(10, 0);
  for (int k = 0; k < 255; k += 3) {
    samosvalLed[0] = CRGB(k, k / 2, 0);
    FastLED.show();
  }
}

void samosvalCancel() {
  digitalWrite(11, 0);
  digitalWrite(10, 1);
  samosvalLedBlink(4);
  digitalWrite(11, 0);
  digitalWrite(10, 0);
  samosvalLed[0] = CRGB(0, 0, 0);
  FastLED.show();
}

void policeActive() {
  door.write(180);
  digitalWrite(9, 0);
  digitalWrite(8, 1);
  policeLedBlink(5);
  digitalWrite(9, 0);
  digitalWrite(8, 0);
}

void policeCancel() {
  policeLed[0] = CRGB(0, 0, 0);
  FastLED.show();
  digitalWrite(8, 0);
  digitalWrite(9, 1);
  delay(5000);
  digitalWrite(9, 0);
  digitalWrite(8, 0);
  door.write(100);
}

bool col = 0;
bool napravl = 0;

void gotoLeft(int n, bool light) {
  digitalWrite(6, 1);
  for (int i = 0; i < n; i++) {
    digitalWrite(7, 1);
    delay(15);
    digitalWrite(7, 0);
    delay(15);
    if (i % 15 == 0 and light) {
      if (col) {
        help1[0] = CRGB(255, 255, 0);
        help2[0] = CRGB(255, 255, 0);
      } else {
        help1[0] = CRGB(0, 0, 0);
        help2[0] = CRGB(0, 0, 0);
      }
      FastLED.show();
      col = !col;
    }
  }
}

void gotoRight(int n, bool light) {
  digitalWrite(6, 0);
  for (int i = 0; i < n; i++) {
    digitalWrite(7, 1);
    delay(15);
    digitalWrite(7, 0);
    delay(15);
    if (i % 15 == 0 and light) {
      if (col) {
        help1[0] = CRGB(255, 255, 0);
        help2[0] = CRGB(255, 255, 0);
      } else {
        help1[0] = CRGB(0, 0, 0);
        help2[0] = CRGB(0, 0, 0);
      }
      FastLED.show();
      col = !col;
    }
  }
}

void people() {
  if (!napravl) {
    svetofor[0] = CRGB(255, 255, 0);
    svetofor[1] = CRGB(255, 255, 0);
    FastLED.show();
    gotoRight(110, true);
    delay(1000);
    svetofor[0] = CRGB(255, 0, 0);
    svetofor[1] = CRGB(255, 0, 0);
    FastLED.show();
    delay(1000);
    gotoRight(290, true);
    help1[0] = CRGB(0, 0, 0);
    help2[0] = CRGB(0, 0, 0);
    svetofor[0] = CRGB(255, 255, 0);
    svetofor[1] = CRGB(255, 255, 0);
    FastLED.show();
    gotoRight(110, false);
    svetofor[0] = CRGB(0, 255, 0);
    svetofor[1] = CRGB(0, 255, 0);
    FastLED.show();
  } else {
    svetofor[0] = CRGB(255, 255, 0);
    svetofor[1] = CRGB(255, 255, 0);
    FastLED.show();
    gotoLeft(110, true);
    delay(1000);
    svetofor[0] = CRGB(255, 0, 0);
    svetofor[1] = CRGB(255, 0, 0);
    FastLED.show();
    delay(1000);
    gotoLeft(290, true);
    help1[0] = CRGB(0, 0, 0);
    help2[0] = CRGB(0, 0, 0);
    svetofor[0] = CRGB(255, 255, 0);
    svetofor[1] = CRGB(255, 255, 0);
    FastLED.show();
    gotoLeft(110, false);
    svetofor[0] = CRGB(0, 255, 0);
    svetofor[1] = CRGB(0, 255, 0);
    FastLED.show();
  }
  napravl = !napravl;
}

void park() {
  stopFan();
  samosvalCancel();
  door.attach(5);
  door.write(180);
  policeCancel();
  gotoLeft(510, false);
  svetofor[0] = CRGB(0, 255, 0);
  svetofor[1] = CRGB(0, 255, 0);
  FastLED.show();
}

void setup() {
  Serial.begin(9600);
  Serial.println("ID: Motors");
  FastLED.addLeds<WS2812B, 4, GRB>(samosvalLed, 1).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<WS2812B, 3, GRB>(policeLed, 1).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<WS2812B, A0, GRB>(svetofor, 2).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<WS2812B, A1, GRB>(help1, 1).setCorrection(TypicalLEDStrip);
  FastLED.addLeds<WS2812B, A2, GRB>(help2, 1).setCorrection(TypicalLEDStrip);
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(12, OUTPUT);
  FastLED.setBrightness(60);
  svetofor[0] = CRGB(252, 116, 253);
  svetofor[1] = CRGB(252, 116, 253);
  FastLED.show();
}

void loop() {
  if (Serial.available()) {
    int act = Serial.parseInt();
    if (act != 0) {
      if (act == -1) {
        park();
      } else if (act == 1) {
        startFan();
      } else if (act == 2) {
        stopFan();
      } else if (act == 3) {
        people();
      } else if (act == 4) {
        samosvalActive();
        delay(3000);
        samosvalCancel();
      } else if (act == 5) {
        policeActive();
        policeLedBlink(10);
        policeCancel();
      }

      Serial.println("OK");
    }
  }
}