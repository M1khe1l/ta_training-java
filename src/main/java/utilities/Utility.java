package utilities;

public class Utility {

    public static double parseDoubleFromString(String string)
    {
        String rawPrice = string.replaceAll("[^0-9.,]", "");
        rawPrice = rawPrice.replaceAll(",", ".");
        return Double.parseDouble(rawPrice);
    }

    public static double sumOfDoublesFromString(String... string)
    {
        double sum = 0;
        for(String s: string){
            sum += parseDoubleFromString(s);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(parseDoubleFromString("$ 25,65") + 0.35);
        System.out.println(sumOfDoublesFromString("$ 25,65", "$0.35", "iu5.4"));
    }
}
