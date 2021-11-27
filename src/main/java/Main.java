import Exceptions.WrongFormatException;
import Exceptions.WrongValueException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args)  {
        UserInterface userInterface = new UserInterface();
        boolean end = false;
        int count = 0;
        List<Result> resultList = null;
        //wpisanie wartośći
        while(!end){
            userInterface.printMessage("Wpisz ilość produktów od 1 do 5: ");
            try{
                count= userInterface.getCount();
                System.err.println(count);
                end = true;
            }catch (WrongFormatException e){
                userInterface.printError("Nieprawidłowy format!!!");
            }catch (WrongValueException e){
                userInterface.printError("Nieprawidłowa wartość!!!");

            }
        }
        end = false;
        BigDecimal discount = null;
        List<Product> list = new ArrayList<>();
        //Utworzenie produktów i wpisanie nazw i cen
        for(int i = 0; i <count;i++){
            String name = null;
            userInterface.printMessage("Wpisz nazwę produktu nr "+(1+i));
            try {
                 name = userInterface.getName();

            }catch (WrongValueException e){
                userInterface.printError("Nieprawidłowa nazwa!!!");
            }
            while(!end){
                try {
                    userInterface.printMessage("Wpisz cenę produktu nr "+ (1+i));
                    list.add(new Product(name,userInterface.getPrice()));
                    end = true;
                }catch (WrongValueException e){
                    userInterface.printError("Nieprawidłowa wartość ceny!!!");
                }catch (WrongFormatException e){
                    userInterface.printError("Nieprawidłowy format ceny!!!");
                }
            }
            end = false;
        }
        end = false;
        boolean next = false;
        //Przeliczenie rabatów
        while(!end){
            try {
                userInterface.printMessage("Wpisz wartość rabatu.");
                discount = userInterface.getPrice();
                next=true;
            }catch (WrongValueException e){
                userInterface.printError("Nieprawidłowa wartość rabatu");
            }catch (WrongFormatException e){
                userInterface.printError("Nieprawidłowy format rabatu");
            }
            if(next) {
                try {
                    resultList =Product.calculateDiscount(list, discount);
                    end = true;
                } catch (WrongValueException e) {
                    userInterface.printError("Rabat musi być mniejszy niż suma wartości produktów!!!");
                }
            }

        }
    for(int i =0; i<count;i++){
        userInterface.printMessage(resultList.get(i).getName() +" "+resultList.get(i).getDiscount());
    }

    }
}
