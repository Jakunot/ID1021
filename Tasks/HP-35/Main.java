
public class Main {
    private static Item[] expr = new Item[] {
            new Item(ItemType.VALUE,1),
            new Item(ItemType.VALUE,2),
            new Item(ItemType.VALUE,3),
            new Item(ItemType.VALUE,4),
            new Item(ItemType.VALUE,5),
            new Item(ItemType.VALUE,6),
            new Item(ItemType.VALUE,7),
            new Item(ItemType.VALUE,8),
            new Item(ItemType.VALUE,9),
            new Item(ItemType.VALUE,10),
            new Item(ItemType.VALUE,11),
            new Item(ItemType.VALUE,12),
            new Item(ItemType.VALUE,13),
            new Item(ItemType.VALUE,14),
            new Item(ItemType.VALUE,15),
            new Item(ItemType.VALUE,16),
            new Item(ItemType.ADD),
            new Item(ItemType.MUL),
            new Item(ItemType.ADD),
            new Item(ItemType.MUL),
            new Item(ItemType.ADD),
            new Item(ItemType.MUL),
            new Item(ItemType.ADD),
            new Item(ItemType.MUL),
            new Item(ItemType.ADD),
            new Item(ItemType.MUL),
            new Item(ItemType.ADD),
            new Item(ItemType.MUL),
            new Item(ItemType.ADD),
            new Item(ItemType.MUL),
            new Item(ItemType.ADD),

    };

    public static void benchmarkStatic(int calculations) {
        long startTime;
        long endTime;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < calculations; i++) {
            Calculator cal = new Calculator(expr);
            startTime = System.nanoTime();
            cal.run();
            endTime = System.nanoTime();
            if ((double) (endTime - startTime) < min) {
                min = (double) (endTime - startTime);
            }
        }
        System.out.println("Static stack: " + min + "ns");
    }
    public static void benchmarkDynamic(int calculations) {
        long startTime;
        long endTime;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < calculations; i++) {
            DynamicCalculator cal = new DynamicCalculator(expr);
            startTime = System.nanoTime();
            cal.run();
            endTime = System.nanoTime();
            if (((double) (endTime - startTime)) < min) {
                min = (double) (endTime - startTime);
            }
        }
        System.out.println("Dynamic stack: " + min + "ns");
    }
    public static void main(String[] args) {

        benchmarkStatic(100);
        benchmarkDynamic(100);
        System.out.println("-----------");
        benchmarkStatic(1000);
        benchmarkDynamic(1000);
        System.out.println("-----------");
        benchmarkStatic(10000);
        benchmarkDynamic(10000);
    }
}