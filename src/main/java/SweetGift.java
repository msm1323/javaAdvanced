import java.util.*;

public class SweetGift {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество сладостей в подарке:");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("Сладость №" + (i + 1));
            String name;
            int type;
            double w;
            double p;
            int h;

            System.out.println("Введите номер типа сладости:" +
                    "\n(1 - Candy, 2 - Chocolate, 3 - Baklava)");
            type = scanner.nextInt();
            System.out.println("Введите наименование:");
            name = scanner.next();
            System.out.println("Введите вес (в формате числа с плавающей точкой):");
            w = scanner.nextDouble();
            System.out.println("Введите цену (в формате числа с плавающей точкой):");
            p = scanner.nextDouble();
            System.out.println("Введите номер характеристики для сладости:");
            switch (type) {
                case 1:
                    System.out.println("(1 - CARAMEL, 2 - CHOCOLATE)");
                    break;
                case 2:
                    System.out.println("(1 - STANDARD, 2 - SQUARE)");
                    break;
                case 3:
                    System.out.println("(1 - WALNUT, 2 - CASHEW, 3 - PISTACHIO)");
                    break;
            }
            h = scanner.nextInt();

            add(type, name, w, p, h);
        }

        System.out.println("Общий вес подарка = " + getWeight());
        System.out.println("Общая стоимость подарка = " + getPrice() + "\n");
        info();

    }


    private List<Sweet> sweets = new ArrayList<>();
    private double totalWeight = 0;
    private double totalPrice = 0;

    public void add(int type, String name, double w, double p, int h) {
        switch (type) {
            case 1:
                if (h == 1) {
                    sweets.add(new Candy(name, w, p, Candy.Type.CARAMEL));
                } else {
                    sweets.add(new Candy(name, w, p, Candy.Type.CHOCOLATE));
                }
                break;
            case 2:
                if (h == 1) {
                    sweets.add(new Chocolate(name, w, p, Chocolate.Form.STANDARD));
                } else {
                    sweets.add(new Chocolate(name, w, p, Chocolate.Form.SQUARE));
                }
                break;
            case 3:
                if (h == 1) {
                    sweets.add(new Baklava(name, w, p, Baklava.Filling.WALNUT));
                } else if (h == 2) {
                    sweets.add(new Baklava(name, w, p, Baklava.Filling.CASHEW));
                } else {
                    sweets.add(new Baklava(name, w, p, Baklava.Filling.PISTACHIO));
                }
                break;
        }
        totalWeight += w;
        totalPrice += p;
    }

    public double getWeight() {
        return totalWeight;
    }

    public double getPrice() {
        return totalPrice;
    }

    public void info() {
        for (Sweet sweet : sweets) {
            sweet.info();
            System.out.println();
        }
    }


    static class Candy extends Sweet {

        Type type;

        Candy(String title, double weight, double price, Type type) {
            super(title, weight, price);
            this.type = type;
        }

        @Override
        void info() {
            super.info();
            System.out.printf("Type: %s\n", this.type);
        }

        enum Type {
            CARAMEL,
            CHOCOLATE
        }

    }

    static class Chocolate extends Sweet {

        Form form;

        Chocolate(String title, double weight, double price, Form form) {
            super(title, weight, price);
            this.form = form;
        }

        @Override
        void info() {
            super.info();
            System.out.printf("Form: %s\n", this.form);
        }

        enum Form {
            STANDARD,
            SQUARE
        }
    }

    static class Baklava extends Sweet {
        Filling filling;

        Baklava(String title, double weight, double price, Filling filling) {
            super(title, weight, price);
            this.filling = filling;
        }

        @Override
        void info() {
            super.info();
            System.out.printf("Filling: %s\n", this.filling);
        }

        enum Filling {
            WALNUT,
            CASHEW,
            PISTACHIO
        }

    }

    public abstract static class Sweet {
        static int num = 0;
        final int id;
        String title;
        double weight;
        double price;

        Sweet(String title, double weight, double price) {
            id = num++;
            this.title = title;
            this.weight = weight;
            try {
                if (!String.valueOf(price).matches("[1-9]++\\d*+\\.\\d{1,2}?")) {
                    throw new InvalidFormat("Цена представлена в неверном формате." +
                            "\nНеобходимый формат: 0.00 (рубли и копейки)");
                }
                this.price = price;
            } catch (InvalidFormat ex) {
                System.out.println(ex.getMessage());
            }
        }

        void info() {
            System.out.printf("Title: %s\nID: %d\nPrice: %f\nWeight: %f\n",
                    this.title, this.id, this.price, this.weight);
        }

    }

    static class InvalidFormat extends Exception {
        public InvalidFormat(String message) {
            super(message);
        }
    }

}
