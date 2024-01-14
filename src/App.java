import java.util.Arrays;
import java.util.Random;

public class App {

    // Função para medir o tempo de execução de cada algoritmo
    public static long measureTime(String partitionMethod, SortingFunction function, int[] arr) {
        long startTime = System.nanoTime();
        function.sort(arr.clone());
        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;
    
        double milliseconds = (double) timeTaken / 1_000_000.0;
        String formattedTime = String.format("%.7f", milliseconds);
    
        int trocas;
        if (function instanceof Lomuto) {
            trocas = ((Lomuto) function).getTrocas();
        } else {
            trocas = ((Hoare) function).getTrocas();
        }
        System.out.println(partitionMethod + ": " + formattedTime + " milissegundos");
        System.out.println("Quantidade de trocas: " + trocas);
    
        return timeTaken;
    }

    // Função para gerar um array de números aleatórios e únicos
    public static int[] generateRandomArray(int size) {
        int[] dataSet = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            dataSet[i] = random.nextInt(size + 1);
        }

        return dataSet;
    }

    // Função para encontrar o algoritmo/método mais rápido
    private static String findFastestAlgorithm(long... times) {
        long minTime = Long.MAX_VALUE;
        String fastestAlgorithm = "";

        String[] algorithms = {"Hoare", "Lomuto"};

        for (int i = 0; i < times.length; i++) {
            if (times[i] < minTime) {
                minTime = times[i];
                fastestAlgorithm = algorithms[i];
            }
        }

        return fastestAlgorithm;
    }

    public static void main(String[] args) {
        int[] randomArray = generateRandomArray(200000);
        int[] sizes = {100, 500, 1000, 5000, 30000, 80000, 100000, 150000, 200000};

        for (int size : sizes) {
            int[] subarray = Arrays.copyOfRange(randomArray, 0, size);
            System.out.println("\nTamanho do Subarray: " + size);

            // Teste com o método de particionamento de Tony Hoare
            long hoareTime = measureTime("Hoare", new Hoare(), subarray);

            // Teste com o método de particionamento de Lomuto
            long lomutoTime = measureTime("Lomuto", new Lomuto(), subarray);

            // Identificar o método mais rápido
            String fastestAlgorithm = findFastestAlgorithm(hoareTime, lomutoTime);
            System.out.println("O método mais rápido foi: " + fastestAlgorithm);
        }
    }

    interface SortingFunction {
        int[] sort(int[] arr);
    }
}