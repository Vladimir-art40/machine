#define MOT_A_STEP 3
#define MOT_A_DIR 2
#define MOT_B_STEP 4
#define MOT_B_DIR 5
#define MOT_C_STEP 6
#define MOT_C_DIR 7

int x = 0, y = 10000, z = 0;

void stepX(int n) {
  digitalWrite(MOT_A_DIR, n);
  digitalWrite(MOT_B_DIR, n);

  digitalWrite(MOT_A_STEP, 1);
  digitalWrite(MOT_B_STEP, 1);
  delayMicroseconds(359);
  digitalWrite(MOT_A_STEP, 0);
  digitalWrite(MOT_B_STEP, 0);
  delayMicroseconds(359);
}

void stepY(int n) {
  digitalWrite(MOT_A_DIR, 1 - n);
  digitalWrite(MOT_B_DIR, n);

  digitalWrite(MOT_A_STEP, 1);
  digitalWrite(MOT_B_STEP, 1);
  delayMicroseconds(352);
  digitalWrite(MOT_A_STEP, 0);
  digitalWrite(MOT_B_STEP, 0);
  delayMicroseconds(352);
}

void stepDrone(int n) {
  digitalWrite(MOT_C_DIR, n);

  digitalWrite(MOT_C_STEP, 1);
  delayMicroseconds(352);
  digitalWrite(MOT_C_STEP, 0);
  delayMicroseconds(352);
}

void park() {
  for (int i = 0; i < 1200; i++) {
    stepDrone(0);
  }
  for (int i = 0; i < 26000; i++) {
    stepY(0);
  }
  for (int i = 0; i < 10000; i++) {
    stepY(1);
  }
  for (int i = 0; i < 27000; i++) {
    stepX(0);
  }
  for (int i = 0; i < 2000; i++) {
    stepX(1);
  }
  x = 0;
  y = 10000;
  z = 0;
}

int xMax = 25000, yMax = 26000, zMax = 5000;

void setup() {
  Serial.begin(9600);
  Serial.println("ID: Up");
  pinMode(MOT_A_STEP, OUTPUT);
  pinMode(MOT_A_DIR, OUTPUT);
  pinMode(MOT_B_STEP, OUTPUT);
  pinMode(MOT_B_DIR, OUTPUT);
  pinMode(MOT_C_STEP, OUTPUT);
  pinMode(MOT_C_DIR, OUTPUT);
}

void loop() {
  if (Serial.available()) {
    int xN = Serial.parseInt();
    int yN = Serial.parseInt();
    int zN = Serial.parseInt();

    bool ok = true;
    if (xN == -1 and yN == -1 and zN == -1) {
      park();
      Serial.println("OK");
      ok = false;
    }

    if (xN > xMax or yN > yMax or zN > zMax) {
      Serial.println("OK");
      Serial.println("ERR");
      ok = false;
    }
    if (xN == 0 or yN == 0 or zN == 0) {
      Serial.println("OK");
      Serial.println("ERR");
      ok = false;
    }

    if (ok) {
      if (zN > z) {
        for (int i = 0; i < zN - z; i++) {
          stepDrone(0);
        }
      } 

      if (xN > x) {
        for (int i = 0; i < xN - x; i++) {
          stepX(1);
        }
      } else {
        for (int i = 0; i < x - xN; i++) {
          stepX(0);
        }
      }

      if (yN > y) {
        for (int i = 0; i < yN - y; i++) {
          stepY(1);
        }
      } else {
        for (int i = 0; i < y - yN; i++) {
          stepY(0);
        }
      }

      if(z > zN) {
        for (int i = 0; i < z - zN; i++) {
          stepDrone(1);
        }
      }
      
      x = xN;
      y = yN;
      z = zN;
      Serial.println("OK");
      
    }
    Serial.print("Recived: ");
      Serial.print(x);
      Serial.print(" ");
      Serial.print(y);
      Serial.print(" ");
      Serial.println(z);
  }
}