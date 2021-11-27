    import Exceptions.WrongFormatException;
    import Exceptions.WrongValueException;

    import java.math.BigDecimal;
    import java.math.MathContext;
    import java.math.RoundingMode;
    import java.util.*;

    /**
     * Klasa zawierająca nazwę oraz cenę produktu
     */
    public class Product {

    String name;
    BigDecimal price;
    public Product(String name, BigDecimal price) throws WrongValueException,WrongFormatException{
        setName(name);
        setPrice(price);
    }
    public String getName() {
        return name;
    }
    public void setName(String name)throws WrongFormatException{
        if(name ==null || name.equals("")) throw new WrongFormatException();
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws WrongValueException,WrongFormatException{
        if(price == null) throw new WrongValueException();
        //sprawdzenie czy cena jest prawidłowa
        try{
            price.setScale(2);
            if(price.doubleValue()<=0) throw new WrongValueException();
            this.price=price;
        }catch (ArithmeticException e){
            throw new WrongFormatException();
        }
    }

        /**
         * Metoda licząca wartości rabatów dla poszczególnych produktów
         * @param list lista produktów do których ma być policzony rabat
         * @param discount wielkość rabatu
         * @return listę obiektów Result czyli wielkości rabatów wraz z nazwami produktów.
         * @throws WrongValueException kiedy rabat jest większy niż suma cen lub ujemny
         */
    static public List<Result> calculateDiscount(List<Product> list,BigDecimal discount) throws WrongValueException{
       if(list== null || discount == null) throw new WrongValueException();
       if(discount.doubleValue()<0) throw new WrongValueException();
       int size = list.size();
        List resultList = new ArrayList();
        if(discount.compareTo(BigDecimal.valueOf(0))==0){
            for(int i =0; i < size; i++){

            resultList.add(new Result(list.get(i).getName(),0.00));
            }
            return resultList;
        }
       BigDecimal sum = new BigDecimal(0.00);
       sum.setScale(2);
       for(int i =0; i < size; i++){
           sum =sum.add(list.get(i).getPrice());
       }
       if(sum.doubleValue()<discount.doubleValue()) throw new WrongValueException();
       BigDecimal percent = discount.divide(sum,2, RoundingMode.DOWN);
       double sumWithDiscount = 0.00;
        for(int i =0;i<size;i++){
            double val = list
                            .get(i)
                            .getPrice()
                            .multiply(percent)
                    .round(new MathContext(3,RoundingMode.DOWN))
                            .doubleValue();
            sumWithDiscount+=val;
            if(i ==size-1){
                double rest = discount.subtract(BigDecimal.valueOf(sumWithDiscount)).doubleValue();
                val = val +rest;
                resultList.add(new Result(list.get(i).getName(),val));
                break;
            }
            resultList.add(new Result(list.get(i).getName(),val));
        }
        return resultList;
    }
}
