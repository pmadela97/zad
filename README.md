# zad
Dokumentacja

Założenia:
Ceny nie mogą być ujemne ani zerowe.
Rabat nie może być większy niż suma cen produktów oraz nie może być mniejszy niż 0.

Opis:
Program składa się z 6 klas.
WrongFormatException - klasa rozszerzająca klasę Exception to rzucania błędnych wartości cen i rabatu np. -4
WrongValueException - klasa rozszerzająca klasę Exception to rzucania błędnych formatów cen i rabatu np. 2.223
Main – klasa główna w której tworzone są obiekty oraz są pętle zapewniające działanie programu.
Product – klasa zawierająca pole nazwy produktu oraz jego ceny. 
Result – klasa zawierające nazwę oraz wartość obliczonego rabatu dla danego produktu
UserInterface – klasa zawierająca metody pobierające od użytkownika w konsoli odp wartości i pokazująca wynik działania programu.

Działanie:
Program jest aplikacją konsolową. Użytkownik na początku ustala liczbę produktów i ją wpisuje. Następnie podaje nazwy produktów oraz ich ceny.
Kolejnym etapem jest wpisanie wielkości rabatu . Po wpisaniu danych program zwraca wynik w postaci listy obiektu Result i wyświetla je.
Główną funkcją jest statyczna metoda calculateDiscount klasy Product do której podawana jest lista produktów z ich cenami oraz wartość obniżki. Metoda oblicza odpowiednie rabaty i zwraca wynik w postaci listy objektów klasy Result.
