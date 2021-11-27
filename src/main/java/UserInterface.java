import Exceptions.WrongFormatException;
import Exceptions.WrongValueException;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Ta klasa umożliwia dostarczenie odpowiednich danych potrzebnych do wykonania programu.
 */
public class UserInterface {

    private Scanner sc;

    public UserInterface() {
        sc = new Scanner(System.in);
    }

    /**
     * Ta metoda pobiera za pomocą klasy Scanner cenę produktu
     * @return wartość produktu
     * @throws WrongValueException kiedy użytkownik wpisuje błędną cenę(<=0 lub w nieodpowiedniej skali np. 12.223)
     */
    BigDecimal getPrice() throws WrongValueException,WrongFormatException{
        BigDecimal value;
        if(sc.hasNext()) {
        String val = sc.next();
            if (val == null) throw new WrongValueException();
            try{
            value = BigDecimal.valueOf(Double.valueOf(val));

                value.setScale(2);
            }catch (ArithmeticException e){
                throw new WrongFormatException();
            }catch (NumberFormatException e){
                throw  new WrongFormatException();
            }
            if(value.doubleValue()<0)throw new WrongValueException();
        }else{
            throw new NullPointerException();
        }
        return value;
    }

    /**
     * Metoda pobiera nazwę produktu
     * @return zwara nazwę produktu
     * @throws WrongValueException
     */
    String getName() throws WrongValueException{
        if(sc.hasNext()){
            String r = sc.next();
            if(r.equals("")) throw new WrongValueException();
            return r;
        }else{
            throw new WrongValueException();
        }
    }

    /**
     * Metoda pokazuje wiadomość w consoli
     * @param m treść wiadomości
     */
    void printMessage(String m){
        System.out.println(m);
    }

    /**
     * Metoda za pomocą klasy Scanner pobiera ilość produktów od 1 do 5
     * @return wartość produktów
     * @throws WrongValueException kiedy wpisywana ilość jest nieprawidłowa np.-4
     * @throws WrongFormatException kiedy format jest zły np 4.0
     */
    int getCount()throws WrongValueException,WrongFormatException{
        int result;
        if(sc.hasNextInt()){
            result = sc.nextInt();
            if(result<=0|| result>5) throw new WrongValueException();
        }else{
            sc.next();
            throw new WrongFormatException();
        }
        return result;
    }

    /**
     * Metoda wyświetla wiadomość jako błąd
     * @param m przekazana wiadomość
     */
    void printError(String m){
        System.err.println(m);
    }
}
