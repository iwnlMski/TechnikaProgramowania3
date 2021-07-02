# Technika Programowania 3
![alt text](https://github.com/iwnlMski/TechnikaProgramowania3/blob/master/readmeimg/TP3image1.PNG?raw=true)

### Projekt numer 3
### Autor projektu: Seweryn Majewski ACiR WETI 2sem. Indeks: 181675
### Użyta technologia: Java 

### Dokumentacja:
### 1. Działanie programu: 
Celem programu jest wizualizajca odczytów z żyroskopu umieszczonego na robocie. Program
jest podzielony na 3 klasy w celu rozdzielenia funkcjonalności I utrzymania SOLID zasad
programowania. 

#### a. ReadFile.java
W tej klasie program wczytuje plik tekstowy z podanej ścieżki, a następnie czyści I
przygotowuje dane do przekazania ich dalej. Funkcja **getData(int linesToSkip)**, jest
wywoływana tylko raz podczas życia programu, I wymaga argumentu
specyfikującego ile pierwszych linii danych pominąć. 

#### b. MyForm.java
Jest to klasa implementująca elementy GUI, oraz jednocześnie pełni funkjcę klasy
main. Poza implementacją elementów interfejsu graficznego, definiuje również ich
zachowanie w poszczególnych scenariuszach użytkowania. Bowiem
zaimplementowane są tak zwane “Listenery” czekające na input z przycisków,
niektóre funkcje walidujące wejście użytkownika oraz jedna z ważniejszych funkcji
programu: **runGraph()**, która nie przyjmuję żadnych argumentów I jest wywołyana
przy wciśnięciu odpowiedniego przycisku. 

#### c. GraphicsDemo.java
Jest to klasa implemetująca wszelkie aspekty rysowania 2D w programie, w której
konstruktor kontroluje tworzenie I otrzymywanie danych oraz odświeżanie ekranu.
Poza konstruktorem zaimplementowałem funkcję pomocniczną **createPoints(x_r,
y_r, dist)** przyjmującą 3 argumenty typu Double. Celem funkcji jest przekalkulowanie
punktów na podstawie danych wydobytych z pliku tekstowego. Punkty kalkulowane
są na podstawie podstawowych działań matematycznych(funkcje trygonometryczne
I trochę algebry linowej). Ostatnią funkcją w tej klasie jest
**paintComponent(Graphics g)**, jest to funkcja dziedziczona z klasy poziom wyżej w
hierarchii dziedziczenia, która w abstrakcyjnym tłumaczeniu, rysuje nasz wykres na
podstawie punktów zdefiniowanych w scopie publicznym. 

### 2. GUI
![alt text](https://github.com/iwnlMski/TechnikaProgramowania3/blob/master/readmeimg/TP3image2.PNG?raw=true)
#
Po włączeniu programu jedynym włączonym przyciskiem jest przycisk “Submit”, który
przekazuję do programu liczbę wpisaną obok. Liczba ta jest liczbą linii pliku które chcemy
pominąć. 
#
![alt text](https://github.com/iwnlMski/TechnikaProgramowania3/blob/master/readmeimg/TP3image3.PNG?raw=true)
#
Po wpisaniu poprawnej wartości podśwetlają się dodatkowe przyciski. W tym momencie
możemy dostosować szybkośc symulacji oraz przybliżenie wykresu. Warto dodać iż gdy
klikniemy “Run”, nie będziemy w stanie tego już zmienić aż do zakończenia symulacji albo
zakończenia programu. 

